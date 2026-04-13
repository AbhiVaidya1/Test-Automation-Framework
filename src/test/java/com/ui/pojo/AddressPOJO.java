package com.ui.pojo;

public class AddressPOJO {
	private String company;
	private String adressLine1;
	private String adressLine2;
	private String city;
	private String postcode;
	private String homePhoneNumber;
	private String mobileNumber;
	private String otherInformation;
	private String addressAlias;
	private String state;
	
	
	public AddressPOJO(String company, String adressLine1, String adressLine2, String city, String postcode,
			String homePhoneNumber, String mobileNumber, String otherInformation, String addressAlias, String state) {
		super();
		this.company = company;
		this.adressLine1 = adressLine1;
		this.adressLine2 = adressLine2;
		this.city = city;
		this.postcode = postcode;
		this.homePhoneNumber = homePhoneNumber;
		this.mobileNumber = mobileNumber;
		this.otherInformation = otherInformation;
		this.addressAlias = addressAlias;
		this.state = state;
	}
	
	public String getCompany() {
		return company;
	}

	public String getAdressLine1() {
		return adressLine1;
	}

	public String getAdressLine2() {
		return adressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getOtherInformation() {
		return otherInformation;
	}

	public String getAddressAlias() {
		return addressAlias;
	}

	public String getState() {
		return state;
	}
	
	@Override
	public String toString() {
		return "AddressPOJO [company=" + company + ", adressLine1=" + adressLine1 + ", adressLine2=" + adressLine2
				+ ", city=" + city + ", postcode=" + postcode + ", homePhoneNumber=" + homePhoneNumber
				+ ", mobileNumber=" + mobileNumber + ", otherInformation=" + otherInformation + ", addressAlias="
				+ addressAlias + ", state=" + state + "]";
	}
	
}
