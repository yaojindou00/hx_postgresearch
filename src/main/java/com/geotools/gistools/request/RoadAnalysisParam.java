package com.geotools.gistools.request;

import com.geotools.gistools.exception.ExceptionMsg;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/9/15 14:40
 */
public class RoadAnalysisParam extends ValidParameter{
    protected String layerName;
    protected Double startX;
    protected Double startY;
    protected Double endX;
    protected Double endY;
    public RoadAnalysisParam(){}
    public RoadAnalysisParam(String layerName,Double startX,Double startY,Double endX,Double endY){
        this.layerName=layerName;
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getLayerName() {
        return layerName;
    }

    public Double getStartX() {
        return startX;
    }

    public void setStartX(Double startX) {
        this.startX = startX;
    }

    public Double getStartY() {
        return startY;
    }

    public void setStartY(Double startY) {
        this.startY = startY;
    }

    public Double getEndX() {
        return endX;
    }

    public void setEndX(Double endX) {
        this.endX = endX;
    }

    public Double getEndY() {
        return endY;
    }

    public void setEndY(Double endY) {
        this.endY = endY;
    }

    @Override
    public boolean check() throws ExceptionMsg {
        return false;
    }
}
