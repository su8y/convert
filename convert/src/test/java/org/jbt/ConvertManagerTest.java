package org.jbt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.locationtech.proj4j.CRSFactory;

class ConvertManagerTest {

	@Test
	void testConvert() {
		CRSFactory crsFactory = new CRSFactory();
		ConvertManager convertManager = new ConvertManager();
		Object result = convertManager.convert();
		assertNotNull(crsFactory);
		assertNull(result);
	}

}
