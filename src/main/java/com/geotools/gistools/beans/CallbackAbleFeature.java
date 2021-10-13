package com.geotools.gistools.beans;

import com.vividsolutions.jts.geom.Geometry;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/11 16:37
 */

public class CallbackAbleFeature implements Serializable {
    //记录一个要素的属性
    private Map<String, Object> attributes = new LinkedHashMap<String, Object>();
    //几何体
    private Geometry geometry = null;

    //wkt
    public String geoJson = "";
    /**
     * 几何类型.
     */
    private String geometryType = null;

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public void setGeometry(Geometry geometry) {
        if (geometry != null && geometryType == null) {
            this.geometryType = geometry.getGeometryType();
        }
        this.geometry = geometry;
    }

    public void setGeoJson(String strWkt) {
        this.geoJson = strWkt;
    }

    public String getGeoJson(String geoJson) {
        return geoJson;

    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name) {
        return (T) this.attributes.get(name);
    }


    public String getStringAttribute(String name) {
        return String.valueOf(this.attributes.get(name));
    }

    public double getDoubleAttribute(String name) {
        return Double.valueOf(this.getStringAttribute(name));
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    public String getGeometryType() {
        return geometryType;
    }

    //    @JsonIgnore
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public String toString() {
        return "CallbackAbleFeature [attributes=" + attributes + ", geometry=" + geometry + ", geometryType="
                + geometryType + "]";
    }

}
