package jbtgeoconvert.converter;

public class CsvToGeoJsonConverter {
	/* void test() {

		String inputCsvPath = "path/to/input.csv";
		String outputGeoJsonPath = "path/to/output.geojson";

		// CSV 파일에서 추출할 위도와 경도 컬럼 이름
		String latitudeColumn = "latitude";  // 예: CSV 파일의 위도 컬럼 이름
		String longitudeColumn = "longitude"; // 예: CSV 파일의 경도 컬럼 이름

		// GDAL/OGR 초기화
		ogr.RegisterAll();

		// 드라이버 가져오기
		Driver csvDriver = ogr.GetDriverByName("CSV");
		if (csvDriver == null) {
			System.err.println("CSV 드라이버를 찾을 수 없습니다.");
			return;
		}

		// CSV 데이터를 읽기
		DataSource csvDataSource = csvDriver.Open(inputCsvPath, 0); // 0: 읽기 모드
		if (csvDataSource == null) {
			System.err.println("CSV 파일을 열 수 없습니다: " + inputCsvPath);
			return;
		}

		// 레이어 가져오기
		Layer csvLayer = csvDataSource.GetLayer(0);
		if (csvLayer == null) {
			System.err.println("CSV 레이어를 가져올 수 없습니다.");
			return;
		}

		// 출력 드라이버 가져오기
		Driver geoJsonDriver = ogr.GetDriverByName("GeoJSON");
		if (geoJsonDriver == null) {
			System.err.println("GeoJSON 드라이버를 찾을 수 없습니다.");
			return;
		}

		// 출력 데이터 소스 생성
		DataSource geoJsonDataSource = geoJsonDriver.CreateDataSource(outputGeoJsonPath);
		if (geoJsonDataSource == null) {
			System.err.println("GeoJSON 데이터를 저장할 수 없습니다: " + outputGeoJsonPath);
			return;
		}

		// 공간 참조 설정 (WGS84 좌표계 사용)
		SpatialReference srs = new SpatialReference();
		srs.ImportFromEPSG(4326); // EPSG:4326은 WGS84 좌표계

		// 출력 레이어 생성
		Layer geoJsonLayer = geoJsonDataSource.CreateLayer("output", srs, ogr.wkbPoint);
		if (geoJsonLayer == null) {
			System.err.println("GeoJSON 레이어를 생성할 수 없습니다.");
			return;
		}

		// CSV 레이어의 속성 정의를 GeoJSON에 복사
		FeatureDefn csvFeatureDef = csvLayer.GetLayerDefn();
		for (int i = 0; i < csvFeatureDef.GetFieldCount(); i++) {
			FieldDefn fieldDefn = csvFeatureDef.GetFieldDefn(i);
			geoJsonLayer.CreateField(fieldDefn);
		}

		// CSV 데이터를 읽어 GeoJSON으로 변환
		Feature csvFeature;
		while ((csvFeature = csvLayer.GetNextFeature()) != null) {
			// 위도와 경도를 읽어서 포인트 생성
			double latitude = csvFeature.GetFieldAsDouble(latitudeColumn);
			double longitude = csvFeature.GetFieldAsDouble(longitudeColumn);
			Geometry point = Geometry.CreateFromWkt(String.format("POINT (%f %f)", longitude, latitude));

			// GeoJSON 피처 생성 및 속성 복사
			Feature geoJsonFeature = new Feature(geoJsonLayer.GetLayerDefn());
			geoJsonFeature.SetGeometry(point);
			for (int i = 0; i < csvFeatureDef.GetFieldCount(); i++) {
				geoJsonFeature.SetField(csvFeatureDef.GetFieldDefn(i).GetNameRef(),
						csvFeature.GetFieldAsString(i));
			}

			// 레이어에 피처 추가
			geoJsonLayer.CreateFeature(geoJsonFeature);

			// 리소스 정리
			geoJsonFeature.delete();
			csvFeature.delete();
		}

		// 데이터 소스 정리
		csvDataSource.delete();
		geoJsonDataSource.delete();

		System.out.println("CSV 파일이 GeoJSON으로 변환되었습니다: " + outputGeoJsonPath);
	}
	 */
}
