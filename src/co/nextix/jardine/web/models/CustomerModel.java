package co.nextix.jardine.web.models;

import com.google.gson.annotations.SerializedName;

public class CustomerModel {

	// "createdtime": "2014-06-14 15:15:46",
	// "customers_days_unchanged": "15",
	// "modifiedtime": "2014-07-07 15:24:06",
	// "smownerid": "1",
	// "z_area": "NORTHEAST LUZON AREA",
	// "z_city": "ITBAYAT",
	// "z_cu_businessunit": "",
	// "z_cu_chainname": "test",
	// "z_cu_customer": "CUST0000008",
	// "z_cu_customername": "szdcfv",
	// "z_cu_customerrecstat": "Pending For Approval",
	// "z_cu_customersize": "Big",
	// "z_cu_customertype": "Modern Trade",
	// "z_cu_fax": "76543210",
	// "z_cu_isactive": "0",
	// "z_cu_landline": "1234567",
	// "z_cu_streetadd": "sdf",
	// "z_province": "BATANES",
	// "xcustomersid": "405",
	// "deleted": "0"

	@SerializedName("z_cu_customer")
	private String crm_no;

	@SerializedName("z_cu_customername")
	private String name;

	@SerializedName("z_cu_streetadd")
	private String streetadd;

	@SerializedName("z_cu_chainname")
	private String chainname;

	@SerializedName("z_cu_landline")
	private String landline;

	@SerializedName("z_cu_customertype")
	private String type;

	@SerializedName("z_cu_businessunit")
	private String businessunit;

	@SerializedName("z_province")
	private String province;

	@SerializedName("z_city")
	private String city;

	@SerializedName("z_cu_isactive")
	private String isactive;

	@SerializedName("z_cu_fax")
	private String fax;

	@SerializedName("z_area")
	private String area;

	@SerializedName("z_cu_customersize")
	private String customersize;

	@SerializedName("z_cu_customerrecstat")
	private String customerrecstat;

	@SerializedName("xcustomersid")
	private String record_id;

	@SerializedName("record_module")
	private String record_module;

	@SerializedName("createdtime")
	private String created_time;

	@SerializedName("modifiedtime")
	private String modified_time;

	@SerializedName("smownerid")
	private String user_id;

	public String getCrmNo() {
		return this.crm_no;
	}

	public void setCrmNo(String crmNo) {
		this.crm_no = crmNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetadd() {
		return this.streetadd;
	}

	public void setStreetadd(String streetadd) {
		this.streetadd = streetadd;
	}

	public String getChainname() {
		return this.chainname;
	}

	public void setChainname(String chainname) {
		this.chainname = chainname;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusinessunit() {
		return this.businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIsActive() {
		return this.isactive;
	}

	public void setIsActive(String isActive) {
		this.isactive = isActive;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCustomerSize() {
		return this.customersize;
	}

	public void setCustomerSize(String customersize) {
		this.customersize = customersize;
	}

	public String getCustomerRecStat() {
		return this.customerrecstat;
	}

	public void setCustomerRecStat(String customerrecstat) {
		this.customerrecstat = customerrecstat;
	}

	public String getRecordId() {
		return this.record_id;
	}

	public void setRecordId(String recordId) {
		this.record_id = recordId;
	}

	public String getRecordModule() {
		return this.record_module;
	}

	public void setRecordModule(String recordModule) {
		this.record_module = recordModule;
	}

	public String getCreatedTime() {
		return this.created_time;
	}

	public void setCreatedTime(String createdTime) {
		this.created_time = createdTime;
	}

	public String getModifiedTime() {
		return this.modified_time;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String userId) {
		this.user_id = userId;
	}
}
