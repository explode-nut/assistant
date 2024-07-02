package com.czn.assistant.dao.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LegendsList implements Serializable {
    Long id;
    String chineseName;
    String name;
}
