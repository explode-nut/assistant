package com.czn.assistant.util;

import com.czn.assistant.dto.response.LegendsListDTO;
import com.czn.assistant.service.impl.LegendsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//根据映射文件将爬虫结果的英文英雄名字转换为中文
@Component
public class LegendsNameConverter {
    LegendsListService legendsListService;

    @Autowired
    public LegendsNameConverter(LegendsListService legendsListService) {
        this.legendsListService = legendsListService;
    }

    //将数据库中的英雄英文名和中文名写入一个映射关系文件中
    public void writeLegendsNameToFile() {
        List<LegendsListDTO> list = legendsListService.getLegendsList();
        String filePath = "src/main/resources/LegendsNameMap.txt";
        String tmpFilePath = "src/main/resources/LegendsNameMap.txt.tmp";

        try (FileOutputStream fos = new FileOutputStream(tmpFilePath);
             FileChannel channel = fos.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将内容写入临时文件内
            for (LegendsListDTO legend : list) {
                String result = legend.getName() +
                        "-" + legend.getChineseName() + "\n";
                byte[] bytes = result.getBytes(StandardCharsets.UTF_8);

                buffer.put(bytes);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                buffer.clear();
            }
            // 将临时文件的内容写入到最终文件内
            FileChannel sourceChannel = null;
            FileChannel destinationChannel = null;
            try {
                sourceChannel = new FileInputStream(tmpFilePath).getChannel();
                destinationChannel = new FileOutputStream(filePath).getChannel();
                sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
            } finally {
                if (sourceChannel != null) {
                    sourceChannel.close();
                }
                if (destinationChannel != null) {
                    destinationChannel.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //删除临时文件
            new File(tmpFilePath).delete();
        }
    }

    //通过映射关系文件修改数据库中英雄的中文名字
    public Integer updateLegendsChineseNameBasedOnLegendsNameMapFile() {
        int n = 0;
        List<LegendsListDTO> list = legendsListService.getLegendsList();
        //英雄name与chineseName映射对象
        Map<String, String> map = convertLegendsListMapToObject();
        try {
            for (LegendsListDTO i : list) {
                LegendsListDTO legend = legendsListService.getLegend(i.getName());
                legend.setChineseName(map.get(legend.getName()));
                legendsListService.updateLegend(legend);
                n++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return n;
        }
        return n;
    }

    //将映射文件转换为Java对象
    //Map的第一个参数为name,第二个为chineseName
    private Map<String, String> convertLegendsListMapToObject() {
        Map<String, String> result = new HashMap<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/LegendsNameMap.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    result.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
