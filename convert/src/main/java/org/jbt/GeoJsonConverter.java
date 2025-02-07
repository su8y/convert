package org.jbt;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.springframework.core.convert.converter.Converter;

public class GeoJsonConverter implements Converter<String, Geometry[]> {
	@Override
	public Geometry[] convert(String str) {
		var geoJson = new GeometryJSON();
		var reader = new StringReader(str);
		var geometries = new ArrayList<Geometry>();

		try {
			Geometry geometry = geoJson.read(reader);
			if (geometry != null) {
				geometries.add(geometry);
			}
			return geometries.toArray(new Geometry[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
