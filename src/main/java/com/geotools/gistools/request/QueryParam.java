package com.geotools.gistools.request;

import com.geotools.gistools.exception.ExceptionMsg;
import com.vividsolutions.jts.geom.Geometry;

import java.io.Serializable;
import java.util.Map;

/**
 * 功能描述：
 *
 * @Author: jdy
 * @Date: 2021/5/17 15:11
 */
public class QueryParam extends ValidParameter {
    /**
     * 图层名称
     */
    protected String layerName;
    /**
     * 城市图层名称
     */
    protected String cityLayerName;
    /**
     * 城市名
     */
    protected String cityname;
    /**
     * 城市代码
     */
    protected String citycode;
    /**
     * 属性过滤条件
     */
    protected String where;

    /**
     * 返回字段
     */
    protected String outFields;

    /**
     * 排序字段
     */
    protected String orderByFields;

    /**
     * 分页信息
     */
    protected Integer current;

    protected Integer limit;

    public QueryParam() {
    }


    public QueryParam(String layerName, String cityLayerName, String cityname, String citycode, String where,
                      String outFields, String orderByFields, Integer current, Integer limit) {
        super();
        this.layerName = layerName;
        this.cityLayerName = cityLayerName;
        this.cityname = cityname;
        this.citycode = citycode;
        this.where = where;
        this.outFields = outFields;
        this.orderByFields = orderByFields;
        this.current = current;
        this.limit = limit;
    }


    @Override
    public boolean check() throws ExceptionMsg {
        ValidParameter.isBlank(this.layerName, "'layerName'参数不能为空!");
        ValidParameter.isBlank(this.cityLayerName, "'cityLayerName'参数不能为空!");
        return true;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }


    public String getOutFields() {
        return outFields;
    }

    public void setOutFields(String outFields) {
        this.outFields = outFields;
    }


    public String getOrderByFields() {
        return orderByFields;
    }

    public void setOrderByFields(String orderByFields) {
        this.orderByFields = orderByFields;
    }


    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }


    public String getCityLayerName() {
        return cityLayerName;
    }


    public void setCityLayerName(String cityLayerName) {
        this.cityLayerName = cityLayerName;
    }


    public String getCityname() {
        return cityname;
    }


    public void setCityname(String cityname) {
        this.cityname = cityname;
    }


    public String getCitycode() {
        return citycode;
    }


    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }


}
