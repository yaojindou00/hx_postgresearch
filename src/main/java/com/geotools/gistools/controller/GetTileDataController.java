package com.geotools.gistools.controller;

import com.geotools.gistools.request.TileParam;
import com.geotools.gistools.service.GetTileUrlService;
import com.geotools.gistools.utils.TileUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/21 15:16
 */
@RestController
@RequestMapping("rest")
@Api(description = "空间数据查询REST实现")
public class GetTileDataController {
    private static final Logger logger = LoggerFactory.getLogger(GetTileDataController.class);
    @Autowired
    private GetTileUrlService getTileUrlService;

    /**
     * 普通动态切片
     *
     * @param city
     * @param z
     * @param x
     * @param y
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/hxgis/tile/{city}/{z}/{x}/{y}", method = RequestMethod.GET)
    public Object getSimpleTileUrl(@PathVariable("city") String city, @PathVariable("z") Integer z, @PathVariable("x") Integer x, @PathVariable("y") Integer y,
                                   HttpServletResponse response,
                                   HttpServletRequest request) throws IOException {

        TileParam tileParam = new TileParam();
        tileParam.row = x;
        tileParam.col = y;
        tileParam.zoom = z;
        tileParam.layerName = city;
        response.setContentType("application/x-protobuf;type=mapbox-vector;chartset=UTF-8");
        return getTileUrlService.getSimpleTileUrl(tileParam);

    }

    /**
     * 聚合动态切片
     *
     * @param city
     * @param z
     * @param x
     * @param y
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/hxgis/jhtile/{xcount}/{ycount}/{city}/{z}/{x}/{y}", method = RequestMethod.GET)
    public Object getAggregationTile(@PathVariable("xcount") Integer xcount, @PathVariable("ycount") Integer ycount, @PathVariable("city") String city, @PathVariable("z") Integer z, @PathVariable("x") Integer x, @PathVariable("y") Integer y,
                                     HttpServletResponse response,
                                     HttpServletRequest request) throws IOException {

        TileParam tileParam = new TileParam();
        tileParam.row = x;
        tileParam.col = y;
        tileParam.zoom = z;
        tileParam.layerName = city;
        tileParam.xcount = xcount;
        tileParam.ycount = ycount;
        response.setContentType("application/x-protobuf;type=mapbox-vector;chartset=UTF-8");
        return getTileUrlService.getAggregationTile(tileParam);

    }
}
