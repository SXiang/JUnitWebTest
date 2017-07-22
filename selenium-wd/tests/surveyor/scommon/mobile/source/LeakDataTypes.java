package surveyor.scommon.mobile.source;

public class LeakDataTypes {
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
		Service_T ("Service_T"),
		Service_Branch ("Service_Branch"),
		Riser ("Riser"),
		Meter_Set ("Meter_Set"),
		Customer_Equipment ("Customer_Equipment"),
		Sewer_Manhole ("Sewer_Manhole"),
		Catch_Basin ("Catch_Basin"),
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
		Catch_Basin ("Catch_Basin"),
		Landfill ("Landfill"),
		Swamp ("Swamp"),
		Customer ("Customer"),
		Other_Enclosure ("Other_Enclosure"),
		Other_Natural_Source ("Other_Natural_Source");
		private final String name;

		LeakSourceType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum LeakType {
		Above_Ground ("Above_Ground"),
		Below_Ground ("Below_Ground");
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
		Un_surfaced ("Un_surfaced"),
		Tar_Component ("Tar_Component"),
		In_Substructure ("In_Substructure"),
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
