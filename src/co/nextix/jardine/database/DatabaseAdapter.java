package co.nextix.jardine.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ActivityTypeTable;
import co.nextix.jardine.database.tables.BusinessUnitTable;
import co.nextix.jardine.database.tables.CalendarTable;
import co.nextix.jardine.database.tables.CompetitorProductStockCheckTable;
import co.nextix.jardine.database.tables.CompetitorProductTable;
import co.nextix.jardine.database.tables.CustomerContactTable;
import co.nextix.jardine.database.tables.CustomerTable;
import co.nextix.jardine.database.tables.DocumentTable;
import co.nextix.jardine.database.tables.EventProtocolTable;
import co.nextix.jardine.database.tables.JDImerchandisingCheckTable;
import co.nextix.jardine.database.tables.JDIproductStockCheckTable;
import co.nextix.jardine.database.tables.MarketingIntelTable;
import co.nextix.jardine.database.tables.MarketingMaterialsTable;
import co.nextix.jardine.database.tables.ProductFocusTable;
import co.nextix.jardine.database.tables.ProductSupplierTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.ProjectRequirementTable;
import co.nextix.jardine.database.tables.SMRTable;
import co.nextix.jardine.database.tables.SalesProtocolTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.WorkplanEntryTable;
import co.nextix.jardine.database.tables.WorkplanTable;
import co.nextix.jardine.database.tables.picklists.PActProjCategoryTable;
import co.nextix.jardine.database.tables.picklists.PActProjStageTable;
import co.nextix.jardine.database.tables.picklists.PActtypeCategoryTable;
import co.nextix.jardine.database.tables.picklists.PAreaTable;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PComptProdStockStatusTable;
import co.nextix.jardine.database.tables.picklists.PCustConPositionTable;
import co.nextix.jardine.database.tables.picklists.PCustRecordStatusTable;
import co.nextix.jardine.database.tables.picklists.PCustSizeTable;
import co.nextix.jardine.database.tables.picklists.PCustTypeTable;
import co.nextix.jardine.database.tables.picklists.PEventTypeTable;
import co.nextix.jardine.database.tables.picklists.PJDImerchCheckStatusTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;
import co.nextix.jardine.database.tables.picklists.PProjReqTypeTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.database.tables.picklists.PSMRentryTypeTable;
import co.nextix.jardine.database.tables.picklists.PSalesProtocolTypeTable;
import co.nextix.jardine.database.tables.picklists.PWorkEntryStatusTable;
import co.nextix.jardine.database.tables.picklists.PactEndUserActTypeTable;

public class DatabaseAdapter {

	// ===========================================================
	// Private static field
	// ===========================================================

	private static DatabaseAdapter sInstance;

	private static final String DATABASE_NAME = "jardine_database";
	private static final int DATABASE_VERSION = 1;

	// User
	public static final String KEY_USER_ROWID = "_id";
	public static final String KEY_USER_NO = "no";
	public static final String KEY_USER_USERNAME = "username";
	public static final String KEY_USER_PASSWORD = "password";
	public static final String KEY_USER_EMAILADDRESS = "email_address";
	public static final String KEY_USER_LASTNAME = "last_name";
	public static final String KEY_USER_MIDDLENAME = "middle_name";
	public static final String KEY_USER_FIRSTNAME = "first_name";
	public static final String KEY_USER_LOGGEDIN = "logged_in";
	public static final String KEY_USER_STATUS = "status";
	public static final String KEY_USER_LASTSYNC = "last_sync";
	public static final String KEY_USER_AREA = "area";
	public static final String KEY_USER_LOGGEDAREA = "logged_area";
	public static final String KEY_USER_CREATEDTIME = "created_time";

	// Activity
	public static final String KEY_ACTIVITY_ROWID = "_id";
	public static final String KEY_ACTIVITY_NO = "no";
	public static final String KEY_ACTIVITY_CRMNO = "crm_no";
	public static final String KEY_ACTIVITY_ACTIVITYTYPE = "activity_type";
	public static final String KEY_ACTIVITY_CHECKIN = "check_in";
	public static final String KEY_ACTIVITY_CHECKOUT = "check_out";
	public static final String KEY_ACTIVITY_BUSINESSUNIT = "business_unit";
	public static final String KEY_ACTIVITY_CREATEDBY = "created_by";
	public static final String KEY_ACTIVITY_LONGITUDE = "longitude";
	public static final String KEY_ACTIVITY_LATITUDE = "latitude";
	public static final String KEY_ACTIVITY_CREATEDTIME = "created_time";
	public static final String KEY_ACTIVITY_MODIFIEDTIME = "modified_time";
	public static final String KEY_ACTIVITY_REASONREMARKS = "reason_remarks";
	public static final String KEY_ACTIVITY_SMR = "smr";
	public static final String KEY_ACTIVITY_ADMINWORKDETAILS = "admin_work_details";
	public static final String KEY_ACTIVITY_CUSTOMER = "customer";
	public static final String KEY_ACTIVITY_AREA = "area";
	public static final String KEY_ACTIVITY_PROVINCE = "province";
	public static final String KEY_ACTIVITY_CITY = "city";
	public static final String KEY_ACTIVITY_WORKPLANENTRY = "workplan_entry";
	public static final String KEY_ACTIVITY_OBJECTIVES = "objectives";
	public static final String KEY_ACTIVITY_FIRSTTIMEVISIT = "first_time_visit";
	public static final String KEY_ACTIVITY_PLANNEDVISIT = "planned_visit";
	public static final String KEY_ACTIVITY_NOTES = "notes";
	public static final String KEY_ACTIVITY_HIGHLIGHTS = "highlights";
	public static final String KEY_ACTIVITY_NEXTSTEPS = "next_steps";
	public static final String KEY_ACTIVITY_FOLLOWUP = "follow_up_commitment_date";
	public static final String KEY_ACTIVITY_PROJECTNAME = "project_name";
	public static final String KEY_ACTIVITY_PROJECTSTAGE = "project_stage";
	public static final String KEY_ACTIVITY_PROJECTCATEGORY = "project_category";
	public static final String KEY_ACTIVITY_VENUE = "venue";
	public static final String KEY_ACTIVITY_NOOFATTENDEES = "no_of_attendees";
	public static final String KEY_ACTIVITY_ENDUSERACTIVITYTYPES = "end_user_activity_types";

	// Activity Type
	public static final String KEY_ACTIVITYTYPE_ROWID = "_id";
	public static final String KEY_ACTIVITYTYPE_NO = "no";
	// public static final String KEY_ACTIVITYTYPE_TYPE = "activity_type";
	public static final String KEY_ACTIVITYTYPE_CRMNO = "crm_no";
	public static final String KEY_ACTIVITYTYPE_NAME = "name";
	public static final String KEY_ACTIVITYTYPE_CATEGORY = "activity_type_categorization";
	public static final String KEY_ACTIVITYTYPE_ISACTIVE = "is_active";
	public static final String KEY_ACTIVITYTYPE_CREATEDTIME = "created_time";
	public static final String KEY_ACTIVITYTYPE_MODIFIEDTIME = "modified_time";
	public static final String KEY_ACTIVITYTYPE_USER = "user";

	// Business Unit
	public static final String KEY_BUSINESSUNIT_ROWID = "_id";
	public static final String KEY_BUSINESSUNIT_NO = "no";
	public static final String KEY_BUSINESSUNIT_CRMNO = "crm_no";
	public static final String KEY_BUSINESSUNIT_NAME = "business_unit_name";
	public static final String KEY_BUSINESSUNIT_CODE = "business_unit_code";
	public static final String KEY_BUSINESSUNIT_ISACTIVE = "is_active";
	public static final String KEY_BUSINESSUNIT_CREATEDTIME = "created_time";
	public static final String KEY_BUSINESSUNIT_MODIFIEDTIME = "modified_time";
	public static final String KEY_BUSINESSUNIT_CREATED_BY = "created_by";

	// Competitor Product Stock Check
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID = "_id";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_NO = "no";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_CRMNO = "crm_no";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY = "activity";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT = "competitor_product";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS = "stock_status";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES = "loaded_on_shelves";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME = "created_time";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME = "modified_time";
	public static final String KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDBY = "created_by";

	// Competitor Product
	public static final String KEY_COMPETITORPRODUCT_ROWID = "_id";
	public static final String KEY_COMPETITORPRODUCT_NO = "no";
	public static final String KEY_COMPETITORPRODUCT_CRMNO = "crm_no";
	public static final String KEY_COMPETITORPRODUCT_COMPETITOR = "competitor";
	public static final String KEY_COMPETITORPRODUCT_PRODUCTBRAND = "product_brand";
	public static final String KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION = "product_description";
	public static final String KEY_COMPETITORPRODUCT_PRODUCTSIZE = "product_size";
	public static final String KEY_COMPETITORPRODUCT_ISACTIVE = "is_active";
	public static final String KEY_COMPETITORPRODUCT_CREATEDTIME = "created_time";
	public static final String KEY_COMPETITORPRODUCT_MODIFIEDTIME = "modified_time";
	public static final String KEY_COMPETITORPRODUCT_CREATEDBY = "created_by";

	// // Competitor
	// public static final String KEY_COMPETITOR_ROWID = "_id";
	// public static final String KEY_COMPETITOR_NO = "no";
	// public static final String KEY_COMPETITOR_CRMNO = "crm_no";
	// public static final String KEY_COMPETITOR_NAME = "competitor_name";
	// public static final String KEY_COMPETITOR_ISACTIVE = "is_active";
	// public static final String KEY_COMPETITOR_CREATEDTIME = "created_time";
	// public static final String KEY_COMPETITOR_MODIFIEDTIME = "modified_time";
	// public static final String KEY_COMPETITOR_USER = "user";

	// Customer Contact
	public static final String KEY_CUSTOMERCONTACT_ROWID = "_id";
	public static final String KEY_CUSTOMERCONTACT_NO = "no";
	public static final String KEY_CUSTOMERCONTACT_CRMNO = "crm_no";
	public static final String KEY_CUSTOMERCONTACT_FIRSTNAME = "first_name";
	public static final String KEY_CUSTOMERCONTACT_LASTNAME = "last_name";
	public static final String KEY_CUSTOMERCONTACT_POSITION = "position";
	public static final String KEY_CUSTOMERCONTACT_MOBILENO = "mobile_no";
	public static final String KEY_CUSTOMERCONTACT_BIRTHDAY = "birthday";
	public static final String KEY_CUSTOMERCONTACT_EMAIL = "email_address";
	public static final String KEY_CUSTOMERCONTACT_CUSTOMER = "customer";
	public static final String KEY_CUSTOMERCONTACT_ISACTIVE = "is_active";
	public static final String KEY_CUSTOMERCONTACT_CREATEDTIME = "created_time";
	public static final String KEY_CUSTOMERCONTACT_MODIFIEDTIME = "modified_time";
	public static final String KEY_CUSTOMERCONTACT_CREATEDBY = "created_by";

	// Customer
	public static final String KEY_CUSTOMER_ROWID = "_id";
	public static final String KEY_CUSTOMER_NO = "no";
	public static final String KEY_CUSTOMER_CRMNO = "crm_no";
	public static final String KEY_CUSTOMER_NAME = "customer_name";
	public static final String KEY_CUSTOMER_CHAINNAME = "chain_name";
	public static final String KEY_CUSTOMER_LANDLINE = "landline";
	public static final String KEY_CUSTOMER_FAX = "fax";
	public static final String KEY_CUSTOMER_SIZE = "customer_size";
	public static final String KEY_CUSTOMER_STREETADDRESS = "street_address";
	public static final String KEY_CUSTOMER_RECORDSTATUS = "customer_record_status";
	public static final String KEY_CUSTOMER_TYPE = "customer_type";
	public static final String KEY_CUSTOMER_BUSINESSUNIT = "business_unit";
	public static final String KEY_CUSTOMER_AREA = "area";
	public static final String KEY_CUSTOMER_PROVINCE = "province";
	public static final String KEY_CUSTOMER_CITYTOWN = "city_town";
	public static final String KEY_CUSTOMER_ISACTIVE = "is_active";
	public static final String KEY_CUSTOMER_CREATEDTIME = "created_time";
	public static final String KEY_CUSTOMER_MODIFIEDTIME = "modified_time";
	public static final String KEY_CUSTOMER_CREATED_BY = "created_by";

