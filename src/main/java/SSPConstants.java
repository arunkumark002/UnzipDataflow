public class SSPConstants {
	
	 /**
     * Private Constructor - This class should not be instantiated.
     */
    private SSPConstants() {
    }

	/*
	 * Common Fields
	 */

	public static final String APR_BAD_NAMED_OUTPUT = "Bad";

	public static final String APR_GOOD_NAMED_OUTPUT = "Good";

	public static final String APR_DATASOURCE = "FPASSAPR";

	public static final String SSP_CR_B2C_HEADER = "RECORD_ID";

	/*
	 * Data Fields
	 */

	public static final String RAW_PAYLOAD_ERR_DETAILS = "This data does not match all fields as per the specification";

	public static final String CHRAGE_RECORDS_B2C_DATATYPE = "Charge Records Data -";

	public static final String NOTIFICATION_STATUS_B2C_DATATYPE = "Notification Status Data -";

	public static final String DEBIT_MEMO_DATATYPE = "Debit Memo Data -";

	public static final String FULFILLMENT_B2C_DATATYPE = "Fulfillment B2C Data -";

	public static final String FULFILLMENT_B2B_DATATYPE = "Fulfillment B2B Data -";

	public static final String CHRAGE_BALANCE_B2C_DATATYPE = "Charge Balance Data -";

	public static final String REQUIRED_FIELDS = "MANDATORY Fields are missing";

	public static final String PROCESSING_EXCEPTION = "Exception occurred while processing";

	public static final String PAYLOAD_SHAKEY_ERR_DETAILS = "Error generating SHA KEY";

	public static final String TEMP_SUBSCRIPTION_ANALYTICS = "Temp subscription analytics Data -";

	public static final String AUTOFULFILLMENT_B2C_DATATYPE = "AutoFulfillment B2C Data -";
	
	public static final String CONST_AF_ID_ERROR_MESSAGE = "AutoFulfillment ID Validation Failed. ";
	
	/*
	 * Datatype validation
	 */

	public static final String EXCEPTION_INTEGER_FIELD = "Exception occurred while handling number";

	public static final String EXCEPTION_STRING_FIELD = "Exception occurred while handling String";

	public static final String EXCEPTION_TS_FIELD = "Exception occurred while handling timestamp";

	public static final String REQUIRED_ARGUMENT_MISSING = "Required argument missing";

	public static final String TYPE_REQUIRED_FIELDS = "MANDATORY Field Type is missing";

	public static final String CONST_VIN_ERROR_MESSAGE = "VIN  Validation Failed. ";


	// SSPTempSubscription constant variables
	public static final String VIN = "vin";

	public static final String ID = "id";

	public static final String PRODUCT_SKU = "productSku";
	
	public static final String START_DATE = "startDate";
	
	public static final String END_DATE = "endDate";
	
	public static final String CREATE_TIMESTAMP = "createTs";
	
	public static final String UPDATE_TIMESTAMP = "updateTs";
	
	public static final String STATE = "state";
	
	public static final String PRODUCT_TYPE = "productType";
	
	public static final String TYPE = "type";
	
	public static final String COUNTRY = "country";
	
	public static final String TRANSACTION_TYPE = "transactionType";
	
	public static final String CUSTOMER_ID = "customerId";
	
	public static final String SUBSCRIPTION_ID = "subscriptionId";
	
	public static final String SERVICE_ACTIVATION_DATE = "serviceActivationDate";
	
	public static final String FREE_TRIAL_ACTIVATION_DATE = "freeTrialActivationDate";
	
	public static final String FREE_TRIAL_END_DATE = "freeTrialEndDate";
	
	
	//AutoFulFillment Variable Constants
	
	public static final String AF_ID ="id";
		
	public static final String AF_STATUS = "status";

	public static final String AF_PRODUCT_RATE_PLAN_ID = "productRatePlanID";
		
	public static final String AF_VIN ="vin";
		
	public static final String AF_CUSTOMER_ID = "customerId";
		
	public static final String AF_SUBSCRIPTION_ID = "subscriptionId";
		
	public static final String AF_PRODUCT_SKU = "productSku";
	
	public static final String AF_FULFILLMENT_SKU = "fulfillmentSku";
		
	public static final String AF_EVENT_TYPE="eventType";
		
	public static final String AF_COUNTRY ="country";
		
	public static final String AF_EVENT_ID = "eventId";
		
	public static final String AF_START_DATE = "startDate";
		
	public static final String AF_END_DATE ="endDate";
		
	public static final String AF_SERVICE_ACTIVATION_DATE = "serviceActivationDate";
		
	public static final String AF_WARRANTY_RECONCILE ="warrantyReconcile";
		
	public static final String AF_CREATE_TS = "createTs";
		
	public static final String AF_UPDATE_TS = "updateTs";
		
	public static final String AF_CAPABLE_FEATURES = "capableFeatures";
		
	public static final String AF_TRACE_ID = "traceId";
		
	public static final String AF_RENEWABLE = "renewable";
		
	public static final String AF_VERSION = "version";
		
	public static final String AF_CAPABILITY_RECONCILE = "capabilityReconcile";
		
}
