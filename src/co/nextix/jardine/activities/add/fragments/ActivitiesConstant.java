package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.DocumentRecord;

public class ActivitiesConstant {
	public static ActivityRecord ACTIVITY_RECORD = null;
	public static long ACTIVITY_ID = 0;
	public static List<CustomerContactRecord> ACTIVITY_CUSTOMER_CONTACT_MAIN_LIST = new ArrayList<CustomerContactRecord>();
	public static List<CustomerContactRecord> ACTIVITY_CUSTOMER_CONTACT_FILTERED = new ArrayList<CustomerContactRecord>();;
	public static CustomerRecord ACTIVITY_CUSTOMER_RECORD = null;
	
	public static List<DocumentRecord> ACTIVITY_DOCUMENT_MAIN_LIST = new ArrayList<DocumentRecord>();
}
