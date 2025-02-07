package org.jbt;

import java.util.ArrayList;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.core.convert.converter.Converter;

public class GeoJsonConverter implements Converter<String, Geometry[]> {
	private final GeoJsonReader jsonReader = new GeoJsonReader();

	@Override
	public Geometry[] convert(String str) {
		var geometries = new ArrayList<Geometry>();

		try {
			Geometry geometry = jsonReader.read(str);
			if (geometry != null) {
				geometries.add(geometry);
			}
			return geometries.toArray(new Geometry[0]);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
