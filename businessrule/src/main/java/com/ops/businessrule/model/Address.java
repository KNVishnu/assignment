package com.ops.businessrule.model;

/**
 * Address model
 * @author Vishnu K N
 *
 */
public class Address {

	private Long addressId;
	private String buildingNo;
	private String addrLine;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getAddrLine() {
		return addrLine;
	}
	public void setAddrLine(String addrLine) {
		this.addrLine = addrLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
