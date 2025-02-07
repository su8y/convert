package learn.geotools.datastore;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.geotools.api.data.DataStore;
import org.geotools.api.data.DataStoreFinder;
import org.geotools.api.data.FeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.filter.Filter;
import org.geotools.data.csv.CSVDataStoreFactory;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

@ExtendWith(MockitoExtension.class)
public class DataStoreTest {
	@Test
	public void findFile() throws IOException {
		File file = new ClassPathResource("locations.csv").getFile();

		Map map = new HashMap<>();
		map.put("url", file.toURL());
		assertThat(file.isFile()).isTrue();
		assertThat(file.exists()).isTrue();

		FileInputStream fileInputStream = new FileInputStream(file);
		while (fileInputStream.available() > 0) {
			System.out.print((char)fileInputStream.read());
		}
	}

	@Test
	@DisplayName("Read Csv File")
	void readCsvTest() throws IOException {
		File file = new ClassPathResource("locations.csv").getFile();
		// URL resource = Thread.currentThread().getContextClassLoader().getResource("locations.csv");

		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("file", file.toURL().toString());
		params.put(CSVDataStoreFactory.STRATEGYP.key, CSVDataStoreFactory.SPECIFC_STRATEGY);
		params.put(CSVDataStoreFactory.LATFIELDP.key, "LAT");
		params.put(CSVDataStoreFactory.LnGFIELDP.key, "LON");
		DataStore store = DataStoreFinder.getDataStore(params);

		String typeName = store.getTypeNames()[0];

		FeatureSource<SimpleFeatureType, SimpleFeature> source =
				store.getFeatureSource(typeName);
		Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM, 10,20,30,40)")

		FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);

		BigDecimal count = new BigDecimal(0);

		try (FeatureIterator<SimpleFeature> features = collection.features()) {
			while (features.hasNext()) {
				count = count.add(BigDecimal.ONE);
				features.next();
			}
		}
		Assertions.assertThat(collection.size()).isEqualTo(16);
	}
}