	// Event Protocol
	public static final String KEY_EVENTPROTOCOL_ROWID = "_id";
	public static final String KEY_EVENTPROTOCOL_NO = "no";
	public static final String KEY_EVENTPROTOCOL_DESCRIPTION = "description";
	public static final String KEY_EVENTPROTOCOL_LASTUPDATE = "last_update";
	public static final String KEY_EVENTPROTOCOL_TAGS = "tags";
	public static final String KEY_EVENTPROTOCOL_EVENTTYPE = "event_type";
	public static final String KEY_EVENTPROTOCOL_ISACTIVE = "is_active";
	public static final String KEY_EVENTPROTOCOL_CREATEDTIME = "created_time";
	public static final String KEY_EVENTPROTOCOL_MODIFIEDTIME = "modified_time";
	public static final String KEY_EVENTPROTOCOL_USER = "user";
	public static final String KEY_EVENTPROTOCOL_CRMNO = "crm_no";

	// JDI Product Stock Check
	public static final String KEY_JDIPRODUCTSTOCKCHECK_ROWID = "_id";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_NO = "no";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_CRMNO = "crm_no";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY = "activity";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_PRODUCTBRAND = "product_brand";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS = "stockstatus";
	// public static final String KEY_JDIPRODUCTSTOCKCHECK_QUANTITY =
	// "quantity";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES = "loadedonshelves";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER = "supplier";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_OTHERREMARKS = "other_remarks";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME = "created_time";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME = "modified_time";
	public static final String KEY_JDIPRODUCTSTOCKCHECK_CREATEDBY = "created_by";

	// Marketing Intel
	public static final String KEY_MARKETINGINTEL_ROWID = "_id";
	public static final String KEY_MARKETINGINTEL_NO = "no";
	public static final String KEY_MARKETINGINTEL_CRMNO = "crm_no";
	public static final String KEY_MARKETINGINTEL_ACTIVITY = "activity";
	// public static final String KEY_MARKETINGINTEL_COMPETITOR = "competitor";
	public static final String KEY_MARKETINGINTEL_COMPETITORPRODUCT = "competitor_product";
	public static final String KEY_MARKETINGINTEL_DETAILS = "details";
	// public static final String KEY_MARKETINGINTEL_REMARKS = "remarks";
	public static final String KEY_MARKETINGINTEL_CREATEDTIME = "created_time";
	public static final String KEY_MARKETINGINTEL_MODIFIEDTIME = "modified_time";
	public static final String KEY_MARKETINGINTEL_CREATEDBY = "created_by";

	// Product
	public static final String KEY_PRODUCT_ROWID = "_id";
	public static final String KEY_PRODUCT_NO = "no";
	public static final String KEY_PRODUCT_CRMNO = "crm_no";
	public static final String KEY_PRODUCT_NUMBER = "product_number";
	public static final String KEY_PRODUCT_BRAND = "product_brand";
	public static final String KEY_PRODUCT_DESCRIPTION = "product_description";
	public static final String KEY_PRODUCT_PACKSIZE = "pack_size";
	public static final String KEY_PRODUCT_BUSINESSUNIT = "business_unit";
	public static final String KEY_PRODUCT_ISACTIVE = "is_active";
	public static final String KEY_PRODUCT_CREATEDTIME = "created_time";
	public static final String KEY_PRODUCT_MODIFIEDTIME = "modified_time";
	public static final String KEY_PRODUCT_CREATEDBY = "created_by";

	// Project Requirement
	public static final String KEY_PROJECTREQUIREMENTS_ROWID = "_id";
	public static final String KEY_PROJECTREQUIREMENTS_NO = "no";
	public static final String KEY_PROJECTREQUIREMENTS_CRMNO = "crm_no";
	public static final String KEY_PROJECTREQUIREMENTS_ACTIVITY = "activity";
	public static final String KEY_PROJECTREQUIREMENTS_TYPE = "project_requirement_type";
	public static final String KEY_PROJECTREQUIREMENTS_DATENEEDED = "date_needed";
	public static final String KEY_PROJECTREQUIREMENTS_SQUAREMETERS = "square_meters";
	// public static final String KEY_PROJECTREQUIREMENTS_PRODUCTSUSED =
	// "products_used";
	public static final String KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND = "products_brand";
	public static final String KEY_PROJECTREQUIREMENTS_OTHERDETAILS = "other_details";
	public static final String KEY_PROJECTREQUIREMENTS_CREATEDTIME = "created_time";
	public static final String KEY_PROJECTREQUIREMENTS_MODIFIEDTIME = "modified_time";
	public static final String KEY_PROJECTREQUIREMENTS_CREATEDBY = "created_by";

	// SMR
	public static final String KEY_SMR_ROWID = "_id";
	public static final String KEY_SMR_NO = "no";
	public static final String KEY_SMR_CRMNO = "crm_no";
	public static final String KEY_SMR_FIRSTNAME = "firstname";
	public static final String KEY_SMR_LASTNAME = "lastname";
	// public static final String KEY_SMR_REGION = "region";
	public static final String KEY_SMR_AREA = "area";
	public static final String KEY_SMR_ISACTIVE = "is_active";
	public static final String KEY_SMR_BUSINESS_UNIT = "business_unit";
	public static final String KEY_SMR_CREATEDTIME = "created_time";
	public static final String KEY_SMR_MODIFIEDTIME = "modified_time";
	public static final String KEY_SMR_CREATED_BY = "created_by";

	// // SMR Time Card
	// public static final String KEY_SMRTIMECARD_ROWID = "_id";
	// public static final String KEY_SMRTIMECARD_NO = "no";
	// public static final String KEY_SMRTIMECARD_DATE = "date";
	// public static final String KEY_SMRTIMECARD_TIMESTAMP = "timestamp";
	// public static final String KEY_SMRTIMECARD_ENTRYTYPE = "entry_type";
	// public static final String KEY_SMRTIMECARD_CREATEDTIME = "created_time";
	// public static final String KEY_SMRTIMECARD_MODIFIEDTIME =
	// "modified_time";
	// public static final String KEY_SMRTIMECARD_USER = "user";
	// public static final String KEY_SMRTIMECARD_CRMNO = "crm_no";

	// // Supplier
	// public static final String KEY_SUPPLIER_ROWID = "_id";
	// public static final String KEY_SUPPLIER_NO = "no";
	// public static final String KEY_SUPPLIER_NAME = "supplier_name";
	// public static final String KEY_SUPPLIER_LANDLINE = "supplier_landline";
	// public static final String KEY_SUPPLIER_ADDRESS = "supplier_address";
	// public static final String KEY_SUPPLIER_CREDITLINE = "credit_line";
	// public static final String KEY_SUPPLIER_CREDITTERM = "credit_term";
	// public static final String KEY_SUPPLIER_CONTACTPERSON = "contact_person";
	// public static final String KEY_SUPPLIER_ISACTIVE = "is_active";
	// public static final String KEY_SUPPLIER_CREATEDTIME = "created_time";
	// public static final String KEY_SUPPLIER_MODIFIEDTIME = "modified_time";
	// public static final String KEY_SUPPLIER_USER = "user";
	// public static final String KEY_SUPPLIER_CRMNO = "crm_no";

	// Workplan Entry
	public static final String KEY_WORKPLANENTRY_ROWID = "_id";
	public static final String KEY_WORKPLANENTRY_NO = "no";
	public static final String KEY_WORKPLANENTRY_CRMNO = "crm_no";
	// public static final String KEY_WORKPLANENTRY_CUSTOMER = "customer";
	public static final String KEY_WORKPLANENTRY_DATE = "date";
	public static final String KEY_WORKPLANENTRY_STATUS = "status";
	public static final String KEY_WORKPLANENTRY_AREA = "area";
	public static final String KEY_WORKPLANENTRY_PROVINCE = "province";
	public static final String KEY_WORKPLANENTRY_CITY = "city";
	public static final String KEY_WORKPLANENTRY_ACTIVITYTYPE = "activity_type";
	public static final String KEY_WORKPLANENTRY_REMARKS = "remarks";
	public static final String KEY_WORKPLANENTRY_WORKPLAN = "workplan";
	public static final String KEY_WORKPLANENTRY_ACTIVITYQUANTITY = "activity_quantity";
	public static final String KEY_WORKPLANENTRY_BUSINESSUNIT = "business_unit";
	public static final String KEY_WORKPLANENTRY_CREATEDTIME = "created_time";
	public static final String KEY_WORKPLANENTRY_MODIFIEDTIME = "modified_time";
	public static final String KEY_WORKPLANENTRY_CREATEDBY = "created_by";

	// Workplan
	public static final String KEY_WORKPLAN_ROWID = "_id";
	public static final String KEY_WORKPLAN_NO = "no";
	public static final String KEY_WORKPLAN_CRMNO = "crm_no";
	public static final String KEY_WORKPLAN_FROMDATE = "from_date";
	public static final String KEY_WORKPLAN_TODATE = "to_date";
	// public static final String KEY_WORKPLAN_STATUS = "status"; removed
	public static final String KEY_WORKPLAN_CREATEDTIME = "created_time";
	public static final String KEY_WORKPLAN_MODIFIEDTIME = "modified_time";
	public static final String KEY_WORKPLAN_CREATEDBY = "created_by";

	// Marketing Materials
	public static final String KEY_MARKETINGMATERIALS_ROWID = "_id";
	public static final String KEY_MARKETINGMATERIALS_NO = "no";
	public static final String KEY_MARKETINGMATERIALS_CRMNO = "crm_no";
	public static final String KEY_MARKETINGMATERIALS_DESCRIPTION = "description";
	public static final String KEY_MARKETINGMATERIALS_LASTUPDATE = "last_update";
	public static final String KEY_MARKETINGMATERIALS_TAGS = "tags";
	public static final String KEY_MARKETINGMATERIALS_BUSINESSUNIT = "business_unit";
	public static final String KEY_MARKETINGMATERIALS_ISNEW = "is_new";
	public static final String KEY_MARKETINGMATERIALS_ISACTIVE = "is_active";
	public static final String KEY_MARKETINGMATERIALS_CREATEDTIME = "created_time";
	public static final String KEY_MARKETINGMATERIALS_MODIFIEDTIME = "modified_time";
	public static final String KEY_MARKETINGMATERIALS_CREATEDBY = "created_by";

	// JDI Merchandising
	public static final String KEY_JDIMERCHANDISING_ROWID = "_id";
	public static final String KEY_JDIMERCHANDISING_NO = "no";
	public static final String KEY_JDIMERCHANDISING_CRMNO = "crm_no";
	public static final String KEY_JDIMERCHANDISING_ACTIVITY = "activity";
	public static final String KEY_JDIMERCHANDISING_PRODUCTBRAND = "product_brand";
	public static final String KEY_JDIMERCHANDISING_STATUS = "status";
	public static final String KEY_JDIMERCHANDISING_CREATEDTIME = "created_time";
	public static final String KEY_JDIMERCHANDISING_MODIFIEDTIME = "modified_time";
	public static final String KEY_JDIMERCHANDISING_CREATEDBY = "created_by";

