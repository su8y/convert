package learn.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

public class CrsProjectionTest {

	@Test
	@DisplayName("Test Crs Projection")
	void testCrsProjection() {
		CRSFactory crsFactory = new CRSFactory();
		CoordinateReferenceSystem wgs84 = crsFactory.createFromName("epsg:4326");
		Assertions.assertEquals("epsg:4326", wgs84.getName());
	}

	@Test
	@DisplayName("Test Transform Projection")
	void testTransformProjection() {
		CRSFactory crsFactory = new CRSFactory();
		CoordinateReferenceSystem wgs84 = crsFactory.createFromName("epsg:4326");
		CoordinateReferenceSystem target = crsFactory.createFromName("epsg:3857");
		CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
		CoordinateTransform transform = ctFactory.createTransform(wgs84, target);
		Coordinate coordinate = new Coordinate(127.123456, 35.123456);

		ProjCoordinate src = new ProjCoordinate(coordinate.x, coordinate.y);
		ProjCoordinate result = new ProjCoordinate();
		transform.transform(src, result);

		Assertions.assertEquals(14151318.389801119, result.x, 0.0000001);
		Assertions.assertEquals(4180671.0033380566, result.y, 0.0000001);

	}

}
