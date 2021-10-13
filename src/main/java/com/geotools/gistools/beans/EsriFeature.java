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

public class EsriFeature implements Serializable {
    //记录一个要素的属性
    private Map<String, Object> attributes = new LinkedHashMap<String, Object>();

    public Map<String, Object> geometry ;

   
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setAttributes(String name, Object value) {
        this.attributes.put(name, value);
    }

    public void setGeometry(Map<String, Object> geometry) {

        this.geometry = geometry;
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

    public Map<String, Object> getGeometry() {
        return geometry;
    }

    @Override
    public String toString() {
        return "CallbackAbleFeature [attributes=" + attributes + ", geometry=" + geometry + ", geometryType=]";
    }

}
