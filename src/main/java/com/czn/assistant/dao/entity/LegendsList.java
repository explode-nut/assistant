package com.czn.assistant.dao.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LegendsList implements Serializable {
    Integer id;
    String chineseName;
    String name;
}
