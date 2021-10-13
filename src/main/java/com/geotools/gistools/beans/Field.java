package com.geotools.gistools.beans;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @Author: jdy
 * @Date: 2021/5/12 10:05
 */
public class Field implements Serializable {
    /**
     * .字段标注
     */
    private String label = "";

    /**
     * .字段名称
     */
    private String name = "";

    /**
     * .字段类型
     */
    private String type = "";

    public Field() {
    }

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Field(String name, String type, String label) {
        this.name = name;
        this.type = type;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
