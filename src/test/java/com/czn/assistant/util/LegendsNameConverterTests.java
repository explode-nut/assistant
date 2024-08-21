package com.czn.assistant.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@SpringBootTest
public class LegendsNameConverterTests {
    @Autowired
    LegendsNameConverter legendsNameConverter;
    @Test
    void writeLegendsNameToFileTest() {
        legendsNameConverter.writeLegendsNameToFile();
        try (FileInputStream fis = new FileInputStream("src/main/resources/LegendsNameMap.txt");
             FileChannel fc = fis.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = -1;
            while ((bytesRead = fc.read(buffer)) != -1) {
                buffer.flip();
                //读取
                while (buffer.hasRemaining()) {
                    char b = (char) buffer.get();
                    System.out.print(b);
                }
                //清楚缓冲区
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateLegendsChineseNameBasedOnLegendsNameMapFileTest() {
        Integer i = legendsNameConverter.updateLegendsChineseNameBasedOnLegendsNameMapFile();
        System.out.println("成功更新" + i + "条数据");
    }
}
