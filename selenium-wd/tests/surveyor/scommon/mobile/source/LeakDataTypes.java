package surveyor.scommon.mobile.source;

public class LeakDataTypes {
	public enum IndicationType {
		LISA ("LISA"),
		Gap ("Gap");

		private final String name;

		IndicationType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum SourceType {
		Leak ("Leak"),
		OtherSource ("OtherSource");

		private final String name;

		SourceType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum LeakLocationType {
		Main ("Main"),
		Service ("Service"),
		Service_T ("Service T"),
		Service_Branch ("Service Branch"),
		Riser ("Riser"),
		Meter_Set ("Meter Set"),
		Customer_Equipment ("Customer Equipment"),
		Sewer_Manhole ("Sewer Manhole"),
		Catch_Basin ("Catch Basin"),
		Substructure ("Substructure"),
		Other ("Other");
		private final String name;

		LeakLocationType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum LeakSourceType {
		Gas ("Gas"),
		Sewer ("Sewer"),
		Catch_Basin ("Catch Basin"),
		Landfill ("Landfill"),
		Swamp ("Swamp"),
		Customer ("Customer"),
		Other_Enclosure ("Other Enclosure"),
		Other_Natural_Source ("Other Natural Source");
		private final String name;

		LeakSourceType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum LeakType {
		Above_Ground ("Above Ground"),
		Below_Ground ("Below Ground");
		private final String name;

		LeakType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ReadingUnitType {
		None ("None"),
		PPM ("PPM"),
		LEL ("LEL"),
		PctGas ("% Gas");
		private final String name;

		ReadingUnitType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum SurfaceOverLeakType {
		Above_Ground ("Above_Ground"),
		Concrete ("Concrete"),
		Un_surfaced ("Un surfaced"),
		Tar_Component ("Tar Component"),
		In_Substructure ("In Substructure"),
		Other ("Other");
		private final String name;

		SurfaceOverLeakType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum LeakPipeMaterialType {
		CastIron ("Cast Iron"),
		Copper ("Copper"),
		OtherPlastic ("Other Plastic"),
		PEPlastic ("PE Plastic"),
		ProtectedSteel ("Protected Steel"),
		UnprotectedSteel ("Un-protected Steel");

		private final String name;

		LeakPipeMaterialType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}
}