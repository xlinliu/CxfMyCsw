package com.csw.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class JTSUtil {
	 private static GeometryFactory geometryFactory = new GeometryFactory();
	 	/**
	 	 * 创建point
	 	 * @param lon
	 	 * @param lat
	 	 * @return
	 	 */
	    public static Point createPoint(Double lon,Double lat){  
	        Coordinate coord = new Coordinate(lon, lat);  
	        Point point = geometryFactory.createPoint( coord );  
	        return point;  
	    }
	    /**
	     * 创建polygon
	     * @param polygonStr
	     * @return
	     * @throws ParseException
	     */
	    public static Polygon createPolygonByWKT(String polygonStr) throws ParseException{  
	        WKTReader reader = new WKTReader( geometryFactory );  
	        Polygon polygon = (Polygon) reader.read(polygonStr);  
	        return polygon;  
	    }  
}
