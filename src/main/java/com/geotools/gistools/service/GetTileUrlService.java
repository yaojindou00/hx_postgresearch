package com.geotools.gistools.service;
import com.geotools.gistools.request.TileParam;
import com.geotools.gistools.utils.TileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/27 14:16
 */
@Service
public class GetTileUrlService   {
    @Autowired(required = false)
    private TileUtils tileUtils;

    
    public byte[] getSimpleTileUrl(TileParam tileParam) throws IOException {

        return tileUtils.getTileData(tileParam);
    }

    
    public byte[] getAggregationTile(TileParam tileParam) throws IOException {

        return tileUtils.getAggregationTile(tileParam);
    }
}
