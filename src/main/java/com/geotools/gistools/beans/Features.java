package com.geotools.gistools.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/12 10:06
 */
@JsonIgnoreProperties(value = {"transportOrders"})
public class
Features implements Serializable {
    /**
     * 图层名称
     */
    private String layerName = null;

    /**
     * 返回记录
     */
    private List<CallbackAbleFeature> features = new ArrayList<CallbackAbleFeature>();
    /**
     * 图层字段列表
     */
    private List<Field> fields = new ArrayList<Field>();
    /**
     * 本次查询的总记录条数
     */
    private int allCount = 0;
    /**
     * 最大返回记录数
     */
    private int maxRecordCount = 100000;
    /**
     * 是否达到最大记录数
     */
    private boolean isMax = false;

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public List<CallbackAbleFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<CallbackAbleFeature> features) {
        this.features = features;
    }

    public int getMaxRecordCount() {
        return maxRecordCount;
    }

    public void setMaxRecordCount(int maxRecordCount) {
        this.maxRecordCount = maxRecordCount;
    }

    public boolean isMax() {
        return isMax;
    }

    public void setMax(boolean isMax) {
        this.isMax = isMax;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public void addFeature(CallbackAbleFeature feat) {
        this.features.add(feat);
    }

    private void addFields(Field field) {
        this.fields.add(field);
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Features [layerName=" + layerName + ", features=" + features + ", fields=" + fields + ", allCount="
                + allCount + ", maxRecordCount=" + maxRecordCount + ", isMax=" + isMax + "]";
    }

}
