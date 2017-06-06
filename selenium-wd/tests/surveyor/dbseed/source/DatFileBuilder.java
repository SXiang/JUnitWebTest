package surveyor.dbseed.source;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections.keyvalue.DefaultKeyValue;

import common.source.FileUtility;
import common.source.RegexUtility;
import common.source.UnicodeChars;
import surveyor.dataaccess.source.CustomerBoundaryType;
import surveyor.dataaccess.source.CustomerMaterialType;

public class DatFileBuilder {

	private String datFilePath = null;
	private List<String> cleanupFilesList = null;

	public DatFileBuilder() {
		cleanupFilesList = new ArrayList<String>();
	}

	public String build(String originalDatFile, String existingCustomerId, String newCustomerId) throws IOException {
		setDatFilePath(originalDatFile.replace(".dat", String.format("-%s.dat", newCustomerId)));

		FileUtility.copyFile(originalDatFile, getDatFilePath());
		Hashtable<String, String> placeholderMap = new Hashtable<String, String>();
		placeholderMap.put(existingCustomerId, newCustomerId);
		replaceAssetFKCols(placeholderMap, existingCustomerId, newCustomerId);
		replaceBoundaryFKCols(placeholderMap, existingCustomerId, newCustomerId);

		FileUtility.updateFile(getDatFilePath(), placeholderMap, FileUtility.ENCODING_UTF16LE,
				(line) -> updateLineWithNewGuidForId(line));

		cleanupFilesList.add(getDatFilePath());
		return getDatFilePath();
	}

	public void close() throws IOException {
		executeCleanup();
	}

	private void executeCleanup() {
		if (cleanupFilesList != null) {
			cleanupFilesList.forEach(fEntry -> {
				if (FileUtility.fileExists(fEntry)) {
					FileUtility.deleteFile(Paths.get(fEntry));
				}
			});
		}
	}

	public String getDatFilePath() {
		return datFilePath;
	}

	public void setDatFilePath(String datFilePath) {
		this.datFilePath = datFilePath;
	}

	private void replaceAssetFKCols(Hashtable<String, String> placeholderMap, String existingCustomerId, String newCustomerId) {
		CustomerMaterialType custMaterialType = new CustomerMaterialType();
		List<CustomerMaterialType> existingCustMT = custMaterialType.getAllForCustomer(existingCustomerId);
		List<CustomerMaterialType> newCustMT = custMaterialType.getAllForCustomer(newCustomerId);
		existingCustMT.stream()
			.map(cmt -> new DefaultKeyValue(cmt.getId(), getMatchingCustomerMaterialTypeId(newCustMT, cmt.getDescription())))
			.collect(Collectors.toList())
			.forEach(p -> {
				if (p.getKey() != null && p.getValue() != null) {
					placeholderMap.put(String.valueOf(p.getKey()), String.valueOf(p.getValue()));
				}
			});
	}

	private void replaceBoundaryFKCols(Hashtable<String, String> placeholderMap, String existingCustomerId, String newCustomerId) {
		CustomerBoundaryType custBoundaryType = new CustomerBoundaryType();
		List<CustomerBoundaryType> existingCustMT = custBoundaryType.getAllForCustomer(existingCustomerId);
		List<CustomerBoundaryType> newCustMT = custBoundaryType.getAllForCustomer(newCustomerId);
		existingCustMT.stream()
			.map(cbt -> new DefaultKeyValue(cbt.getId(), getMatchingCustomerBoundaryTypeId(newCustMT, cbt.getFeatureClassDescription())))
			.collect(Collectors.toList())
			.forEach(p -> {
				if (p.getKey() != null && p.getValue() != null) {
					placeholderMap.put(String.valueOf(p.getKey()), String.valueOf(p.getValue()));
				}
			});
	}

	private String getMatchingCustomerMaterialTypeId(List<CustomerMaterialType> customerMaterialTypeList, String description) {
		return customerMaterialTypeList.stream()
			.filter(cmt -> cmt.getDescription().equals(description))
			.map(cmt -> cmt.getId())
			.findFirst().orElse(null);
	}

	private String getMatchingCustomerBoundaryTypeId(List<CustomerBoundaryType> customerBoundaryTypeList, String featureClassDesc) {
		return customerBoundaryTypeList.stream()
			.filter(cbt -> cbt.getFeatureClassDescription().equals(featureClassDesc))
			.map(cbt -> cbt.getId())
			.findFirst().orElse(null);
	}

	private String updateLineWithNewGuidForId(String line) {
		StringBuilder builder = new StringBuilder();
		List<String> lineParts = RegexUtility.split(line, RegexUtility.TAB_SPLIT_REGEX_PATTERN);
		if (lineParts != null && lineParts.size() > 0) {
			builder.append(UUID.randomUUID().toString().toUpperCase());
			if (lineParts.size() > 1) {
				for (int i = 1; i < lineParts.size(); i++) {
					builder.append(UnicodeChars.tab());
					builder.append(lineParts.get(i));
				}
			}
		}

		return builder.toString();
	}
}