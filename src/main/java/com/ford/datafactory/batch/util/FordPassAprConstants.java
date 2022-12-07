package com.ford.datafactory.batch.util;


public class FordPassAprConstants {
	
	 /**
     * Private Constructor - This class should not be instantiated.
     */
    private FordPassAprConstants() {
    }
    
    
	/*
	 * Email Constants
	 */
    public static final String APR_NAMESPACE_EMAIL_COMMON_SETTINGS = "email";
    
    public static final String APR_PROPERTY_EMAIL_ADDRESSES = "EmailAddresses";
    
    /*
	 * Common Fields
	 */
	
    public static final String APR_BAD_NAMED_OUTPUT = "Bad";
	 
	public static final String APR_GOOD_NAMED_OUTPUT = "Good";

	public static final String APR_DATASOURCE = "FPASSAPR";
	
	public static final String APR_HEADER = "H";
	
	public static final String APR_TAIL = "T";
	
	
	
	/*
	 * Data Files
	 */
	public static final String ACT_ATTR_VAL_DATATYPE = "Winning Moves Custom Activity Attribute Values Data - ";
	
	public static final String QST_ATTR_VAL_DATATYPE = "Winning Moves Questionnaire Attribute Data - ";
	
	public static final String CUST_ATTR_DEF_DATATYPE = "CustomAttributesDef Data - ";
	
	public static final String SURVEY_ANS_HEADER_DATATYPE = "Winning Moves Survey Answers Header Data - ";
	
	public static final String SURVEY_ANS_DATATYPE = "Winning Moves Survey Answers Data - ";
	
	public static final String SURVEY_QST_DATATYPE = "Winning Moves Survey Questions Data - ";
	
	public static final String REWARD_DATATYPE = "Reward Data - ";
	
	public static final String LOTTERIES_DATATYPE = "Winning Moves Lotteries Data - ";
	
	public static final String QUESTIONNAIRE_QUESTIONS_ATTRIBUTES_DATATYPE = "Winning Moves QuestionnaireQuestionsAttributes Data - ";
	
	public static final String LOTTERY_PARTICIPANTS_DATATYPE = "Winning Moves Lottery Participants Data - ";
	
	public static final String LOTTERY_WINNERS_DATATYPE = "Winning Moves Lottery Winners Data - ";
	
	public static final String CV_DATATYPE = "Counter Values Data - ";
	
	public static final String ACC_DATATYPE = "Accounts data - ";
	
	public static final String TRANSACTION_BADGES_DATATYPE = "Transaction badges data - ";
	
	public static final String DICITEM_DATATYPE = "DictionaryItem data - ";
	
	public static final String TRANSACTION_DATATYPE = "Transaction data - ";
	
	public static final String TRANS_PROMOTION_COUNTERS_DATATYPE = "Transaction Promotion Counters Data - ";

	public static final String TRANS_PROMOTION_POINTS_DATATYPE = "Transaction Promotion Points Data - ";
	
	public static final String TRANS_BASKET_ITEM_REDEMPTION_DATATYPE = "Transaction BasketItem Redemption Data - ";
	
	public static final String TRANS_BASKET_ITEM_EARN_PRODUCTS_DATATYPE = "Transaction BasketItem Earn Products Data - ";
	
	public static final String CUSTOM_ATTRIBUTES_DYNAMIC_DATATYPE = "Custom Attributes Dynamic Data - ";
	
	public static final String USERS_DATATYPE = "Users data - ";
	
    public static final String VIN_DATATYPE = "Vin data - ";
	
	public static final String COUPONS_DATATYPE = "Coupons data - ";
	
	public static final String TRANSACTION_POINTS_BALANCES_DATATYPE = "Transaction points balances data - ";
	
	public static final String NOTIFICATIONS_DATATYPE = "Notifications data - ";
	
	public static final String TRANSACTION_ATTRIBUTES_DATATYPE = "Transaction Attributes Data - ";
	
	public static final String TRANSACTION_CONVERTED_POINTS = "Transaction converted points Data - ";
	
	public static final String TRANSACTION_POINTS_AGGREGATE = "Transaction points aggregate Data - ";
	
	public static final String POINTS_BALANCE_DATATYPE = "Points Balance Data - ";
		
	public static final String TRANSACTION_COUPON_REDEMPTION_DATATYPE = "Transaction Coupon Redemption Data - ";
	
	public static final String TRANSACTION_RULE_DATATYPE = "Transaction Rule Data - ";
	
	public static final String LOCATION_DATATYPE = "Location Data - ";
	
	public static final String TRANS_RULE_DATATYPE = "Transaction Rule data - ";
	
	public static final String ACTIVITIES_DATATYPE = "Activities data - ";
	
	public static final String SURVEYS_DATATYPE = "Surveys data - ";

	public static final String BADGES_CUSTOMER_DATATYPE = "Badges Customer data - ";
	
	public static final String BADGES_DATATYPE = "Badges data - ";
	
