package com.geotools.gistools.beans;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述：
 *
 * @Author: jdy
 * @Date: 2021/5/11 16:37
 */

public class CallbackAbleFeature implements Serializable {
    //记录一个要素的属性
    private Map<String, Object> properties = new LinkedHashMap<String, Object>();
    //几何体
//    private Geometry geometry = null;

    //wkt
    public Map<String, Object> geometry ;
    /**
     * 几何类型.
     */
//    private String geometryType = null;
    public String type ="Feature";
    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void Properties(String name, Object value) {
        this.properties.put(name, value);
    }

    public void setGeometry(Map<String, Object> geometry) {
//        if (geometry != null && geometryType == null) {
//            this.geometryType = geometry.getGeometryType();
//        }
        this.geometry = geometry;
    }
//
//    public void setGeoJson(String strWkt) {
//        this.geoJson = strWkt;
//    }
//
//    public String getGeoJson(String geoJson) {
//        return geoJson;
//
//    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name) {
        return (T) this.properties.get(name);
    }


    public String getStringAttribute(String name) {
        return String.valueOf(this.properties.get(name));
    }

    public double getDoubleAttribute(String name) {
        return Double.valueOf(this.getStringAttribute(name));
    }

//    public void setGeometryType(String geometryType) {
//        this.geometryType = geometryType;
//    }
//
//    public String getGeometryType() {
//        return geometryType;
//    }

    //    @JsonIgnore
    public Map<String, Object> getGeometry() {
        return geometry;
    }

    @Override
    public String toString() {
        return "CallbackAbleFeature [attributes=" + properties + ", geometry=" + geometry + ", geometryType=]";
    }

}