	// Documents
	public static final String KEY_DOCUMENT_ROWID = "_id";
	public static final String KEY_DOCUMENT_NO = "no";
	public static final String KEY_DOCUMENT_TITLE = "title";
	public static final String KEY_DOCUMENT_MODULENAME = "module_name";
	public static final String KEY_DOCUMENT_MODULEID = "module_id";
	public static final String KEY_DOCUMENT_FILENAME = "file_name";
	public static final String KEY_DOCUMENT_FILETYPE = "file_type";
	public static final String KEY_DOCUMENT_FILEPATH = "file_path";
	public static final String KEY_DOCUMENT_ISACTIVE = "is_active";
	public static final String KEY_DOCUMENT_CREATEDTIME = "created_time";
	public static final String KEY_DOCUMENT_MODIFIEDTIME = "modified_time";
	public static final String KEY_DOCUMENT_USER = "user";
	public static final String KEY_DOCUMENT_CRMNO = "crm_no";

	// Calendar
	public static final String KEY_CALENDAR_ROWID = "_id";
	public static final String KEY_CALENDAR_ACTIVITYTYPE = "activitytype";
	public static final String KEY_CALENDAR_DATESTART = "date_start";
	public static final String KEY_CALENDAR_DUEDATE = "due_date";
	public static final String KEY_CALENDAR_DESCRIPTION = "description";
	public static final String KEY_CALENDAR_SUBJECT = "subject";
	public static final String KEY_CALENDAR_TIMESTART = "time_start";
	public static final String KEY_CALENDAR_TIMEEND = "time_end";
	public static final String KEY_CALENDAR_ACTIVITY = "activity";
	public static final String KEY_CALENDAR_CREATEDTIME = "created_time";
	public static final String KEY_CALENDAR_MODIFIEDTIME = "modified_time";
	public static final String KEY_CALENDAR_USER = "user";

	// Product Focus
	public static final String KEY_PRODUCTFOCUS_ROWID = "_id";
	public static final String KEY_PRODUCTFOCUS_PRODUCT = "product";
	public static final String KEY_PRODUCTFOCUS_ACTIVITY = "activity";

	// Product Supplier
	public static final String KEY_PRODUCTSUPPLIER_ROWID = "_id";
	public static final String KEY_PRODUCTSUPPLIER_NO = "no";
	public static final String KEY_PRODUCTSUPPLIER_CRMNO = "crm_no";
	public static final String KEY_PRODUCTSUPPLIER_PRODUCTBRAND = "product_brand";
	public static final String KEY_PRODUCTSUPPLIER_SUPPLIER = "supplier";
	public static final String KEY_PRODUCTSUPPLIER_OTHERREMARKS = "others_remarks";
	public static final String KEY_PRODUCTSUPPLIER_ACTIVITY = "activity";
	public static final String KEY_PRODUCTSUPPLIER_CREATEDBY = "created_by";
	public static final String KEY_PRODUCTSUPPLIER_CREATEDTIME = "created_time";
	public static final String KEY_PRODUCTSUPPLIER_MODIFIEDTIME = "modified_time";

	// Sales Protocol
	public static final String KEY_SALESPROTOCOL_ROWID = "_id";
	public static final String KEY_SALESPROTOCOL_NO = "no";
	public static final String KEY_SALESPROTOCOL_CRMNO = "crm_no";
	public static final String KEY_SALESPROTOCOL_BUSINESSUNIT = "business_unit";
	public static final String KEY_SALESPROTOCOL_DESCRIPTION = "description";
	public static final String KEY_SALESPROTOCOL_LASTUPDATE = "last_update";
	public static final String KEY_SALESPROTOCOL_TAGS = "tags";
	public static final String KEY_SALESPROTOCOL_PROTOCOLTYPE = "protocol_type";
	public static final String KEY_SALESPROTOCOL_ISACTIVE = "is_active";
	public static final String KEY_SALESPROTOCOL_CREATEDBY = "created_by";
	public static final String KEY_SALESPROTOCOL_CREATEDTIME = "created_time";
	public static final String KEY_SALESPROTOCOL_MODIFIEDTIME = "modified_time";

	// Picklists
	public static final String KEY_PICKLISTS_ROWID = "_id";
	public static final String KEY_PICKLISTS_NAME = "name";

	// // Picklist Activitytype Type
	// public static final String KEY_ACTIVITYTYPE_TYPE_ROWID = "_id";
	// public static final String KEY_ACTIVITYTYPE_TYPE_NAME = "name";
	// public static final String KEY_ACTIVITYTYPE_TYPE_CATEGORY =
	// "activity_category";

	// Location Province
	public static final String KEY_PROVINCE_ROWID = "_id";
	public static final String KEY_PROVINCE_NAME = "name";
	public static final String KEY_PROVINCE_AREA = "area";

	// Location CityTown
	public static final String KEY_CITYTOWN_ROWID = "_id";
	public static final String KEY_CITYTOWN_NAME = "name";
	public static final String KEY_CITYTOWN_PROVINCE = "province";

	// ===========================================================
	// Table Create String
	// ===========================================================