	public static final String POINT_TYPES_DATATYPE = "Point Types Data - ";
	
	public static final String PARTNERS_DATATYPE = "Partners Data - ";
	
	public static final String RAW_PAYLOAD = "RAW_PAYLOAD";
	
	public static final String RAW_PAYLOAD_ERR_DETAILS = "This data does not match all fields as per the specification";
	
	public static final String PAYLOAD_SHAKEY_ERR_DETAILS = "Error generating SHA KEY";
	
	public static final String REQUIRED_FIELDS = "MANDATORY Fields are missing";
	
	public static final String REQUIRED_HEADER_TIMESTAMP_MISSING = "Header timestamp is missing or not valid as per the specification";
	
	public static final String PROCESSING_EXCEPTION = "Exception occurred while processing";
	
	public static final String SHA_KEY_GENERATION_DETAILS= "Mandatory fields required to generate Sha Key are null";
	
	public static final String TRANSACTION_TTP_ERR_DETAILS="TTP_CODE doesnt lookup values";
	
	public static final String CHN_TRANS_PROMOTION_COUNTERS_DATATYPE = "China Transaction Promotion Counters Data - ";
	
	public static final String CHN_CUSTOM_ATTRIBUTES_DYNAMIC_DATATYPE = "China Custom Attributes Dynamic Data - ";
	
	public static final String CHN_POINT_TYPES_DATATYPE = "China Point Types Data - ";

	public static final String OAR_MIGRATED_ACCOUNTS_DATATYPE = "OAR Migrated Accounts Data - ";
	
	/*
	 * Datatype validation
	 */
	public static final String EXCEPTION_INTEGER_FIELD = "Exception occurred while handling number";
	
	public static final String EXCEPTION_STRING_FIELD = "Exception occurred while handling String";
	
	public static final String EXCEPTION_HEADER_TS_FIELD_NAME = "Header Timestamp";

	public static final String EXCEPTION_HEADER_TS_FIELD = "Exception occurred while handling header timestamp";
	
	public static final String EXCEPTION_PARTITIONHEADER_TS_FIELD = "Exception occurred while partitioning header timestamp";
	
	public static final String EXCEPTION_TS_FIELD = "Exception occurred while handling timestamp";
	
	public static final String EXCEPTION_PC_FIELD ="Exception occurred while handling partition year";
	
	public static final String EXCEPTION_COMPLEX_FIELD ="Exception occurred while handling complex field";
	
	public static final String BADGES_CUSTOMER_PROPERTY_VALUE="badgesCustomerCtrCode";
	
	public static final String TRANSACTION_BADGES_PROPERTY_VALUE="transactionBadgesCtrCode";
	
	public static final String BADGES_PROPERTY_VALUE="badgesCtrCode";
	
	/*
	 * lookup table
	 */
	public static final String [] NA_EU_CODES = {"USA","GBR","DEU","CAN","FRA","ITA","ESP","BRA"};
	public static final String NA_EU_VALUE= "NA_EU";
	public static final String CHN_VALUE = "CHN";
	public static final String USA_VALUE = "USA";
	public static final String NA_EU_FILE_PATTERN="CLM_FORD_";
	public static final String CHN_FILE_PATTERN="CLM_FORD_CHN_";
	//public static final String REQUIRED_HEADER_TIMESTAMP_COUNTRY_CODE_MISSING = "Header timestamp is missing or not valid as per the specification";
	/*
	 *  winningMoves country code validation
	 */
	public static final String EXCEPTION_COUNTRYCODE_FIELD = "Country code obtained from input is not as expected";

	public static final String PROMOTION_DATATYPE = "Promotion data - ";
	
	public static final String EXCEPTION_TEN_REVERSE_TYPE_FIELD = "Value differs from mentioned value";
	
	public static final String TRANSACTION_RECOG_TIERS_DATATYPE = "Transaction Recognition Tiers data - ";
	
	public static final String RECOG_TIERS_DATATYPE = "Recognition Tiers data - ";
	
	public static final String RECOGNITION_TIER_CUSTOMER_DATATYPE = "Recognition Tier Customer Data - ";
	
	public static final String LEADERBOARDS_DATATYPE = "Leaderboards Data - ";
	
	public static final String TRANSACTION_ERRORS_DATATYPE = "Transaction Errors Data - ";

	public static final String LOGISTIC_REWARDS_DATATYPE = "Logistic Rewards Data - ";

	public static final String ASSIGNMENTS_PROPERTY_VALUE = "Assignments data - ";

	public static final String LOGISTICWAREHOUSESTOCKHISTORY_DATATYPE = "LogisticWarehouseStockHistory Data - ";
	
	public static final String LOGISTICWAREHOUSESTOCKS_DATATYPE = "Logistic Warehouse Stocks Data - ";

	public static final String ORDERS_DATATYPE = "orders data type - ";

	public static final String ORDERS_HISTORY_DATATYPE = "orders history data type - ";


}
