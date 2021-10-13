package com.geotools.gistools.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.geotools.gistools.beans.ApiResult;
import com.geotools.gistools.beans.Features;
import com.geotools.gistools.exception.ExceptionMsg;
import com.geotools.gistools.request.QueryParam;
import com.geotools.gistools.request.QueryParameter;
import com.geotools.gistools.request.RoadAnalysisParam;
import com.geotools.gistools.service.SpatialDataQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能描述：
 *
 * @Author: jdy
 * @Date: 2021/5/12 10:32
 */
@RestController
@RequestMapping("rest")
@Api(description = "空间数据查询REST实现")
@CacheConfig(cacheNames = "cacheTest")
public class SpatialDataQueryController {
    private static final Logger logger = LoggerFactory.getLogger(SpatialDataQueryController.class);
    @Autowired
    private SpatialDataQueryService spatialDataQueryService;

    @ApiOperation(value = "空间数据属性查询")
    @RequestMapping(value = "api/search", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "layerName", required = true, dataType = "String", value = "空间数据库中的图层名称"),
            @ApiImplicitParam(paramType = "query", name = "filter", required = false, dataType = "String", value = "属性过滤条件，语法请参考SQL，例如：LXBM='G45' AND SXXFX=1"),
            @ApiImplicitParam(paramType = "query", name = "spatialFilter", required = false, dataType = "String", value = "空间过滤条件，标准的WKT"),
            @ApiImplicitParam(paramType = "query", name = "outFields", required = false, dataType = "String", value = "查询返回的字段,例如：LXBM,LXMC"),
            @ApiImplicitParam(paramType = "query", name = "isReturnGeometry", required = true, dataType = "Boolean", value = "是否返回空间数据"),
            @ApiImplicitParam(paramType = "query", name = "orderByFields", required = false, dataType = "String", value = "排序条件，语法参考SQL，例如：ORDER BY NAME DESC"),
            @ApiImplicitParam(paramType = "query", name = "spatialRel", required = true, dataType = "String", allowableValues = "INTERSECTS,CONTAINS,DISJOINT,TOUCHES,CROSSES,WITHIN,OVERLAPS", value = "空间位置关系"),
            @ApiImplicitParam(paramType = "query", name = "current", required = false, dataType = "String", value = "分页参数，第几页，不传此参数默认不分页，开始页数为1"),
            @ApiImplicitParam(paramType = "query", name = "limit", required = false, dataType = "String", value = "每页记录数，此参数可选，默认为全部")})
    // @Cacheable
    public ApiResult search(@RequestParam(value = "layerName", required = true) String layerName,
                            @RequestParam(value = "filter", required = false) String filter,
                            @RequestParam(value = "spatialFilter", required = false) String spatialFilter,
                            @RequestParam(value = "outFields", required = false) String outFields,
                            @RequestParam(value = "isReturnGeometry", required = true) Boolean isReturnGeometry,
                            @RequestParam(value = "orderByFields", required = false) String orderByFields,
                            @RequestParam(value = "spatialRel", required = false) String spatialRel,
                            @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                            @RequestParam(value = "limit", required = false, defaultValue = "-1") Integer limit) throws RemoteException, ExceptionMsg {
//        logger.info("空间数据属性查询调用接口开始=======图层名称:{},属性过滤条件:{},空间过滤条件:{},返回的字段:{},是否返回空间数据:{},排序条件:{},空间位置关系:{},页码:{},每页记录数:{}", layerName, filter, spatialFilter, outFields, isReturnGeometry, orderByFields, spatialRel, current, limit);
        ApiResult apiData = new ApiResult();
        QueryParameter param = new QueryParameter(layerName, filter, spatialFilter, outFields, isReturnGeometry, orderByFields, spatialRel, current, limit);

        Features pFeartrues = spatialDataQueryService.search(param);
        apiData.setData(pFeartrues);
        return apiData;
    }

    @ApiOperation(value = "缓冲查询")
    @RequestMapping(value = "bufferSearch", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "layerName", required = true, dataType = "String", value = "空间数据库中的图层名称"),
            @ApiImplicitParam(paramType = "query", name = "filter", required = false, dataType = "String", value = "属性过滤条件，语法请参考SQL，例如：LXBM='G45' AND SXXFX=1"),
            @ApiImplicitParam(paramType = "query", name = "spatialFilter", required = false, dataType = "String", value = "空间过滤条件，标准的WKT"),
            @ApiImplicitParam(paramType = "query", name = "outFields", required = false, dataType = "String", value = "查询返回的字段,例如：LXBM,LXMC"),
            @ApiImplicitParam(paramType = "query", name = "isReturnGeometry", required = true, dataType = "Boolean", value = "是否返回空间数据"),
            @ApiImplicitParam(paramType = "query", name = "orderByFields", required = false, dataType = "String", value = "排序条件，语法参考SQL，例如：ORDER BY NAME DESC"),
            @ApiImplicitParam(paramType = "query", name = "buffDis", required = false, dataType = "String", value = "缓冲距离"),
            @ApiImplicitParam(paramType = "query", name = "current", required = false, dataType = "String", value = "分页参数，第几页，不传此参数默认不分页，开始页数为1"),
            @ApiImplicitParam(paramType = "query", name = "limit", required = false, dataType = "String", value = "每页记录数，此参数可选，默认为全部")})
    @Cacheable
    public ApiResult bufferSearch(@RequestParam(value = "layerName", required = true) String layerName,
                                  @RequestParam(value = "filter", required = false) String filter,
                                  @RequestParam(value = "spatialFilter", required = false) String spatialFilter,
                                  @RequestParam(value = "outFields", required = false) String outFields,
                                  @RequestParam(value = "isReturnGeometry", required = true) Boolean isReturnGeometry,
                                  @RequestParam(value = "orderByFields", required = false) String orderByFields,

                                  @RequestParam(value = "buffDis", required = false, defaultValue = "0") Integer buffDis,
                                  @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                                  @RequestParam(value = "limit", required = false, defaultValue = "-1") Integer limit) {
        ApiResult apiData = new ApiResult();
        QueryParameter param = new QueryParameter(layerName, filter, spatialFilter, outFields,
                isReturnGeometry, orderByFields, buffDis, current, limit);

        Features pFeartrues = spatialDataQueryService.bufferSearch(param);

        apiData.setData(pFeartrues);
        return apiData;
    }

    @ApiOperation(value = "空间数据属性查询按行政区")
    @RequestMapping(value = "getDataByNameOrCode", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "layerName", required = true, dataType = "String", value = "空间数据库中的图层名称"),
            @ApiImplicitParam(paramType = "query", name = "where", required = false, dataType = "String", value = "属性过滤条件，语法请参考SQL，例如：LXBM='G45' AND SXXFX=1"),
            @ApiImplicitParam(paramType = "query", name = "cityLayerName", required = true, dataType = "String", value = "城市空间数据库中的图层名称"),
            @ApiImplicitParam(paramType = "query", name = "cityname", required = false, dataType = "String", value = "城市名称过滤条件，语法请参考SQL，cityname='延安市'"),
            @ApiImplicitParam(paramType = "query", name = "citycode", required = false, dataType = "String", value = "城市代码，语法请参考SQL，citycode='600061000'"),
            @ApiImplicitParam(paramType = "query", name = "outFields", required = false, dataType = "String", value = "查询返回的字段,例如：LXBM,LXMC"),
            @ApiImplicitParam(paramType = "query", name = "orderByFields", required = false, dataType = "String", value = "排序条件，语法参考SQL，例如：ORDER BY NAME DESC"),
            @ApiImplicitParam(paramType = "query", name = "current", required = false, dataType = "String", value = "分页参数，第几页，不传此参数默认不分页，开始页数为1"),
            @ApiImplicitParam(paramType = "query", name = "limit", required = false, dataType = "String", value = "每页记录数，此参数可选，默认为全部")})
    @Cacheable
    public ApiResult getDataByNameOrCode(@RequestParam(value = "layerName", required = true) String layerName,
                                         @RequestParam(value = "where", required = false) String where,
                                         @RequestParam(value = "cityLayerName", required = false) String cityLayerName,
                                         @RequestParam(value = "cityname", required = false) String cityname,
                                         @RequestParam(value = "citycode", required = false) String citycode,
                                         @RequestParam(value = "outFields", required = false) String outFields,
                                         @RequestParam(value = "orderByFields", required = false) String orderByFields,
                                         @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                                         @RequestParam(value = "limit", required = false, defaultValue = "-1") Integer limit) {
        ApiResult apiData = new ApiResult();
        QueryParam param = new QueryParam(layerName, cityLayerName, cityname, citycode, where,
                outFields, orderByFields, current, limit);

        Features pFeartrues = spatialDataQueryService.getDataByNameOrCode(param);

        apiData.setData(pFeartrues);
        return apiData;
    }

    /**
     * 批量新增
     *
     * @param map {tablename:"test",list:[{name:"讨厌",descrape:"规划",geom:'POINT(109.28 37.75)'},{name:"讨厌22",descrape:"规划2",geom:'POINT(109.38 36.75)'}]
     * @return
     */
    @PostMapping("/insertData")
    @ResponseBody
    @CrossOrigin
    int insertData(@RequestBody HashMap<String, Object> map) {

        List<HashMap<String, Object>> objs = getData(map, 0);
        int num = 0;
        for (HashMap<String, Object> obj : objs) {
            num += spatialDataQueryService.insertData(obj);
        }
        return num;
    }

    /**
     * 批量更新
     *
     * @param map{tablename:"test",list:[ {name:"讨厌00",descrape:"规划0",geom:'POINT(109.28 37.75)',wheres:{gid:20}},
     *                                    {name:"讨厌0",descrape:"规划0",geom:'POINT(109.38 35.75)',wheres:{gid:21}}
     *                                    ]}
     * @return
     */
    @PostMapping("/updateData")
    @ResponseBody
    @CrossOrigin
    int updateData(@RequestBody HashMap<String, Object> map) {

        List<HashMap<String, Object>> objs = getData(map, 1);
        int num = 0;
        for (HashMap<String, Object> obj : objs) {
            num += spatialDataQueryService.updateData(obj);
        }
        return num;
    }

    /**
     * 批量删除
     *
     * @param obj 例：{tablename:"test",filedid:"gid", list:[2,6]}
     * @return
     */
    @PostMapping("/deleteData")
    @ResponseBody
    @CrossOrigin
    int deleteData(@RequestBody HashMap<String, Object> obj) {
        return spatialDataQueryService.deleteData(obj);
    }

    /**
     * 城市数据统计
     *
     * @param "layername要统计表名"cun_sx"
     * @param "citytablename城市表"city_gz"
     * @param "outFields城市表字段cityname,"citycode"
     * @param "type统计格式数量"count(*)",求和"sum(length)",平均"avg(length)"
     * @return
     */
    @GetMapping("/getGroupData")
    @ResponseBody
    @CrossOrigin
    List<HashMap<String, Object>> getGroupData(
            @RequestParam(value = "layername", required = true) String layername,
            @RequestParam(value = "citytablename", required = true) String citytablename,
            @RequestParam(value = "outFields", required = true) String outFields,
            @RequestParam(value = "type", required = true) String type) {

        List<HashMap<String, Object>> groupData = spatialDataQueryService.getGroupData(layername, citytablename, outFields, type);

        return groupData;
    }

    @ApiOperation(value = "通过经纬度获取所在城市名称")
    @RequestMapping(value = "getCityNameByLatLng", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "tablename", required = true, dataType = "String", value = "表名"),
            @ApiImplicitParam(paramType = "query", name = "cityname", required = true, dataType = "String", value = "城市名字段"),
            @ApiImplicitParam(paramType = "query", name = "lng", required = true, dataType = "String", value = "经度"),
            @ApiImplicitParam(paramType = "query", name = "lat", required = true, dataType = "String", value = "纬度")})
    @Cacheable
    public ApiResult getCityNameByLatLng(
            @RequestParam(value = "tablename", required = true) String tablename,
            @RequestParam(value = "cityname", required = true) String cityname,
            @RequestParam(value = "lng", required = true) String lng,
            @RequestParam(value = "lat", required = true) String lat) {
        ApiResult apiData = new ApiResult();
        String citydata = spatialDataQueryService.getCityNameByLatLng(tablename, cityname, lng, lat);
        apiData.setData(citydata);
        return apiData;
    }

    //修改数据格式
    List<HashMap<String, Object>> getData(HashMap<String, Object> obj, int state) {
        List<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        String tablename = obj.get("tablename").toString();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = (List<Map<String, Object>>) obj.get("list");
        for (Map<String, Object> map : list) {
            HashMap<String, Object> map2 = new HashMap<String, Object>();
            map2.put("tablename", tablename);
            List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
            for (String key : map.keySet()) {
                if (!key.equals("wheres")) {
                    HashMap<String, Object> map3 = new HashMap<String, Object>();
                    map3.put("name", key);
                    map3.put("value", map.get(key));
                    list2.add(map3);
                }

            }
            map2.put("items", list2);
            if (state == 1) {
                List<Map<String, Object>> list4 = new ArrayList<Map<String, Object>>();
                Map<String, Object> map6 = new HashMap<String, Object>();
                map6 = (Map<String, Object>) map.get("wheres");
                for (String key : map6.keySet()) {
                    HashMap<String, Object> map5 = new HashMap<String, Object>();
                    map5.put("name", key);
                    map5.put("value", map6.get(key));
                    list4.add(map5);
                }
                map2.put("wheres", list4);
            }
            items.add(map2);

        }
        return items;
    }
    @ApiOperation(value = "路径分析")
    @RequestMapping(value = "roadAnaysis", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "layerName", required = true, dataType = "String", value = "路径分析的线图层，必须是单线"),
            @ApiImplicitParam(paramType = "query", name = "startX", required = true, dataType = "Double", value = "起点位置X坐标"),
            @ApiImplicitParam(paramType = "query", name = "startY", required = true, dataType = "Double", value = "起点位置Y坐标"),
            @ApiImplicitParam(paramType = "query", name = "endX", required = true, dataType = "Double", value = "终点位置X坐标"),
            @ApiImplicitParam(paramType = "query", name = "endY", required = true, dataType = "Double", value = "终点位置Y坐标")
            })
    @Cacheable
    public ApiResult roadAnaysis(@RequestParam(value = "layerName", required = true) String layerName,
                                 @RequestParam(value = "startX", required = true) Double startX,
                                 @RequestParam(value = "startY", required = true) Double startY,
                                 @RequestParam(value = "endX", required = true) Double endX,
                                 @RequestParam(value = "endY", required = true) Double endY){
        ApiResult apiData=new ApiResult();
        RoadAnalysisParam roadAnalysisParam=new RoadAnalysisParam(layerName,startX,startY,endX,endY);
        Features pFeartrues = spatialDataQueryService.roadAnaysis(roadAnalysisParam);
        apiData.setData(pFeartrues);
        apiData.setCode(200);
        return apiData;
    }
}