	private String TABLE_CREATE_USER = "create table %s (%s integer primary key autoincrement, %s text, %s text, %s text, %s text, %s text, %s text, %s text, %s integer, %s integer, %s text, %s text, %s text, %s text);";
	// private String TABLE_CREATE_ACTIVITY =
	// "create table %s (%s integer primary key autoincrement, %s text, %s text, %s real, %s text, %s text, %s real, %s real, %s text, %s text, %s text, %s text, %s text, %s real, %s real, %s real, %s integer, %s integer, %s text, %s text, %s real, %s real, %s text, %s text, %s text, %s text, %s text, %s text, %s text, %s text, %s text, %s text, %s text, %s text, %s real,%s real,%s real,%s real,%s real,foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s));";
	private String TABLE_CREATE_ACTIVITY = "create table " + ACTIVITY_TABLE
			+ " (" + KEY_ACTIVITY_ROWID
			+ " integer primary key autoincrement, " + KEY_ACTIVITY_NO
			+ " text, " + KEY_ACTIVITY_CRMNO + " text, "
			+ KEY_ACTIVITY_ACTIVITYTYPE + " real, " + KEY_ACTIVITY_CHECKIN
			+ " text," + KEY_ACTIVITY_CHECKOUT + " text, "
			+ KEY_ACTIVITY_BUSINESSUNIT + " real," + KEY_ACTIVITY_CREATEDBY
			+ " real," + KEY_ACTIVITY_LONGITUDE + " real, "
			+ KEY_ACTIVITY_LATITUDE + " real, " + KEY_ACTIVITY_CREATEDTIME
			+ " text, " + KEY_ACTIVITY_MODIFIEDTIME + " text, "
			+ KEY_ACTIVITY_REASONREMARKS + " text, " + KEY_ACTIVITY_SMR
			+ " real, " + KEY_ACTIVITY_ADMINWORKDETAILS + " text, "
			+ KEY_ACTIVITY_CUSTOMER + " real, " + KEY_ACTIVITY_AREA + " real, "
			+ KEY_ACTIVITY_PROVINCE + " real, " + KEY_ACTIVITY_CITY + " real, "
			+ KEY_ACTIVITY_WORKPLANENTRY + " real, " + KEY_ACTIVITY_OBJECTIVES
			+ " text, " + KEY_ACTIVITY_FIRSTTIMEVISIT + " integer, "
			+ KEY_ACTIVITY_PLANNEDVISIT + " integer, " + KEY_ACTIVITY_NOTES
			+ " text, " + KEY_ACTIVITY_HIGHLIGHTS + " text, "
			+ KEY_ACTIVITY_NEXTSTEPS + " text, " + KEY_ACTIVITY_FOLLOWUP
			+ " text, " + KEY_ACTIVITY_PROJECTNAME + " text, "
			+ KEY_ACTIVITY_PROJECTSTAGE + " text, "
			+ KEY_ACTIVITY_PROJECTCATEGORY + " text, " + KEY_ACTIVITY_VENUE
			+ " text, " + KEY_ACTIVITY_NOOFATTENDEES + " integer, "
			+ KEY_ACTIVITY_ENDUSERACTIVITYTYPES + " text, foreign key("
			+ KEY_ACTIVITY_ACTIVITYTYPE + ") references " + ACTIVITY_TYPE_TABLE
			+ "(" + KEY_ACTIVITYTYPE_ROWID + "), foreign key("
			+ KEY_ACTIVITY_BUSINESSUNIT + ") references " + BUSINESS_UNIT_TABLE
			+ "(" + KEY_BUSINESSUNIT_ROWID + "), foreign key("
			+ KEY_ACTIVITY_CREATEDBY + ") references " + USER_TABLE + " ("
			+ KEY_USER_ROWID + "), foreign key(" + KEY_ACTIVITY_SMR
			+ ") references " + SMR_TABLE + " (" + KEY_SMR_ROWID
			+ "), foreign key(" + KEY_ACTIVITY_CUSTOMER + ") references "
			+ CUSTOMER_TABLE + " (" + KEY_CUSTOMER_ROWID + "), foreign key("
			+ KEY_ACTIVITY_AREA + ") references " + AREA_TABLE + " ("
			+ KEY_PICKLISTS_ROWID + "), foreign key(" + KEY_ACTIVITY_PROVINCE
			+ ") references " + PROVINCE_TABLE + "(" + KEY_PROVINCE_ROWID
			+ "), foreign key(" + KEY_ACTIVITY_CITY + ") references "
			+ CITYTOWN_TABLE + "(" + KEY_CITYTOWN_ROWID + "), foreign key("
			+ KEY_ACTIVITY_WORKPLANENTRY + ") references "
			+ WORKPLAN_ENTRY_TABLE + "(" + KEY_WORKPLANENTRY_ROWID + "));";
	private String TABLE_CREATE_ACTIVITY_TYPE = "create table %s (%s integer primary key autoincrement, %s text,%s text,%s text, %s real, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_BUSINESS_UNIT = "create table %s (%s integer primary key autoincrement, %s text, %s text, %s text, %s text, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_COMPETITOR_PRODUCT = "create table %s (%s integer primary key autoincrement, %s text,%s text, %s real, %s text, %s text, %s text, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_COMPETITOR_PRODUCT_STOCK_CHECK = "create table %s (%s integer primary key autoincrement, %s text, %s text, %s real, %s real, %s real, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	// private String TABLE_CREATE_COMPETITOR =
	// "create table %s (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_CUSTOMER_CONTACT = "create table %s (%s integer primary key autoincrement, %s text,%s text, %s text, %s text, %s real, %s text, %s text, %s text, %s real, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_CUSTOMER = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s text, %s text, %s real, %s text, %s real, %s real, %s real,%s real, %s real,%s real, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s),foreign key(%s) references %s(%s),foreign key(%s) references %s(%s),foreign key(%s) references %s(%s),foreign key(%s) references %s(%s),foreign key(%s) references %s(%s),foreign key(%s) references %s(%s),foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_EVENT_PROTOCOL = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s text, %s real, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_JDI_PRODUCT_STOCK_CHECK = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s real, %s real, %s real, %s integer, %s real, %s text, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_MARKETING_INTEL = "create table %s (%s integer primary key autoincrement, %s text , %s text , %s real, %s real, %s text, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_PRODUCT = "create table %s (%s integer primary key autoincrement, %s text, %s text, %s text, %s text, %s text, %s text, %s real, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_PROJECT_REQUIREMENT = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s real, %s real, %s text, %s text, %s text, %s text, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_SMR = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s real, %s integer, %s real, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	// private String TABLE_CREATE_SMR_TIMECARD =
	// "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s real, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	// private String TABLE_CREATE_SUPPLIER =
	// "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s text, %s text, %s text, %s text, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_WORKPLAN_ENTRY = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s real, %s real, %s real, %s real, %s real, %s text, %s real, %s integer, %s real, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_WORKPLAN = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_MARKETING_MATERIALS = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s text, %s real, %s integer, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_JDI_MERCHANDISING = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s real, %s real, %s real, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_DOCUMENT = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s text, %s text, %s text,%s text, %s integer, %s text, %s text, %s real, foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_CALENDAR = "create table %s (%s integer primary key autoincrement, %s text, %s text , %s text, %s text, %s text, %s text, %s text, %s real, %s text, %s text, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_PICKLISTS = "create table %s (%s integer primary key autoincrement, %s text)";
	private String TABLE_CREATE_PICKLISTS_W_DEPENDENCIES = "create table %s (%s integer primary key autoincrement, %s text, %s real)";
	private String TABLE_CREATE_PRODUCTFOCUS = "create table %s (%s integer primary key autoincrement, %s real, %s real, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_PRODUCTSUPPLIER = "create table %s (%s integer primary key autoincrement, %s text, %s text, %s text, %s real, %s text, %s real, %s real, %s text, %s text, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";
	private String TABLE_CREATE_SALESPROTOCOLS = "create table %s (%s integer primary key autoincrement, %s text, %s text, %s real, %s text, %s text, %s text, %s real, %s integer, %s real, %s text, %s text, foreign key(%s) references %s(%s), foreign key(%s) references %s(%s), foreign key(%s) references %s(%s))";

	// ===========================================================
	// Public static field
	// ===========================================================

	public static final String USER_TABLE = "User";
	public static final String ACTIVITY_TABLE = "Activity";
	public static final String ACTIVITY_TYPE_TABLE = "Activity_Type";
	public static final String BUSINESS_UNIT_TABLE = "Business_Unit";
	public static final String COMPETITOR_PRODUCT_TABLE = "Competitor_Product";
	public static final String COMPETITOR_PRODUCT_STOCK_CHECK_TABLE = "Competitor_Product_Stock_Check";
	// public static final String COMPETITOR_TABLE = "Competitor";
	public static final String CUSTOMER_CONTACT_TABLE = "Customer_Contact";
	public static final String CUSTOMER_TABLE = "Customer";
	public static final String EVENT_PROTOCOL_TABLE = "Event_Protocol";
	public static final String JDI_PRODUCT_STOCK_CHECK_TABLE = "JDI_Product_Stock_Check";
	public static final String MARKETING_INTEL_TABLE = "Marketing_Intel";
	public static final String PRODUCT_TABLE = "Product";
	public static final String PROJECT_REQUIREMENTS_TABLE = "Project_Requirements";
	public static final String SMR_TABLE = "SMR";
	// public static final String SMR_TIMECARD_TABLE = "SMR_Time_Card";
	// public static final String SUPPLIER_TABLE = "Supplier";
	public static final String WORKPLAN_ENTRY_TABLE = "Workplan_Entry";
	public static final String WORKPLAN_TABLE = "Workplan";
	public static final String MARKETING_MATERIALS_TABLE = "Marketing_Materials";
	public static final String JDI_MERCHANDISING_CHECK_TABLE = "JDI_Merchandising_Check";
	public static final String DOCUMENT_TABLE = "Document";
	public static final String CALENDAR_TABLE = "Calendar";
	public static final String PRODUCT_FOCUS_TABLE = "Product_Focus";
	public static final String PRODUCT_SUPPLIER_TABLE = "Product_Supplier";
	public static final String SALES_PROTOCOL_TABLE = "Sales_Protocol";

	// Picklists
	public static final String ACTIVITY_PROJECT_CATEGORY_TABLE = "Activity_Project_Category";
	public static final String ACTIVITY_PROJECT_STAGE_TABLE = "Activity_Project_Stage";
	public static final String ACTIVITYTYPE_CATEGORY_TABLE = "ActivityType_Category";
	// public static final String ACTIVITYTYPE_TYPE_TABLE =
	// "ActivityType_Type";
	public static final String COMPETITOR_PRODUCT_STOCKSTATUS_TABLE = "Competitor_Product_StockStatus";
	public static final String CUSTOMERCONTACT_POSITION_TABLE = "Customer_Contact_Position";
	public static final String CUSTOMER_SIZE_TABLE = "Customer_Size";
	public static final String CUSTOMER_TYPE_TABLE = "Customer_Type";
	public static final String CUSTOMER_RECORD_STATUS_TABLE = "Customer_Record_Status";
	public static final String EVENT_TYPE_TABLE = "Event_Type";
	public static final String JDI_MERCHANDISING_CHECK_STATUS_TABLE = "JDI_Merchandising_Check_Status";
	public static final String JDI_PRODUCT_STOCK_STATUS_TABLE = "JDI_Product_Stock_Status";
	public static final String PROJECT_REQUIREMENTS_TYPE_TABLE = "Project_Requirements_Type";
	public static final String SMR_TIMECARD_ENTRY_TABLE = "SMR_TimeCard_Entry";
	public static final String WORKPLAN_ENTRY_STATUS_TABLE = "Workplan_Entry_Status";
	public static final String ACTIVITY_ENDUSER_ACTIVITYTYPE_TABLE = "Enduser_Activity_Type";
	public static final String SALES_PROTOCOL_TYPE_TABLE = "Sales_Protocol_Type";

	// Location
	public static final String AREA_TABLE = "Area";
	public static final String CITYTOWN_TABLE = "City_Town";
	public static final String PROVINCE_TABLE = "Province";

	// ===========================================================
	// Private fields
	// ===========================================================

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private UserTable mUser;
	private ActivityTable mActivity;
	private ActivityTypeTable mActivityType;
	private BusinessUnitTable mBusiness;
	private CompetitorProductTable mCompetitorProduct;
	private CompetitorProductStockCheckTable mCompetitorProductStockCheck;
	// private CompetitorTable mCompetitor;
	private CustomerContactTable mCustomerContact;
	private CustomerTable mCustomer;
	private EventProtocolTable mEventProtocol;
	private JDIproductStockCheckTable mJDIproductStockCheck;
	private MarketingIntelTable mMarktingIntel;
	private ProductTable mProductTable;
	private ProjectRequirementTable mProjectRequirements;
	private SMRTable mSMR;
	// private SMRtimeCardTable mSMRtimeCard;
	// private SupplierTable mSupplier;
	private WorkplanEntryTable mWorkplanEntry;
	private WorkplanTable mWorkplan;
	private MarketingMaterialsTable mMarketingMaterials;
	private JDImerchandisingCheckTable mJDImerchandisingCheck;
	private DocumentTable mDocument;
	private CalendarTable mCalendar;
	private ProductFocusTable mProductFocus;
	private ProductSupplierTable mProductSupplier;
	private SalesProtocolTable mSalesProtocol;

	// Picklists
	private PActProjCategoryTable mActivityProjectCategory;
	private PActProjStageTable mActivityProjectStage;
	private PActtypeCategoryTable mActivitytypeCategory;
	// private PActtypeTypeTable mActivitytypeType;
	private PComptProdStockStatusTable mCompetitorProductStockStatus;
	private PCustConPositionTable mCustomerContactPosition;
	private PCustSizeTable mCustomerSize;
	private PCustTypeTable mCustomerType;
	private PCustRecordStatusTable mCustomerRecordStatus;
	private PEventTypeTable mEventProtocolType;
	private PJDImerchCheckStatusTable mJDImerchCheckStatus;
	private PJDIprodStatusTable mJDIproductStatus;
	private PProjReqTypeTable mProjectRequirementType;
	private PSMRentryTypeTable mSMRentryType;
	private PWorkEntryStatusTable mWorkplanEntryStatus;
	private PactEndUserActTypeTable mActivityEndUserActType;
	private PSalesProtocolTypeTable mSalesProtocolTypeTable;

	// Location
	private PAreaTable mArea;
	private PCityTownTable mCityTown;
	private PProvinceTable mProvince;

	// ===========================================================
	// Private constructor
	// ===========================================================

	private DatabaseAdapter(Context ctx) {
		mDbHelper = new DatabaseHelper(ctx);
	}

	// ===========================================================
	// Public static methods
	// ===========================================================

	public static void init(Context context) {
		if (sInstance == null) {
			sInstance = new DatabaseAdapter(context);
		}
		Log.i(JardineApp.TAG, "Initialized database");
	}

	public static DatabaseAdapter getInstance() {
		return sInstance;
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public DatabaseAdapter open() {
		if (mDb == null || !mDb.isOpen()) {
			mDb = mDbHelper.getWritableDatabase();
		}
		return this;
	}

	public void close() {
		if (mDbHelper != null) {
			mDbHelper.close();
		}
		if (mDb != null && mDb.isOpen()) {
			mDb.close();
		}
	}

	public UserTable getUser() {
		if (mUser == null) {
			mUser = new UserTable(mDb, USER_TABLE);
		}
		return mUser;
	}

	public ActivityTable getActivity() {
		if (mActivity == null) {
			mActivity = new ActivityTable(mDb, ACTIVITY_TABLE);
		}
		return mActivity;
	}

	public ActivityTypeTable getActivityType() {
		if (mActivityType == null) {
			mActivityType = new ActivityTypeTable(mDb, ACTIVITY_TYPE_TABLE);
		}
		return mActivityType;
	}

	public BusinessUnitTable getBusinessUnit() {
		if (mBusiness == null) {
			mBusiness = new BusinessUnitTable(mDb, BUSINESS_UNIT_TABLE);
		}
		return mBusiness;
	}

	public CompetitorProductTable getCompetitorProduct() {
		if (mCompetitorProduct == null) {
			mCompetitorProduct = new CompetitorProductTable(mDb,
					COMPETITOR_PRODUCT_TABLE);
		}
		return mCompetitorProduct;
	}

	public CompetitorProductStockCheckTable getCompetitorProductStockCheck() {
		if (mCompetitorProductStockCheck == null) {
			mCompetitorProductStockCheck = new CompetitorProductStockCheckTable(
					mDb, COMPETITOR_PRODUCT_STOCK_CHECK_TABLE);
		}
		return mCompetitorProductStockCheck;
	}

	// public CompetitorTable getCompetitor() {
	// if (mCompetitor == null) {
	// mCompetitor = new CompetitorTable(mDb, COMPETITOR_TABLE);
	// }
	// return mCompetitor;
	// }

	public CustomerContactTable getCustomerContact() {
		if (mCustomerContact == null) {
			mCustomerContact = new CustomerContactTable(mDb,
					CUSTOMER_CONTACT_TABLE);
		}
		return mCustomerContact;
	}

	public CustomerTable getCustomer() {
		if (mCustomer == null) {
			mCustomer = new CustomerTable(mDb, CUSTOMER_TABLE);
		}
		return mCustomer;
	}

	public EventProtocolTable getEventProtocol() {
		if (mEventProtocol == null) {
			mEventProtocol = new EventProtocolTable(mDb, EVENT_PROTOCOL_TABLE);
		}
		return mEventProtocol;
	}

	public JDIproductStockCheckTable getJDIproductStockCheck() {
		if (mJDIproductStockCheck == null) {
			mJDIproductStockCheck = new JDIproductStockCheckTable(mDb,
					JDI_PRODUCT_STOCK_CHECK_TABLE);
		}
		return mJDIproductStockCheck;
	}

	public MarketingIntelTable getMarketingIntel() {
		if (mMarktingIntel == null) {
			mMarktingIntel = new MarketingIntelTable(mDb, MARKETING_INTEL_TABLE);
		}
		return mMarktingIntel;
	}

	public ProductTable getProduct() {
		if (mProductTable == null) {
			mProductTable = new ProductTable(mDb, PRODUCT_TABLE);
		}
		return mProductTable;
	}

	public ProjectRequirementTable getProjectRequirement() {
		if (mProjectRequirements == null) {
			mProjectRequirements = new ProjectRequirementTable(mDb,
					PROJECT_REQUIREMENTS_TABLE);
		}
		return mProjectRequirements;
	}

	public SMRTable getSMR() {
		if (mSMR == null) {
			mSMR = new SMRTable(mDb, SMR_TABLE);
		}
		return mSMR;
	}

	// public SMRtimeCardTable getSMRTimeCard() {
	// if (mSMRtimeCard == null) {
	// mSMRtimeCard = new SMRtimeCardTable(mDb, SMR_TIMECARD_TABLE);
	// }
	// return mSMRtimeCard;
	// }

	// public SupplierTable getSupplier() {
	// if (mSupplier == null) {
	// mSupplier = new SupplierTable(mDb, SUPPLIER_TABLE);
	// }
	// return mSupplier;
	// }

	public WorkplanEntryTable getWorkplanEntry() {
		if (mWorkplanEntry == null) {
			mWorkplanEntry = new WorkplanEntryTable(mDb, WORKPLAN_ENTRY_TABLE);
		}
		return mWorkplanEntry;
	}

	public WorkplanTable getWorkplan() {
		if (mWorkplan == null) {
			mWorkplan = new WorkplanTable(mDb, WORKPLAN_TABLE);
		}
		return mWorkplan;
	}

	public MarketingMaterialsTable getMarketingMaterials() {
		if (mMarketingMaterials == null) {
			mMarketingMaterials = new MarketingMaterialsTable(mDb,
					MARKETING_MATERIALS_TABLE);
		}
		return mMarketingMaterials;
	}

	public JDImerchandisingCheckTable getJDImerchandisingCheck() {
		if (mJDImerchandisingCheck == null) {
			mJDImerchandisingCheck = new JDImerchandisingCheckTable(mDb,
					JDI_MERCHANDISING_CHECK_TABLE);
		}
		return mJDImerchandisingCheck;
	}

	public DocumentTable getDocument() {
		if (mDocument == null) {
			mDocument = new DocumentTable(mDb, DOCUMENT_TABLE);
		}
		return mDocument;
	}

	public CalendarTable getCalendar() {
		if (mCalendar == null) {
			mCalendar = new CalendarTable(mDb, CALENDAR_TABLE);
		}
		return mCalendar;
	}

	// Picklists

	public PActProjCategoryTable getActivityProjectCategory() {
		if (mActivityProjectCategory == null) {
			mActivityProjectCategory = new PActProjCategoryTable(mDb,
					ACTIVITY_PROJECT_CATEGORY_TABLE);
		}
		return mActivityProjectCategory;
	}

	public PActProjStageTable getActivityProjectStage() {
		if (mActivityProjectStage == null) {
			mActivityProjectStage = new PActProjStageTable(mDb,
					ACTIVITY_PROJECT_STAGE_TABLE);
		}
		return mActivityProjectStage;
	}

	public PActtypeCategoryTable getActivitytypeCategory() {
		if (mActivitytypeCategory == null) {
			mActivitytypeCategory = new PActtypeCategoryTable(mDb,
					ACTIVITYTYPE_CATEGORY_TABLE);
		}
		return mActivitytypeCategory;
	}

	// public PActtypeTypeTable getActivitytypeType() {
	// if (mActivitytypeType == null) {
	// mActivitytypeType = new PActtypeTypeTable(mDb,
	// ACTIVITYTYPE_TYPE_TABLE);
	// }
	// return mActivitytypeType;
	// }

	public PComptProdStockStatusTable getCompetitorProductStockStatus() {
		if (mCompetitorProductStockStatus == null) {
			mCompetitorProductStockStatus = new PComptProdStockStatusTable(mDb,
					COMPETITOR_PRODUCT_STOCKSTATUS_TABLE);
		}
		return mCompetitorProductStockStatus;
	}

	public PCustConPositionTable getCustomerContactPosition() {
		if (mCustomerContactPosition == null) {
			mCustomerContactPosition = new PCustConPositionTable(mDb,
					CUSTOMERCONTACT_POSITION_TABLE);
		}
		return mCustomerContactPosition;
	}

	public PCustSizeTable getCustomerSize() {
		if (mCustomerSize == null) {
			mCustomerSize = new PCustSizeTable(mDb, CUSTOMER_SIZE_TABLE);
		}
		return mCustomerSize;
	}

	public PCustTypeTable getCustomerType() {
		if (mCustomerType == null) {
			mCustomerType = new PCustTypeTable(mDb, CUSTOMER_TYPE_TABLE);
		}
		return mCustomerType;
	}

	public PCustRecordStatusTable getCustomerRecordStatus() {
		if (mCustomerRecordStatus == null) {
			mCustomerRecordStatus = new PCustRecordStatusTable(mDb,
					CUSTOMER_RECORD_STATUS_TABLE);
		}
		return mCustomerRecordStatus;
	}

	public PEventTypeTable getEventProtocolType() {
		if (mEventProtocolType == null) {
			mEventProtocolType = new PEventTypeTable(mDb, EVENT_TYPE_TABLE);
		}
		return mEventProtocolType;
	}

	public PJDImerchCheckStatusTable getJDImerchCheckStatus() {
		if (mJDImerchCheckStatus == null) {
			mJDImerchCheckStatus = new PJDImerchCheckStatusTable(mDb,
					JDI_MERCHANDISING_CHECK_STATUS_TABLE);
		}
		return mJDImerchCheckStatus;
	}

	public PJDIprodStatusTable getJDIproductStatus() {
		if (mJDIproductStatus == null) {
			mJDIproductStatus = new PJDIprodStatusTable(mDb,
					JDI_PRODUCT_STOCK_STATUS_TABLE);
		}
		return mJDIproductStatus;
	}

	public PProjReqTypeTable getProjectRequirementType() {
		if (mProjectRequirementType == null) {
			mProjectRequirementType = new PProjReqTypeTable(mDb,
					PROJECT_REQUIREMENTS_TYPE_TABLE);
		}
		return mProjectRequirementType;
	}

	public PSMRentryTypeTable getSMRentryType() {
		if (mSMRentryType == null) {
			mSMRentryType = new PSMRentryTypeTable(mDb,
					SMR_TIMECARD_ENTRY_TABLE);
		}
		return mSMRentryType;
	}

	public PWorkEntryStatusTable getWorkplanEntryStatus() {
		if (mWorkplanEntryStatus == null) {
			mWorkplanEntryStatus = new PWorkEntryStatusTable(mDb,
					WORKPLAN_ENTRY_STATUS_TABLE);
		}
		return mWorkplanEntryStatus;
	}

	public PactEndUserActTypeTable getActEndUserActivityType() {
		if (mActivityEndUserActType == null) {
			mActivityEndUserActType = new PactEndUserActTypeTable(mDb,
					ACTIVITY_ENDUSER_ACTIVITYTYPE_TABLE);
		}
		return mActivityEndUserActType;
	}

	public PSalesProtocolTypeTable getSalesProtocolType() {
		if (mSalesProtocolTypeTable == null) {
			mSalesProtocolTypeTable = new PSalesProtocolTypeTable(mDb,
					SALES_PROTOCOL_TYPE_TABLE);
		}
		return mSalesProtocolTypeTable;
	}

	public ProductFocusTable getProductFocus() {
		if (mProductFocus == null) {
			mProductFocus = new ProductFocusTable(mDb, PRODUCT_FOCUS_TABLE);
		}
		return mProductFocus;
	}

	public ProductSupplierTable getProductSupplier() {
		if (mProductSupplier == null) {
			mProductSupplier = new ProductSupplierTable(mDb,
					PRODUCT_SUPPLIER_TABLE);
		}
		return mProductSupplier;
	}

	public SalesProtocolTable getSalesProtocol() {
		if (mSalesProtocol == null) {
			mSalesProtocol = new SalesProtocolTable(mDb, SALES_PROTOCOL_TABLE);
		}
		return mSalesProtocol;
	}

	// Location

	public PAreaTable getArea() {
		if (mArea == null) {
			mArea = new PAreaTable(mDb, AREA_TABLE);
		}
		return mArea;
	}

	public PCityTownTable getCityTown() {
		if (mCityTown == null) {
			mCityTown = new PCityTownTable(mDb, CITYTOWN_TABLE);
		}
		return mCityTown;
	}

	public PProvinceTable getProvince() {
		if (mProvince == null) {
			mProvince = new PProvinceTable(mDb, PROVINCE_TABLE);
		}
		return mProvince;
	}

	// ===========================================================
	// Private inner classes
	// ===========================================================

	private final class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			createTables(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(JardineApp.TAG,
					String.format(
							"Upgrading database from version %s to %s, which will destroy all old data",
							oldVersion, newVersion));
			// dropTable(db, USER_TABLE);
			// onCreate(db);

			// if (DATABASE_VERSION == 1) {
			// // testing add column: version 2
			// String upgrade_user = USER_TABLE + " (" + KEY_USER_ROWID
			// + " integer primary key autoincrement, " + KEY_USER_NO
			// + " text not null, " + KEY_USER_USERNAME
			// + " text not null, " + KEY_USER_PASSWORD
			// + " text not null, " + KEY_USER_EMAILADDRESS
			// + " text not null, " + KEY_USER_LASTNAME
			// + " text not null, " + KEY_USER_MIDDLENAME + " text, "
			// + KEY_USER_FIRSTNAME + " text not null, "
			// + KEY_USER_LOGGEDIN + " integer not null, "
			// + KEY_USER_ADDRESS + " text, " + KEY_USER_AVATAR
			// + " text, " + KEY_USER_AVATARTHUMBNAIL + " text, "
			// + KEY_USER_COVERPHOTO + " text, "
			// + KEY_USER_COVERPHOTOTHUMBNAIL + " text, "
			// + KEY_USER_FBID + " text, " + KEY_USER_FBSESSION
			// + " text, " + KEY_USER_GENDER + " text, "
			// + KEY_USER_STATUS + " integer, " + KEY_USER_PRIVACY
			// + " text, " + KEY_USER_CREATEDTIME + " text, "
			// + KEY_USER_ADDEDCOLUMN + " text )";
			//
			// db.beginTransaction();
			// try {
			// db.execSQL("CREATE TABLE IF NOT EXISTS " + upgrade_user);
			// List<String> columns = GetColumns(db, USER_TABLE);
			// db.execSQL("ALTER table " + USER_TABLE
			// + " RENAME TO 'temp_" + USER_TABLE + "'");
			// db.execSQL("create table " + upgrade_user);
			// columns.retainAll(GetColumns(db, USER_TABLE));
			// String cols = join(columns, ",");
			// db.execSQL(String.format(
			// "INSERT INTO %s (%s) SELECT %s from temp_%s",
			// USER_TABLE, cols, cols, USER_TABLE));
			// db.execSQL("DROP table 'temp_" + USER_TABLE + "'");
			// db.setTransactionSuccessful();
			// } finally {
			// db.endTransaction();
			// }
			// }else{
			//
			// }
		}

		private void createTables(SQLiteDatabase db) {

			String user = String.format(TABLE_CREATE_USER, USER_TABLE,
					KEY_USER_ROWID, KEY_USER_NO, KEY_USER_USERNAME,
					KEY_USER_PASSWORD, KEY_USER_EMAILADDRESS,
					KEY_USER_LASTNAME, KEY_USER_MIDDLENAME, KEY_USER_FIRSTNAME,
					KEY_USER_LOGGEDIN, KEY_USER_STATUS, KEY_USER_LASTSYNC,
					KEY_USER_AREA, KEY_USER_LOGGEDAREA, KEY_USER_CREATEDTIME);
			// String activity = String.format(TABLE_CREATE_ACTIVITY,
			// ACTIVITY_TABLE, KEY_ACTIVITY_ROWID, KEY_ACTIVITY_NO,
			// KEY_ACTIVITY_CRMNO, KEY_ACTIVITY_WORKPLAN,
			// KEY_ACTIVITY_STARTTIME, KEY_ACTIVITY_ENDTIME,
			// KEY_ACTIVITY_LONGITUDE, KEY_ACTIVITY_LATITUDE,
			// KEY_ACTIVITY_OBJECTIVE, KEY_ACTIVITY_NOTES,
			// KEY_ACTIVITY_HIGHLIGHTS, KEY_ACTIVITY_NEXTSTEPS,
			// KEY_ACTIVITY_FOLLOWUP, KEY_ACTIVITY_ACTIVITYTYPE,
			// KEY_ACTIVITY_WORKPLANENTRY, KEY_ACTIVITY_BUSINESSUNIT,
			// KEY_ACTIVITY_AREA, KEY_ACTIVITY_PROVINCE,
			// KEY_ACTIVITY_CITYTOWN, KEY_ACTIVITY_SOURCE,
			// KEY_ACTIVITY_CUSTOMER, KEY_ACTIVITY_FIRSTTIMEVISIT,
			// KEY_ACTIVITY_PLANNEDVISIT, KEY_ACTIVITY_CREATEDTIME,
			// KEY_ACTIVITY_MODIFIEDTIME, KEY_ACTIVITY_USER,
			// KEY_ACTIVITY_SMR, KEY_ACTIVITY_ISSUES,
			// KEY_ACTIVITY_FEEDBACK, KEY_ACTIVITY_ONGOINGCAMPAIGNS,
			// KEY_ACTIVITY_LASTDELIVERY, KEY_ACTIVITY_PROMOSTUBS,
			// KEY_ACTIVITY_PROJECTNAME, KEY_ACTIVITY_PROJECTCATEGORY,
			// KEY_ACTIVITY_PROJECTSTAGE, KEY_ACTIVITY_DATE,
			// KEY_ACTIVITY_TIME, KEY_ACTIVITY_VENUE,
			// KEY_ACTIVITY_NOOFATTENDEES, KEY_ACTIVITY_WORKPLAN,
			// WORKPLAN_TABLE, KEY_WORKPLAN_ROWID,
			// KEY_ACTIVITY_ACTIVITYTYPE, ACTIVITY_TYPE_TABLE,
			// KEY_ACTIVITYTYPE_ROWID, KEY_ACTIVITY_WORKPLANENTRY,
			// WORKPLAN_ENTRY_TABLE, KEY_WORKPLANENTRY_ROWID,
			// KEY_ACTIVITY_CUSTOMER, CUSTOMER_TABLE, KEY_CUSTOMER_ROWID,
			// KEY_ACTIVITY_USER, USER_TABLE, KEY_USER_ROWID,
			// KEY_ACTIVITY_SMR, SMR_TABLE, KEY_SMR_ROWID,
			// KEY_ACTIVITY_BUSINESSUNIT, BUSINESS_UNIT_TABLE,
			// KEY_BUSINESSUNIT_ROWID, KEY_ACTIVITY_AREA, AREA_TABLE,
			// KEY_PICKLISTS_ROWID, KEY_ACTIVITY_PROVINCE, PROVINCE_TABLE,
			// KEY_PROVINCE_ROWID, KEY_ACTIVITY_CITYTOWN, CITYTOWN_TABLE,
			// KEY_PROVINCE_ROWID);
			String activityType = String.format(TABLE_CREATE_ACTIVITY_TYPE,
					ACTIVITY_TYPE_TABLE, KEY_ACTIVITYTYPE_ROWID,
					KEY_ACTIVITYTYPE_NO, KEY_ACTIVITYTYPE_CRMNO,
					KEY_ACTIVITYTYPE_NAME, KEY_ACTIVITYTYPE_CATEGORY,
					KEY_ACTIVITYTYPE_ISACTIVE, KEY_ACTIVITYTYPE_CREATEDTIME,
					KEY_ACTIVITYTYPE_MODIFIEDTIME, KEY_ACTIVITYTYPE_USER,
					KEY_ACTIVITYTYPE_CATEGORY, ACTIVITYTYPE_CATEGORY_TABLE,
					KEY_PICKLISTS_ROWID, KEY_ACTIVITYTYPE_USER, USER_TABLE,
					KEY_USER_ROWID);
			String businessUnit = String.format(TABLE_CREATE_BUSINESS_UNIT,
					BUSINESS_UNIT_TABLE, KEY_BUSINESSUNIT_ROWID,
					KEY_BUSINESSUNIT_NO, KEY_BUSINESSUNIT_CRMNO,
					KEY_BUSINESSUNIT_NAME, KEY_BUSINESSUNIT_CODE,
					KEY_BUSINESSUNIT_ISACTIVE, KEY_BUSINESSUNIT_CREATEDTIME,
					KEY_BUSINESSUNIT_MODIFIEDTIME, KEY_BUSINESSUNIT_CREATED_BY,
					KEY_BUSINESSUNIT_CREATED_BY, USER_TABLE, KEY_USER_ROWID);
			String competitorProduct = String
					.format(TABLE_CREATE_COMPETITOR_PRODUCT,
							COMPETITOR_PRODUCT_TABLE,
							KEY_COMPETITORPRODUCT_ROWID,
							KEY_COMPETITORPRODUCT_NO,
							KEY_COMPETITORPRODUCT_CRMNO,
							KEY_COMPETITORPRODUCT_COMPETITOR,
							KEY_COMPETITORPRODUCT_PRODUCTBRAND,
							KEY_COMPETITORPRODUCT_PRODUCTDESCRIPTION,
							KEY_COMPETITORPRODUCT_PRODUCTSIZE,
							KEY_COMPETITORPRODUCT_ISACTIVE,
							KEY_COMPETITORPRODUCT_CREATEDTIME,
							KEY_COMPETITORPRODUCT_MODIFIEDTIME,
							KEY_COMPETITORPRODUCT_CREATEDBY,
							KEY_COMPETITORPRODUCT_CREATEDBY, USER_TABLE,
							KEY_USER_ROWID);
			String competitorProductStockCheck = String.format(
					TABLE_CREATE_COMPETITOR_PRODUCT_STOCK_CHECK,
					COMPETITOR_PRODUCT_STOCK_CHECK_TABLE,
					KEY_COMPETITORPRODUCTSTOCKCHECK_ROWID,
					KEY_COMPETITORPRODUCTSTOCKCHECK_NO,
					KEY_COMPETITORPRODUCTSTOCKCHECK_CRMNO,
					KEY_COMPETITORPRODUCTSTOCKCHECK_ACTIVITY,
					KEY_COMPETITORPRODUCTSTOCKCHECK_COMPETITORPRODUCT,
					KEY_COMPETITORPRODUCTSTOCKCHECK_STOCKSTATUS,
					KEY_COMPETITORPRODUCTSTOCKCHECK_LOADEDONSHELVES,
					KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDTIME,
					KEY_COMPETITORPRODUCTSTOCKCHECK_MODIFIEDTIME,
					KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDBY,
					KEY_COMPETITORPRODUCTSTOCKCHECK_CREATEDBY, USER_TABLE,
					KEY_USER_ROWID);
			// String competitor = String.format(TABLE_CREATE_COMPETITOR,
			// COMPETITOR_TABLE, KEY_COMPETITOR_ROWID, KEY_COMPETITOR_NO,
			// KEY_COMPETITOR_CRMNO, KEY_COMPETITOR_NAME,
			// KEY_COMPETITOR_ISACTIVE, KEY_COMPETITOR_CREATEDTIME,
			// KEY_COMPETITOR_MODIFIEDTIME, KEY_COMPETITOR_USER,
			// KEY_COMPETITOR_USER, USER_TABLE, KEY_USER_ROWID);
			String customerContact = String.format(
					TABLE_CREATE_CUSTOMER_CONTACT, CUSTOMER_CONTACT_TABLE,
					KEY_CUSTOMERCONTACT_ROWID, KEY_CUSTOMERCONTACT_NO,
					KEY_CUSTOMERCONTACT_CRMNO, KEY_CUSTOMERCONTACT_FIRSTNAME,
					KEY_CUSTOMERCONTACT_LASTNAME, KEY_CUSTOMERCONTACT_POSITION,
					KEY_CUSTOMERCONTACT_MOBILENO, KEY_CUSTOMERCONTACT_BIRTHDAY,
					KEY_CUSTOMERCONTACT_EMAIL, KEY_CUSTOMERCONTACT_CUSTOMER,
					KEY_CUSTOMERCONTACT_ISACTIVE,
					KEY_CUSTOMERCONTACT_CREATEDTIME,
					KEY_CUSTOMERCONTACT_MODIFIEDTIME,
					KEY_CUSTOMERCONTACT_CREATEDBY,
					KEY_CUSTOMERCONTACT_POSITION,
					CUSTOMERCONTACT_POSITION_TABLE, KEY_PICKLISTS_ROWID,
					KEY_CUSTOMERCONTACT_CUSTOMER, CUSTOMER_TABLE,
					KEY_CUSTOMER_ROWID, KEY_CUSTOMERCONTACT_CREATEDBY,
					KEY_CUSTOMERCONTACT_CREATEDBY, USER_TABLE, KEY_USER_ROWID);
			String customer = String.format(TABLE_CREATE_CUSTOMER,
					CUSTOMER_TABLE, KEY_CUSTOMER_ROWID, KEY_CUSTOMER_NO,
					KEY_CUSTOMER_CRMNO, KEY_CUSTOMER_NAME,
					KEY_CUSTOMER_CHAINNAME, KEY_CUSTOMER_LANDLINE,
					KEY_CUSTOMER_FAX, KEY_CUSTOMER_SIZE,
					KEY_CUSTOMER_STREETADDRESS, KEY_CUSTOMER_RECORDSTATUS,
					KEY_CUSTOMER_TYPE, KEY_CUSTOMER_BUSINESSUNIT,
					KEY_CUSTOMER_AREA, KEY_CUSTOMER_PROVINCE,
					KEY_CUSTOMER_CITYTOWN, KEY_CUSTOMER_ISACTIVE,
					KEY_CUSTOMER_CREATEDTIME, KEY_CUSTOMER_MODIFIEDTIME,
					KEY_CUSTOMER_CREATED_BY, KEY_CUSTOMER_SIZE,
					CUSTOMER_SIZE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_CUSTOMER_RECORDSTATUS, CUSTOMER_TYPE_TABLE,
					KEY_PICKLISTS_ROWID, KEY_CUSTOMER_TYPE,
					CUSTOMER_TYPE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_CUSTOMER_BUSINESSUNIT, BUSINESS_UNIT_TABLE,
					KEY_BUSINESSUNIT_ROWID, KEY_CUSTOMER_AREA, AREA_TABLE,
					KEY_PICKLISTS_ROWID, KEY_CUSTOMER_PROVINCE, PROVINCE_TABLE,
					KEY_PROVINCE_ROWID, KEY_CUSTOMER_CITYTOWN, CITYTOWN_TABLE,
					KEY_CITYTOWN_ROWID, KEY_CUSTOMER_CREATED_BY, USER_TABLE,
					KEY_USER_ROWID);
			String eventProtocol = String.format(TABLE_CREATE_EVENT_PROTOCOL,
					EVENT_PROTOCOL_TABLE, KEY_EVENTPROTOCOL_ROWID,
					KEY_EVENTPROTOCOL_NO, KEY_EVENTPROTOCOL_CRMNO,
					KEY_EVENTPROTOCOL_DESCRIPTION,
					KEY_EVENTPROTOCOL_LASTUPDATE, KEY_EVENTPROTOCOL_TAGS,
					KEY_EVENTPROTOCOL_EVENTTYPE, KEY_EVENTPROTOCOL_ISACTIVE,
					KEY_EVENTPROTOCOL_CREATEDTIME,
					KEY_EVENTPROTOCOL_MODIFIEDTIME, KEY_EVENTPROTOCOL_USER,
					KEY_EVENTPROTOCOL_USER, USER_TABLE, KEY_USER_ROWID);
			String jdiProductStockCheck = String.format(
					TABLE_CREATE_JDI_PRODUCT_STOCK_CHECK,
					JDI_PRODUCT_STOCK_CHECK_TABLE,
					KEY_JDIPRODUCTSTOCKCHECK_ROWID,
					KEY_JDIPRODUCTSTOCKCHECK_NO,
					KEY_JDIPRODUCTSTOCKCHECK_CRMNO,
					KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY,
					KEY_JDIPRODUCTSTOCKCHECK_PRODUCTBRAND,
					KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS,
					KEY_JDIPRODUCTSTOCKCHECK_LOADEDONSHELVES,
					KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER,
					KEY_JDIPRODUCTSTOCKCHECK_OTHERREMARKS,
					KEY_JDIPRODUCTSTOCKCHECK_CREATEDTIME,
					KEY_JDIPRODUCTSTOCKCHECK_MODIFIEDTIME,
					KEY_JDIPRODUCTSTOCKCHECK_CREATEDBY,
					KEY_JDIPRODUCTSTOCKCHECK_ACTIVITY, ACTIVITY_TABLE,
					KEY_ACTIVITYTYPE_ROWID,
					KEY_JDIPRODUCTSTOCKCHECK_PRODUCTBRAND, PRODUCT_TABLE,
					KEY_PRODUCT_ROWID, KEY_JDIPRODUCTSTOCKCHECK_STOCKSTATUS,
					JDI_PRODUCT_STOCK_STATUS_TABLE, KEY_PICKLISTS_ROWID,
					KEY_JDIPRODUCTSTOCKCHECK_SUPPLIER, CUSTOMER_TYPE_TABLE,
					KEY_PICKLISTS_ROWID, KEY_JDIPRODUCTSTOCKCHECK_CREATEDBY,
					USER_TABLE, KEY_USER_ROWID);
			String marketingIntel = String.format(TABLE_CREATE_MARKETING_INTEL,
					MARKETING_INTEL_TABLE, KEY_MARKETINGINTEL_ROWID,
					KEY_MARKETINGINTEL_NO, KEY_MARKETINGINTEL_CRMNO,
					KEY_MARKETINGINTEL_ACTIVITY,
					KEY_MARKETINGINTEL_COMPETITORPRODUCT,
					KEY_MARKETINGINTEL_DETAILS, KEY_MARKETINGINTEL_CREATEDTIME,
					KEY_MARKETINGINTEL_MODIFIEDTIME,
					KEY_MARKETINGINTEL_CREATEDBY, KEY_MARKETINGINTEL_ACTIVITY,
					ACTIVITY_TABLE, KEY_ACTIVITYTYPE_ROWID,
					KEY_MARKETINGINTEL_COMPETITORPRODUCT,
					COMPETITOR_PRODUCT_TABLE, KEY_COMPETITORPRODUCT_ROWID,
					KEY_MARKETINGINTEL_CREATEDBY, USER_TABLE, KEY_USER_ROWID);
			String product = String.format(TABLE_CREATE_PRODUCT, PRODUCT_TABLE,
					KEY_PRODUCT_ROWID, KEY_PRODUCT_NO, KEY_PRODUCT_CRMNO,
					KEY_PRODUCT_NUMBER, KEY_PRODUCT_BRAND,
					KEY_PRODUCT_DESCRIPTION, KEY_PRODUCT_PACKSIZE,
					KEY_PRODUCT_BUSINESSUNIT, KEY_PRODUCT_ISACTIVE,
					KEY_PRODUCT_CREATEDTIME, KEY_PRODUCT_MODIFIEDTIME,
					KEY_PRODUCT_CREATEDBY, KEY_PRODUCT_BUSINESSUNIT,
					BUSINESS_UNIT_TABLE, KEY_BUSINESSUNIT_ROWID,
					KEY_PRODUCT_CREATEDBY, USER_TABLE, KEY_USER_ROWID);
			String projectRequirement = String.format(
					TABLE_CREATE_PROJECT_REQUIREMENT,
					PROJECT_REQUIREMENTS_TABLE, KEY_PROJECTREQUIREMENTS_ROWID,
					KEY_PROJECTREQUIREMENTS_NO, KEY_PROJECTREQUIREMENTS_CRMNO,
					KEY_PROJECTREQUIREMENTS_ACTIVITY,
					KEY_PROJECTREQUIREMENTS_TYPE,
					KEY_PROJECTREQUIREMENTS_DATENEEDED,
					KEY_PROJECTREQUIREMENTS_SQUAREMETERS,
					KEY_PROJECTREQUIREMENTS_PRODUCTSBRAND,
					KEY_PROJECTREQUIREMENTS_OTHERDETAILS,
					KEY_PROJECTREQUIREMENTS_CREATEDTIME,
					KEY_PROJECTREQUIREMENTS_MODIFIEDTIME,
					KEY_PROJECTREQUIREMENTS_CREATEDBY,
					KEY_PROJECTREQUIREMENTS_ACTIVITY, ACTIVITY_TABLE,
					KEY_ACTIVITY_ROWID, KEY_PROJECTREQUIREMENTS_TYPE,
					PROJECT_REQUIREMENTS_TYPE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PROJECTREQUIREMENTS_CREATEDBY, USER_TABLE,
					KEY_USER_ROWID);
			String smr = String.format(TABLE_CREATE_SMR, SMR_TABLE,
					KEY_SMR_ROWID, KEY_SMR_NO, KEY_SMR_CRMNO,
					KEY_SMR_FIRSTNAME, KEY_SMR_LASTNAME, KEY_SMR_AREA,
					KEY_SMR_ISACTIVE, KEY_SMR_BUSINESS_UNIT,
					KEY_SMR_CREATEDTIME, KEY_SMR_MODIFIEDTIME,
					KEY_SMR_CREATED_BY, KEY_SMR_AREA, AREA_TABLE,
					KEY_PICKLISTS_ROWID, KEY_SMR_BUSINESS_UNIT,
					BUSINESS_UNIT_TABLE, KEY_BUSINESSUNIT_ROWID,
					KEY_SMR_CREATED_BY, KEY_SMR_CREATED_BY, USER_TABLE,
					KEY_USER_ROWID);
			// String smrTimecard = String.format(TABLE_CREATE_SMR_TIMECARD,
			// SMR_TIMECARD_TABLE, KEY_SMRTIMECARD_ROWID,
			// KEY_SMRTIMECARD_NO, KEY_SMRTIMECARD_CRMNO,
			// KEY_SMRTIMECARD_DATE, KEY_SMRTIMECARD_TIMESTAMP,
			// KEY_SMRTIMECARD_ENTRYTYPE, KEY_SMRTIMECARD_CREATEDTIME,
			// KEY_SMRTIMECARD_MODIFIEDTIME, KEY_SMRTIMECARD_USER,
			// KEY_SMRTIMECARD_USER, USER_TABLE, KEY_USER_ROWID);
			// String supplier = String.format(TABLE_CREATE_SUPPLIER,
			// SUPPLIER_TABLE, KEY_SUPPLIER_ROWID, KEY_SUPPLIER_NO,
			// KEY_SUPPLIER_CRMNO, KEY_SUPPLIER_NAME,
			// KEY_SUPPLIER_LANDLINE, KEY_SUPPLIER_ADDRESS,
			// KEY_SUPPLIER_CREDITLINE, KEY_SUPPLIER_CREDITTERM,
			// KEY_SUPPLIER_CONTACTPERSON, KEY_SUPPLIER_ISACTIVE,
			// KEY_SUPPLIER_CREATEDTIME, KEY_SUPPLIER_MODIFIEDTIME,
			// KEY_SUPPLIER_USER, KEY_SUPPLIER_USER, USER_TABLE,
			// KEY_USER_ROWID);
			String workplanEntry = String.format(TABLE_CREATE_WORKPLAN_ENTRY,
					WORKPLAN_ENTRY_TABLE, KEY_WORKPLANENTRY_ROWID,
					KEY_WORKPLANENTRY_NO, KEY_WORKPLANENTRY_CRMNO,
					KEY_WORKPLANENTRY_DATE, KEY_WORKPLANENTRY_STATUS,
					KEY_WORKPLANENTRY_AREA, KEY_WORKPLANENTRY_PROVINCE,
					KEY_WORKPLANENTRY_CITY, KEY_WORKPLANENTRY_ACTIVITYTYPE,
					KEY_WORKPLANENTRY_REMARKS, KEY_WORKPLANENTRY_WORKPLAN,
					KEY_WORKPLANENTRY_ACTIVITYQUANTITY,
					KEY_WORKPLANENTRY_BUSINESSUNIT,
					KEY_WORKPLANENTRY_CREATEDTIME,
					KEY_WORKPLANENTRY_MODIFIEDTIME,
					KEY_WORKPLANENTRY_CREATEDBY, KEY_WORKPLANENTRY_STATUS,
					WORKPLAN_ENTRY_STATUS_TABLE, KEY_PICKLISTS_ROWID,
					KEY_WORKPLANENTRY_AREA, AREA_TABLE, KEY_PICKLISTS_ROWID,
					KEY_WORKPLANENTRY_PROVINCE, PROVINCE_TABLE,
					KEY_PROVINCE_ROWID, KEY_WORKPLANENTRY_CITY, CITYTOWN_TABLE,
					KEY_CITYTOWN_ROWID, KEY_WORKPLANENTRY_ACTIVITYTYPE,
					ACTIVITY_TYPE_TABLE, KEY_ACTIVITYTYPE_ROWID,
					KEY_WORKPLANENTRY_WORKPLAN, WORKPLAN_TABLE,
					KEY_WORKPLAN_ROWID, KEY_WORKPLANENTRY_BUSINESSUNIT,
					BUSINESS_UNIT_TABLE, KEY_BUSINESSUNIT_ROWID,
					KEY_WORKPLANENTRY_CREATEDBY, USER_TABLE, KEY_USER_ROWID);
			String workplan = String.format(TABLE_CREATE_WORKPLAN,
					WORKPLAN_TABLE, KEY_WORKPLAN_ROWID, KEY_WORKPLAN_NO,
					KEY_WORKPLAN_CRMNO, KEY_WORKPLAN_FROMDATE,
					KEY_WORKPLAN_TODATE, KEY_WORKPLAN_CREATEDTIME,
					KEY_WORKPLAN_MODIFIEDTIME, KEY_WORKPLAN_CREATEDBY,
					KEY_WORKPLAN_CREATEDBY, USER_TABLE, KEY_USER_ROWID);
			String marketingMaterials = String.format(
					TABLE_CREATE_MARKETING_MATERIALS,
					MARKETING_MATERIALS_TABLE, KEY_MARKETINGMATERIALS_ROWID,
					KEY_MARKETINGMATERIALS_NO, KEY_MARKETINGMATERIALS_CRMNO,
					KEY_MARKETINGMATERIALS_DESCRIPTION,
					KEY_MARKETINGMATERIALS_LASTUPDATE,
					KEY_MARKETINGMATERIALS_TAGS,
					KEY_MARKETINGMATERIALS_BUSINESSUNIT,
					KEY_MARKETINGMATERIALS_ISNEW,
					KEY_MARKETINGMATERIALS_ISACTIVE,
					KEY_MARKETINGMATERIALS_CREATEDTIME,
					KEY_MARKETINGMATERIALS_MODIFIEDTIME,
					KEY_MARKETINGMATERIALS_CREATEDBY,
					KEY_MARKETINGMATERIALS_BUSINESSUNIT, BUSINESS_UNIT_TABLE,
					KEY_BUSINESSUNIT_ROWID, KEY_MARKETINGMATERIALS_CREATEDBY,
					USER_TABLE, KEY_USER_ROWID);
			String jdiMerchandising = String.format(
					TABLE_CREATE_JDI_MERCHANDISING,
					JDI_MERCHANDISING_CHECK_TABLE, KEY_JDIMERCHANDISING_ROWID,
					KEY_JDIMERCHANDISING_NO, KEY_JDIMERCHANDISING_CRMNO,
					KEY_JDIMERCHANDISING_ACTIVITY,
					KEY_JDIMERCHANDISING_PRODUCTBRAND,
					KEY_JDIMERCHANDISING_STATUS,
					KEY_JDIMERCHANDISING_CREATEDTIME,
					KEY_JDIMERCHANDISING_MODIFIEDTIME,
					KEY_JDIMERCHANDISING_CREATEDBY,
					KEY_JDIMERCHANDISING_ACTIVITY, ACTIVITY_TABLE,
					KEY_ACTIVITY_ROWID, KEY_JDIMERCHANDISING_PRODUCTBRAND,
					PRODUCT_TABLE, KEY_PRODUCT_ROWID,
					KEY_JDIMERCHANDISING_STATUS,
					JDI_MERCHANDISING_CHECK_STATUS_TABLE, KEY_PICKLISTS_ROWID,
					KEY_JDIMERCHANDISING_CREATEDBY, USER_TABLE, KEY_USER_ROWID);
			String document = String.format(TABLE_CREATE_DOCUMENT,
					DOCUMENT_TABLE, KEY_DOCUMENT_ROWID, KEY_DOCUMENT_NO,
					KEY_DOCUMENT_CRMNO, KEY_DOCUMENT_TITLE,
					KEY_DOCUMENT_MODULENAME, KEY_DOCUMENT_MODULEID,
					KEY_DOCUMENT_FILENAME, KEY_DOCUMENT_FILETYPE,
					KEY_DOCUMENT_FILEPATH, KEY_DOCUMENT_ISACTIVE,
					KEY_DOCUMENT_CREATEDTIME, KEY_DOCUMENT_MODIFIEDTIME,
					KEY_DOCUMENT_USER, KEY_DOCUMENT_USER, USER_TABLE,
					KEY_USER_ROWID);
			String calendar = String.format(TABLE_CREATE_CALENDAR,
					CALENDAR_TABLE, KEY_CALENDAR_ROWID,
					KEY_CALENDAR_ACTIVITYTYPE, KEY_CALENDAR_DATESTART,
					KEY_CALENDAR_DUEDATE, KEY_CALENDAR_DESCRIPTION,
					KEY_CALENDAR_SUBJECT, KEY_CALENDAR_TIMESTART,
					KEY_CALENDAR_TIMEEND, KEY_CALENDAR_ACTIVITY,
					KEY_CALENDAR_CREATEDTIME, KEY_CALENDAR_MODIFIEDTIME,
					KEY_CALENDAR_USER, KEY_CALENDAR_ACTIVITY, ACTIVITY_TABLE,
					KEY_ACTIVITY_ROWID, KEY_DOCUMENT_USER, USER_TABLE,
					KEY_USER_ROWID);
			String productFocus = String.format(TABLE_CREATE_PRODUCTFOCUS,
					PRODUCT_FOCUS_TABLE, KEY_PRODUCTFOCUS_ROWID,
					KEY_PRODUCTFOCUS_PRODUCT, KEY_PRODUCTFOCUS_ACTIVITY,
					KEY_PRODUCTFOCUS_PRODUCT, PRODUCT_TABLE, KEY_PRODUCT_ROWID,
					KEY_PRODUCTFOCUS_ACTIVITY, ACTIVITY_TABLE,
					KEY_ACTIVITY_ROWID);
			String productSupplier = String.format(
					TABLE_CREATE_PRODUCTSUPPLIER, PRODUCT_SUPPLIER_TABLE,
					KEY_PRODUCTSUPPLIER_ROWID, KEY_PRODUCTSUPPLIER_NO,
					KEY_PRODUCTSUPPLIER_CRMNO,
					KEY_PRODUCTSUPPLIER_PRODUCTBRAND,
					KEY_PRODUCTSUPPLIER_SUPPLIER,
					KEY_PRODUCTSUPPLIER_OTHERREMARKS,
					KEY_PRODUCTFOCUS_ACTIVITY, KEY_PRODUCTSUPPLIER_CREATEDBY,
					KEY_PRODUCTSUPPLIER_CREATEDTIME,
					KEY_PRODUCTSUPPLIER_MODIFIEDTIME,
					KEY_PRODUCTSUPPLIER_SUPPLIER, CUSTOMER_TABLE,
					KEY_CUSTOMER_ROWID, KEY_PRODUCTSUPPLIER_ACTIVITY,
					ACTIVITY_TABLE, KEY_ACTIVITY_ROWID,
					KEY_PRODUCTSUPPLIER_CREATEDBY, USER_TABLE, KEY_USER_ROWID);
			String salesProtocol = String.format(TABLE_CREATE_SALESPROTOCOLS,
					SALES_PROTOCOL_TABLE, KEY_SALESPROTOCOL_ROWID,
					KEY_SALESPROTOCOL_NO, KEY_SALESPROTOCOL_CRMNO,
					KEY_SALESPROTOCOL_BUSINESSUNIT,
					KEY_SALESPROTOCOL_DESCRIPTION,
					KEY_SALESPROTOCOL_LASTUPDATE, KEY_SALESPROTOCOL_TAGS,
					KEY_SALESPROTOCOL_PROTOCOLTYPE, KEY_SALESPROTOCOL_ISACTIVE,
					KEY_SALESPROTOCOL_CREATEDBY, KEY_SALESPROTOCOL_CREATEDTIME,
					KEY_SALESPROTOCOL_MODIFIEDTIME,
					KEY_SALESPROTOCOL_BUSINESSUNIT, BUSINESS_UNIT_TABLE,
					KEY_BUSINESSUNIT_ROWID, KEY_SALESPROTOCOL_PROTOCOLTYPE,
					SALES_PROTOCOL_TYPE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_SALESPROTOCOL_CREATEDBY, USER_TABLE, KEY_USER_ROWID);

			db.execSQL(user);
			db.execSQL(TABLE_CREATE_ACTIVITY);
			db.execSQL(activityType);
			db.execSQL(businessUnit);
			db.execSQL(competitorProduct);
			db.execSQL(competitorProductStockCheck);
			// db.execSQL(competitor);
			db.execSQL(customerContact);
			db.execSQL(customer);
			db.execSQL(eventProtocol);
			db.execSQL(jdiProductStockCheck);
			db.execSQL(marketingIntel);
			db.execSQL(product);
			db.execSQL(projectRequirement);
			db.execSQL(smr);
			// db.execSQL(smrTimecard);
			// db.execSQL(supplier);
			db.execSQL(workplanEntry);
			db.execSQL(workplan);
			db.execSQL(marketingMaterials);
			db.execSQL(jdiMerchandising);
			db.execSQL(document);
			db.execSQL(calendar);
			db.execSQL(productFocus);
			db.execSQL(productSupplier);
			db.execSQL(salesProtocol);

			// Picklists

			String activityProjectCategory = String.format(
					TABLE_CREATE_PICKLISTS, ACTIVITY_PROJECT_CATEGORY_TABLE,
					KEY_PICKLISTS_ROWID, KEY_PICKLISTS_NAME);
			String activityProjectStage = String.format(TABLE_CREATE_PICKLISTS,
					ACTIVITY_PROJECT_STAGE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String activitytypeCategory = String.format(TABLE_CREATE_PICKLISTS,
					ACTIVITYTYPE_CATEGORY_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			// String activitytypeType = String.format(
			// TABLE_CREATE_PICKLISTS_W_DEPENDENCIES,
			// ACTIVITYTYPE_TYPE_TABLE, KEY_ACTIVITYTYPE_TYPE_ROWID,
			// KEY_ACTIVITYTYPE_TYPE_NAME, KEY_ACTIVITYTYPE_TYPE_CATEGORY);
			String competitorproductStockStatus = String.format(
					TABLE_CREATE_PICKLISTS,
					COMPETITOR_PRODUCT_STOCKSTATUS_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String customercontactPosition = String.format(
					TABLE_CREATE_PICKLISTS, CUSTOMERCONTACT_POSITION_TABLE,
					KEY_PICKLISTS_ROWID, KEY_PICKLISTS_NAME);
			String customerSize = String.format(TABLE_CREATE_PICKLISTS,
					CUSTOMER_SIZE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String customerType = String.format(TABLE_CREATE_PICKLISTS,
					CUSTOMER_TYPE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String customerRecordStatus = String.format(TABLE_CREATE_PICKLISTS,
					CUSTOMER_RECORD_STATUS_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String eventType = String.format(TABLE_CREATE_PICKLISTS,
					EVENT_TYPE_TABLE, KEY_PICKLISTS_ROWID, KEY_PICKLISTS_NAME);
			String jdiMerchandisingCheckStatus = String.format(
					TABLE_CREATE_PICKLISTS,
					JDI_MERCHANDISING_CHECK_STATUS_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String jdiProductStockStatus = String.format(
					TABLE_CREATE_PICKLISTS, JDI_PRODUCT_STOCK_STATUS_TABLE,
					KEY_PICKLISTS_ROWID, KEY_PICKLISTS_NAME);
			String projectRequirementsType = String.format(
					TABLE_CREATE_PICKLISTS, PROJECT_REQUIREMENTS_TYPE_TABLE,
					KEY_PICKLISTS_ROWID, KEY_PICKLISTS_NAME);
			String smrTimecardEntry = String.format(TABLE_CREATE_PICKLISTS,
					SMR_TIMECARD_ENTRY_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String workplanentryStatus = String.format(TABLE_CREATE_PICKLISTS,
					WORKPLAN_ENTRY_STATUS_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String salesProtocolType = String.format(TABLE_CREATE_PICKLISTS,
					SALES_PROTOCOL_TYPE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			String actEndUserActivityType = String.format(
					TABLE_CREATE_PICKLISTS,
					ACTIVITY_ENDUSER_ACTIVITYTYPE_TABLE, KEY_PICKLISTS_ROWID,
					KEY_PICKLISTS_NAME);
			db.execSQL(activityProjectCategory);
			db.execSQL(activityProjectStage);
			db.execSQL(activitytypeCategory);
			// db.execSQL(activitytypeType);
			db.execSQL(competitorproductStockStatus);
			db.execSQL(customercontactPosition);
			db.execSQL(customerSize);
			db.execSQL(customerType);
			db.execSQL(customerRecordStatus);
			db.execSQL(eventType);
			db.execSQL(jdiMerchandisingCheckStatus);
			db.execSQL(jdiProductStockStatus);
			db.execSQL(projectRequirementsType);
			db.execSQL(smrTimecardEntry);
			db.execSQL(workplanentryStatus);
			db.execSQL(salesProtocolType);
			db.execSQL(actEndUserActivityType);

			// Location
			String area = String.format(TABLE_CREATE_PICKLISTS, AREA_TABLE,
					KEY_PICKLISTS_ROWID, KEY_PICKLISTS_NAME);
			String province = String.format(
					TABLE_CREATE_PICKLISTS_W_DEPENDENCIES, PROVINCE_TABLE,
					KEY_PROVINCE_ROWID, KEY_PROVINCE_NAME, KEY_PROVINCE_AREA);
			String cityTown = String.format(
					TABLE_CREATE_PICKLISTS_W_DEPENDENCIES, CITYTOWN_TABLE,
					KEY_CITYTOWN_ROWID, KEY_CITYTOWN_NAME,
					KEY_CITYTOWN_PROVINCE);
			db.execSQL(area);
			db.execSQL(province);
			db.execSQL(cityTown);
		}
		// private void dropTable(SQLiteDatabase db, String tableName) {
		// db.execSQL(String.format("DROP TABLE IF EXISTS %s", tableName));
		// }

		// private void dropTable(SQLiteDatabase db, String[] tableNames) {
		// for (String table : tableNames) {
		// db.execSQL(String.format("DROP TABLE IF EXISTS %s", table));
		// }
		// }
	}

	// private List<String> GetColumns(SQLiteDatabase db, String tableName) {
	// List<String> ar = null;
	// Cursor c = null;
	// try {
	// c = db.rawQuery("select * from " + tableName + " limit 1", null);
	// if (c != null) {
	// ar = new ArrayList<String>(Arrays.asList(c.getColumnNames()));
	// }
	// } catch (Exception e) {
	// Log.v(tableName, e.getMessage(), e);
	// e.printStackTrace();
	// } finally {
	// if (c != null)
	// c.close();
	// }
	// return ar;
	// }
	//
	// private String join(List<String> list, String delim) {
	// StringBuilder buf = new StringBuilder();
	// int num = list.size();
	// for (int i = 0; i < num; i++) {
	// if (i != 0)
	// buf.append(delim);
	// buf.append((String) list.get(i));
	// }
	// return buf.toString();
	// }
}
