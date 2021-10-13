package com.geotools.gistools.utils;

import com.geotools.gistools.beans.TileBox;
import com.geotools.gistools.mapper.TileMapper;
import com.geotools.gistools.request.TileParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/21 16:46
 */

@Component
public class TileUtils {
    @Autowired(required = false)
    TileMapper tileMapper;

    /**
     * 根据经纬度和缩放等级，求得瓦片路径
     **/
    public String getTileNumber(final double lat, final double lon, final int zoom) throws IOException {
        int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
        int ytile = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1 << zoom));
        if (xtile < 0)
            xtile = 0;
        if (xtile >= (1 << zoom))
            xtile = ((1 << zoom) - 1);
        if (ytile < 0)
            ytile = 0;
        if (ytile >= (1 << zoom))
            ytile = ((1 << zoom) - 1);
        return ("" + zoom + "/" + xtile + "/" + ytile);
    }

    public byte[] getTileData(TileParam tileParam) {
        TileBox tileBox = tile2boundingBox(tileParam.row, tileParam.col, tileParam.getZoom(), tileParam.layerName);

        List<Map> mpas = tileMapper.getSimpleTile(tileBox);

        byte[] dd = (byte[]) mpas.get(0).get("tile");

        return dd;
    }

    public byte[] getAggregationTile(TileParam tileParam) {
        TileBox tileBox = tile2boundingBox(tileParam.row, tileParam.col, tileParam.getZoom(), tileParam.layerName);
        tileBox.setXcount(tileParam.xcount);
        tileBox.setYcount(tileParam.ycount);
        List<Map> mpas = tileMapper.getAggregationTile(tileBox);

        byte[] dd = (byte[]) mpas.get(0).get("tile");

        return dd;
    }


    /**
     * 瓦片获得范围
     **/
    public TileBox tile2boundingBox(final int x, final int y, final int zoom, String layerName) {
        //BoundingBox bb = new BoundingBox();
        TileBox bb = new TileBox();
        bb.setYmax(tile2lat(y, zoom));
        bb.setYmin(tile2lat(y + 1, zoom));
        bb.setXmin(tile2lon(x, zoom));
        bb.setXmax(tile2lon(x + 1, zoom));
        bb.setLayerName(layerName);
        return bb;
    }

    /**
     * 瓦片转换经度
     **/
    public double tile2lon(int x, int z) {
        return x / Math.pow(2.0, z) * 360.0 - 180;
    }

    /**
     * 瓦片转换纬度
     *
     * @param y
     * @param z
     * @return
     * @author zhaoquanfeng 2018年8月13日 下午7:44:08
     * @modify {原因} by zhaoquanfeng 2018年8月13日 下午7:44:08
     */
    public double tile2lat(int y, int z) {
        double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, z);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }


}
