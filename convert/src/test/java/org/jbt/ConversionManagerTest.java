package org.jbt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.locationtech.proj4j.CRSFactory;

class ConversionManagerTest {

	@Test
	void testConvert() {
		CRSFactory crsFactory = new CRSFactory();
		ConversionManager conversionManager = new ConversionManager();
		Object result = conversionManager.convert();
		assertNotNull(crsFactory);
		assertNull(result);
	}

}
