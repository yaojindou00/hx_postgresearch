package com.geotools.gistools.service;

import com.geotools.gistools.beans.CallbackAbleFeature;
import com.geotools.gistools.beans.Features;
import com.geotools.gistools.beans.Field;
import com.geotools.gistools.exception.ExceptionMsg;
import com.geotools.gistools.mapper.CommonMapper;
import com.geotools.gistools.request.QueryParam;
import com.geotools.gistools.request.QueryParameter;
import com.geotools.gistools.request.RoadAnalysisParam;
import com.google.gson.Gson;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/12 10:37
 */
@Service
public class SpatialDataQueryService   {
   
    @Resource
    private CommonMapper commonMapper;
   
    public Features search(QueryParameter queryParameter) throws RemoteException, ExceptionMsg {
    	 Features featuresSet = new Features();
         String outFields = queryParameter.getOutFields();
         List<Field> fields = new ArrayList<Field>();
         if (outFields == null || outFields.equals("") || outFields.equals("*")) {//不传参默认全部
             queryParameter.setOutFields("*");
         }
         List<Map<String, Object>> lists = commonMapper.search(queryParameter);
         creatFeatures(queryParameter, featuresSet, fields, lists);


         return featuresSet;
    }

    
    public Features bufferSearch(QueryParameter queryParameter) {
    	Features featuresSet = new Features();
        String outFields = queryParameter.getOutFields();
        List<Field> fields = new ArrayList<Field>();
        if (outFields == null || outFields.equals("") || outFields.equals("*")) {//不传参默认全部
            queryParameter.setOutFields("*");
        }
        List<Map<String, Object>> lists = commonMapper.bufferSearch(queryParameter);

        creatFeatures(queryParameter, featuresSet, fields, lists);

        return featuresSet;
    }

    
    public Features getDataByNameOrCode(QueryParam queryParam) {
    	 Features featuresSet = new Features();
         String outFields = queryParam.getOutFields();
         List<Field> fields = new ArrayList<Field>();

         if (outFields == null || outFields.equals("") || outFields.equals("*")) {//不传参默认全部
             queryParam.setOutFields("*");
         }
         List<Map<String, Object>> lists = commonMapper.getDataByNameOrCode(queryParam);
         List<CallbackAbleFeature> features = new ArrayList<CallbackAbleFeature>();
         List<Map<String, Object>> columns = commonMapper.getColumns(queryParam.getLayerName());
         addFields(queryParam.getOutFields(), fields, columns);
         for (Map<String, Object> map : lists) {
             CallbackAbleFeature callbackAbleFeature = new CallbackAbleFeature();
             //获取几何信息
             String wkt = map.get("geom").toString();
             Gson gson = new Gson();
             Map<String, Object> geomap = new HashMap<String, Object>();
             geomap = gson.fromJson(wkt, map.getClass());
             callbackAbleFeature.setGeometry(geomap);
             //生成属性
             HashMap<String, Object> hashMap = new HashMap<String, Object>();
             for (Field field : fields) {
                 hashMap.put(field.getName(), map.get(field.getName()));
             }
             callbackAbleFeature.setProperties(hashMap);

             features.add(callbackAbleFeature);
         }
         featuresSet.setAllCount(features.size());
         featuresSet.setFeatures(features);
         featuresSet.setFields(fields);
         featuresSet.setLayerName(queryParam.getLayerName());
         return featuresSet;
    }

    
    public int insertData(HashMap<String, Object> obj) {

        return commonMapper.insertData(obj);
    }

    
    public int updateData(HashMap<String, Object> obj) {

        return commonMapper.updateData(obj);
    }

    
    public int deleteData(HashMap<String, Object> obj) {

        return commonMapper.deleteData(obj);
    }

    
    public List<HashMap<String, Object>> getGroupData(String layername, String citytable, String outFields, String type) {

        return commonMapper.getGroupData(layername, citytable, outFields, type);
    }

    
    public String getCityNameByLatLng(String tablename, String cityname, String lng, String lat) {

        return commonMapper.getCityNameByLatLng(tablename, cityname, lng, lat);
    }

    
    public Features roadAnaysis(RoadAnalysisParam roadAnalysisParam) {
    	  Features pFeatrues=new Features();
          pFeatrues.setLayerName(roadAnalysisParam.getLayerName());
          List<Map<String, Object>> lists = commonMapper.roadAnaysis(roadAnalysisParam);
          if(lists.size()>0){
              pFeatrues.setAllCount(lists.size());
              for (Map<String, Object> map : lists) {
                  CallbackAbleFeature callbackAbleFeature = new CallbackAbleFeature();
                  //获取几何信息
                  String wkt = map.get("geom").toString();
                  Gson gson = new Gson();
                  Map<String, Object> geomap = new HashMap<String, Object>();
                  geomap = gson.fromJson(wkt, map.getClass());
                  callbackAbleFeature.setGeometry(geomap);
                  pFeatrues.addFeature(callbackAbleFeature);
              }
          }
          return pFeatrues;
        
    }
    
    
    
