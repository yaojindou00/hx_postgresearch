package com.geotools.gistools.utils;

import java.io.StringWriter;

import org.geotools.geojson.geom.GeometryJSON;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;


public class WktAndGeom {

    private GeometryFactory geometryFactory = new GeometryFactory();

    /**
     * create a point
     *
     * @return
     */
    public Point createPoint() {
        Coordinate coord = new Coordinate(109.013388, 32.715519);
        Point point = geometryFactory.createPoint(coord);
        return point;
    }

    /**
     * create a point by WKT
     *
     * @return
     * @throws ParseException
     */
    public Point createPointByWKT(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
//        "POINT (109.013388 32.715519)"
        Point point = (Point) reader.read(wkt);
        return point;
    }

    /**
     * create multiPoint by wkt
     *
     * @return
     */
    public MultiPoint createMulPointByWKT(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
//        "MULTIPOINT(109.013388 32.715519,119.32488 31.435678)"
        MultiPoint mpoint = (MultiPoint) reader.read(wkt);
        return mpoint;
    }

    /**
     * create a line
     *
     * @return
     */
    public LineString createLine() {
        Coordinate[] coords = new Coordinate[]{new Coordinate(2, 2), new Coordinate(2, 2)};
        LineString line = geometryFactory.createLineString(coords);
        return line;
    }

    /**
     * create a line by WKT
     *
     * @return
     * @throws ParseException
     */
    public LineString createLineByWKT(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
//        "LINESTRING(0 0, 2 0)"
        LineString line = (LineString) reader.read(wkt);
        return line;
    }

    /**
     * create multiLine
     *
     * @return
     */
    public MultiLineString createMLine() {
        Coordinate[] coords1 = new Coordinate[]{new Coordinate(2, 2), new Coordinate(2, 2)};
        LineString line1 = geometryFactory.createLineString(coords1);
        Coordinate[] coords2 = new Coordinate[]{new Coordinate(2, 2), new Coordinate(2, 2)};
        LineString line2 = geometryFactory.createLineString(coords2);
        LineString[] lineStrings = new LineString[2];
        lineStrings[0] = line1;
        lineStrings[1] = line2;
        MultiLineString ms = geometryFactory.createMultiLineString(lineStrings);
        return ms;
    }

    /**
     * create multiLine by WKT
     *
     * @return
     * @throws ParseException
     */
    public MultiLineString createMLineByWKT(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
//        "MULTILINESTRING((0 0, 2 0),(1 1,2 2))"
        MultiLineString line = (MultiLineString) reader.read(wkt);
        return line;
    }

    /**
     * create a polygon(多边形) by WKT
     *
     * @return
     * @throws ParseException
     */
    public Polygon createPolygonByWKT(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
//        "POLYGON((20 10, 30 0, 40 10, 30 20, 20 10))"
        Polygon polygon = (Polygon) reader.read(wkt);
        return polygon;
    }

    /**
     * create multi polygon by wkt
     *
     * @return
     * @throws ParseException
     */
    public MultiPolygon createMulPolygonByWKT(String wkt) throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
//        "MULTIPOLYGON(((40 10, 30 0, 40 10, 30 20, 40 10),(30 10, 30 0, 40 10, 30 20, 30 10)))"
        MultiPolygon mpolygon = (MultiPolygon) reader.read(wkt);
        return mpolygon;
    }

    /**
     * create GeometryCollection  contain point or multiPoint or line or multiLine or polygon or multiPolygon
     *
     * @return
     * @throws ParseException
     */
    public GeometryCollection createGeoCollect() throws ParseException {
        LineString line = createLine();
//        Polygon poly =  createPolygonByWKT();
        Geometry g1 = geometryFactory.createGeometry(line);
//        Geometry g2 = geometryFactory.createGeometry(poly);
        Geometry[] garray = new Geometry[]{g1};
        GeometryCollection gc = geometryFactory.createGeometryCollection(garray);
        return gc;
    }

    /**
     * create a Circle  创建一个圆，圆心(x,y) 半径RADIUS
     *
     * @param x
     * @param y
     * @param RADIUS
     * @return
     */
    public Polygon createCircle(double x, double y, final double RADIUS) {
        final int SIDES = 32;//圆上面的点个数
        Coordinate coords[] = new Coordinate[SIDES + 1];
        for (int i = 0; i < SIDES; i++) {
            double angle = ((double) i / (double) SIDES) * Math.PI * 2.0;
            double dx = Math.cos(angle) * RADIUS;
            double dy = Math.sin(angle) * RADIUS;
            coords[i] = new Coordinate((double) x + dx, (double) y + dy);
        }
        coords[SIDES] = coords[0];
        LinearRing ring = geometryFactory.createLinearRing(coords);
        Polygon polygon = geometryFactory.createPolygon(ring, null);
        return polygon;
    }

    /**
     * 由wkt格式的geometry生成geojson
     *
     * @param wkt
     * @return
     */
    public static String wktToJson(String wkt) {
        String json = null;
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            StringWriter writer = new StringWriter();
//            GeometryJSON g = new GeometryJSON(20);
//            g.write(geometry, writer);
            json = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * create Geom by wkt
     *
     * @return
     * @throws ParseException
     */
    public Geometry createGeometryByWKT(String wkt) {
        Geometry geometry = null;
        String type = wkt.substring(0, wkt.indexOf("("));
        try {
            switch (type) {
                case "POINT":
                    geometry = createPointByWKT(wkt);
                    break;
                case "MULTIPOINT":
                    geometry = createMulPointByWKT(wkt);
                    break;
                case "LINESTRING":
                    geometry = createLineByWKT(wkt);
                    break;
                case "MULTILINESTRING":
                    geometry = createMLineByWKT(wkt);
                    break;
                case "POLYGON":
                    geometry = createPolygonByWKT(wkt);
                    break;
                case "MULTIPOLYGON":
                    geometry = createMulPolygonByWKT(wkt);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return geometry;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        WktAndGeom gt = new WktAndGeom();
        Polygon p = gt.createCircle(0, 1, 2);
        //圆上所有的坐标(32个)
        Coordinate coords[] = p.getCoordinates();
        for (Coordinate coord : coords) {
            System.out.println(coord.x + "," + coord.y);
        }
    }
}
