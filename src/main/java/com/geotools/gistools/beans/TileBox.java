package com.geotools.gistools.beans;

import lombok.Data;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/21 16:49
 */
@Data
public class TileBox {
    double Xmax;
    double Xmin;
    double Ymax;
    double Ymin;
    String layerName;
    Integer xcount;
    Integer ycount;

}