    /**
     * featuresSet属性设置
     *
     * @param queryParameter
     * @param featuresSet
     * @param fields
     * @param lists
     */
    private void creatFeatures(QueryParameter queryParameter, Features featuresSet, List<Field> fields,
                               List<Map<String, Object>> lists) {
        List<CallbackAbleFeature> features = new ArrayList<CallbackAbleFeature>();
        List<Map<String, Object>> columns = commonMapper.getColumns(queryParameter.getLayerName());

        addFields(queryParameter.getOutFields(), fields, columns);//生成fields
        for (Map<String, Object> map : lists) {
            CallbackAbleFeature callbackAbleFeature = new CallbackAbleFeature();
            if (queryParameter.isReturnGeometry()) {
                String wkt = map.get("geom").toString();
                Gson gson = new Gson();
                Map<String, Object> geomap = new HashMap<String, Object>();
                geomap = gson.fromJson(wkt, map.getClass());
                callbackAbleFeature.setGeometry(geomap);


            }
            HashMap<String, Object> hashMap = new HashMap<String, Object>();

            for (Field field : fields) {
                hashMap.put(field.getName(), map.get(field.getName()));
            }

            callbackAbleFeature.setProperties(hashMap);
            features.add(callbackAbleFeature);

        }
        featuresSet.setAllCount(lists.size());
        featuresSet.setLayerName(queryParameter.getLayerName());
        featuresSet.setFeatures(features);
        featuresSet.setFields(fields);
    }
    /**
     * 添加field
     *
     * @param "queryParam"
     * @param fields
     * @param columns
     */
    private void addFields(String outFields, List<Field> fields, List<Map<String, Object>> columns) {
        String[] atrs;
        if (outFields.equals("*")) {
            for (Map<String, Object> map : columns) {
                String column_name = map.get("column_name").toString();
                if (!column_name.equals("geom")) {
                    typeTran(fields, map, column_name);
                }
            }
        } else {
            atrs = outFields.split(",");
            for (Map<String, Object> map : columns) {
                String column_name = map.get("column_name").toString();
                for (String atr : atrs) {
                    if (atr.equals(column_name)) {
                        typeTran(fields, map, column_name);
                    }

                }
            }
        }
    }

    /**
     * 数据类型转化并添加field
     *
     * @param fields
     * @param map
     * @param column_name
     */
    private void typeTran(List<Field> fields, Map<String, Object> map, String column_name) {
        String data_type = map.get("data_type").toString();
        if (data_type.indexOf("int") > -1) {
            data_type = "integer";
        } else if (data_type.indexOf("character") > -1) {
            data_type = "string";
        } else if (data_type.indexOf("numeric") > -1 || data_type.indexOf("float8") > -1) {
            data_type = "double";
        }
        Field field = new Field(column_name, data_type, column_name);
        fields.add(field);
    }
}
