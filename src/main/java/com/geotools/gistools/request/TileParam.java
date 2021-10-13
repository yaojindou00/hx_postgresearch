package com.geotools.gistools.request;

import com.geotools.gistools.exception.ExceptionMsg;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/27 11:19
 */

public class TileParam extends ValidParameter {
    public String layerName;
    public int row;
    public int col;
    public Integer zoom;
    public Integer xcount;
    public Integer ycount;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Integer getZoom() {
        return zoom;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    @Override
    public boolean check() throws ExceptionMsg {
        return false;
    }

    public Integer getXcount() {
        return xcount;
    }

    public void setXcount(Integer xcount) {
        this.xcount = xcount;
    }

    public Integer getYcount() {
        return ycount;
    }

    public void setYcount(Integer ycount) {
        this.ycount = ycount;
    }

}
