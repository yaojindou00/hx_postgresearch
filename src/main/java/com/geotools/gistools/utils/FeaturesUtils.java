package com.geotools.gistools.utils;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.WKTReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ScopeMetadata;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/24 11:10
 */
public class FeaturesUtils {
    private static final Logger logger = LoggerFactory.getLogger(FeaturesUtils.class);

    public static Geometry chageGeoByValue(Geometry geo) {
        if (geo == null) return null;
        org.locationtech.jts.geom.GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        Geometry newGeo = null;
        if (geo instanceof com.vividsolutions.jts.geom.Point) {

        } else if (geo instanceof com.vividsolutions.jts.geom.MultiPoint) {

        } else if (geo instanceof com.vividsolutions.jts.geom.LineString) {

        } else if (geo instanceof com.vividsolutions.jts.geom.MultiLineString) {

        } else if (geo instanceof com.vividsolutions.jts.geom.Polygon) {

        } else if (geo instanceof com.vividsolutions.jts.geom.MultiPolygon) {

        }

        return null;
    }
}
