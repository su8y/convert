package org.jbt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

class GeoJsonConverterTest {

	GeoJsonConverter converter;

	@BeforeEach
	void setUp() {
		converter = new GeoJsonConverter();
	}

	@Test
	@DisplayName("Point 변환 테스트")
	void pointConvert() {
		Geometry[] convert = converter.convert("{\"type\":\"Point\",\"coordinates\":[1.0,2.0]}");

		Point point = (Point)convert[0];
		assertEquals(1, convert.length);
		assertEquals("POINT (1 2)", point.toString());
		assertEquals(1, point.getCoordinate().getX(), 0.1);
		assertEquals(2, point.getCoordinate().getY(), 0.1);

	}

	@Test
	@DisplayName("line string 변환 테스트")
	void lineStringConvertTest() {
		Geometry[] convert = converter.convert("{\"type\":\"LineString\",\"coordinates\":[[1.0,2.0],[3.0,4.0]]}");

		LineString lineString = (LineString)convert[0];
		assertEquals(1, convert.length);
		assertEquals("LINESTRING (1 2, 3 4)", lineString.toString());
	}

	@Test
	@DisplayName("GeometryCollection 변환 테스트")
	void geometryCollectionConvertTest() {
		String geoJson = """
				{
					"type": "GeometryCollection",
					"geometries": [
						{
							"type": "Point",
							"coordinates": [1.0, 2.0]
						},
						{
							"type": "LineString",
							"coordinates": [[3.0, 4.0], [5.0, 6.0]]
						}
					]
				}
				""";

		Geometry[] convert = converter.convert(geoJson);

		assertEquals(1, convert.length);
		assertEquals("GeometryCollection", convert[0].getGeometryType());

		GeometryCollection collection = (GeometryCollection)convert[0];
		assertEquals(2, collection.getNumGeometries());

		Point point = (Point)collection.getGeometryN(0);
		assertEquals("POINT (1 2)", point.toString());

		LineString lineString = (LineString)collection.getGeometryN(1);
		assertEquals("LINESTRING (3 4, 5 6)", lineString.toString());
	}

	/**
	 * NOTE: GeoJsonConverter로는 Feature와 FeatureCollection을 변환할 수 없다.
	 * Feature와 FeatureCollection은 GeoTools의 FeatureJSON을 사용해야 한다.
	 *
	 * GeoJsonReader로 읽을시에는 Feature가 Geometry로 읽혀서 변환된다.
	 * 이것은 즉 Property를 무시하고 Geometry만 변환한다는 것이다. */
	@Test
	@DisplayName("Point 좌표 투영 가져오는지 테스트")
	void getProjectionTest() {
		// @DisplayName("Point 좌표 투영 가져오는지 테스트")
		String geoJson = """
				{
					"type": "FeatureCollection",
					"crs": {
						"type": "name",
						"properties": {
						"name": "EPSG:3857"
					}
					},
					"features": [
						{
							"type": "Feature",
							"geometry": {
								"type": "Point",
								"coordinates": [1.0, 2.0]
							},
							"properties": {}
						},
						{
							"type": "Feature",
							"geometry": {
								"type": "LineString",
								"coordinates": [[1.0, 2.0],[3.0, 4.0]]
							},
							"properties": {}
						}
					]
				}
				""";

		Geometry[] convert = converter.convert(geoJson);
		Assertions.assertTrue(convert.length == 1);

		GeometryCollection geometryCollection = (GeometryCollection)convert[0];
		Assertions.assertEquals(2, geometryCollection.getNumGeometries());
		Assertions.assertEquals(3857, geometryCollection.getSRID());
	}
}
