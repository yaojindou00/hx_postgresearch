package com.geotools.gistools.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 功能描述：
 *
 * @Author: jdy
 * @Date: 2021/5/12 10:06
 */
@JsonIgnoreProperties(value = {"transportOrders"})
public class EsriLayer implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 图层名称
     */
    private String layerName = null;
    private String displayFieldName= "NAME";
    private String geometryType;
    private Map<String, Object> spatialReference;
    /**
     * 返回记录
     */
    private List<EsriFeature> features = new ArrayList<EsriFeature>();
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
  

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public List<EsriFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<EsriFeature> features) {
        this.features = features;
    }

    public int getMaxRecordCount() {
        return maxRecordCount;
    }

    public void setMaxRecordCount(int maxRecordCount) {
        this.maxRecordCount = maxRecordCount;
    }

   

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public void addFeature(EsriFeature feat) {
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

    
    public String getDisplayFieldName() {
		return displayFieldName;
	}

	public void setDisplayFieldName(String displayFieldName) {
		this.displayFieldName = displayFieldName;
	}

	public String getGeometryType() {
		return geometryType;
	}

	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	public Map<String, Object> getSpatialReference() {
		return spatialReference;
	}

	public void setSpatialReference(int srid) {
		Map<String, Object> spatialReference =new HashMap<String, Object>();
		spatialReference.put("latestWkid", srid);
		spatialReference.put("wkid", srid);
		this.spatialReference = spatialReference;
	}

	@Override
    public String toString() {
        return "Features [layerName=" + layerName + ", features=" + features + ", fields=" + fields + ", allCount="
                + allCount + ", maxRecordCount=" + maxRecordCount + "]";
    }

}
