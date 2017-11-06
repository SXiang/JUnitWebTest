package surveyor.dataaccess.source;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import common.source.BaseHelper;

public class CustomerLicenses {
	public static class License {
		private String licenseFeatureId;
		private String description;

		public License(String licenseFeatureId, String description) {
			this.setLicenseFeatureId(licenseFeatureId);
			this.setDescription(description);
		}

		public String getLicenseFeatureId() {
			return licenseFeatureId;
		}

		public void setLicenseFeatureId(String licenseFeatureId) {
			this.licenseFeatureId = licenseFeatureId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	public List<License> getLicenses(String customerName) {
		List<License> licenses = null;
		if (!BaseHelper.isNullOrEmpty(customerName)) {
			Customer customer = Customer.getCustomer(customerName);
			if (customer != null) {
				String customerId = customer.getId();
				List<CustomerLicensedFeatureOptions> licFeatureOptions = CustomerLicensedFeatureOptions.getCustomerLicensedFeatureOptions(customerId);
				licenses = licFeatureOptions.stream()
					.map(lfo -> LicensedFeatureOptions.getLicensedFeatureOption(lfo.getLicensedFeatureOptionId().toString()))
					.map(fo -> new License(fo.getId().toString(), fo.getDescription()))
					.collect(Collectors.toList());
			}
		}

		return licenses;
	}
}
