package com.geotools.gistools.mapper;

import com.geotools.gistools.beans.TileBox;
import com.geotools.gistools.request.TileParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/27 11:01
 */
@Repository
@org.apache.ibatis.annotations.Mapper
public interface TileMapper {
    List<Map> getSimpleTile(TileBox tileBox);

    List<Map> getAggregationTile(TileBox tileBox);
}

