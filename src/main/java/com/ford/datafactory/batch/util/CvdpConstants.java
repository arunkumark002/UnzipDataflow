package com.ford.datafactory.batch.util;

// ******************************************************************************
// * Copyright (c) 2008 Ford Motor Company. All Rights Reserved.
// * Original author: Ford Motor Company J2EE Center of Excellence
// *
// * $Workfile:   CvdpConstants.java  $
// * $Revision:   1.8  $
// * $Author:   jburnard  $
// * $Date:   Mar 02 2011 23:43:56  $
// *
// ******************************************************************************


/**
 * This class contains constants used by CVDP presentation layer.Added temp to DR issue.
 *
 * @since v. 1.0
 */
public class CvdpConstants {

    /** The Class Name used for Logging */
    private static final String CLASS_NAME = CvdpConstants.class.getName();

    /**
     * Private Constructor - This class should not be instantiated.
     */
    private CvdpConstants() {
    }

    /*======================================================================
     *  System Wide Constants
     *======================================================================*/

    public static final String PROJECT_PATH = "projectPath";
    public static final String GIVIS_PERSISTANCE_CONFIG = "GIVISPersistenceConfig";

    public static final String ENVIRONMENT_SPECIFIC_PROPERTIES_FILE =
            "Environment.properties";

    public static final String DATABASE_TRUE = "Y";
    public static final String DATABASE_FALSE = "N";

    public static final String CDSID_SYSTEM = "SYSTEM";

    public static final String CHARSET = "UTF-8";

    public static final String HBASE_TABLE_SECTION_DATA = "data";

    public static final String APPLICATION_JSON = "application/json";
    public static final String EXT_TMP = ".tmp";
    public static final String WILDCARD_FILENAME = "*FILENAME*";
    public static final String WILDCARD_FILETYPE = "*FILETYPE*";
    public static final String WILDCARD_TIMESTAMP = "*TIMESTAMP*";
    public static final String WILDCARD_TIMESTAMP_NEW = "*NEW_TIMESTAMP*";
    /* 01/18/2018
     * Adding below constants related to YYYY,MM,DD to support replacing year,month and data values in file path
     * */
    public static final String WILDCARD_YEAR = "*YYYY*";
    public static final String WILDCARD_MONTH = "*MM*";
    public static final String WILDCARD_DATE = "*DD*";
    /*01/18/2018*/
    public static final String TIMESTAMP = "TIMESTAMP";

    public static final String FILE_TYPE_EV = "EV";
    public static final String FILE_TYPE_EV2 = "EV2";
    public static final String FILE_TYPE_VHR = "VHR";
    public static final String FILE_TYPE_VIN = "VIN";
    public static final String FILE_TYPE_DEALER_WIFI = "DealerWifi";
    public static final String FILE_TYPE_TRANSFORM = "TTI";
    public static final String FILE_TYPE_APPUSAGE = "AUD";
    public static final String FILE_TYPE_NOT_APPLICABLE = "N/A";
    public static final String FILE_TYPE_TCU2G = "TCU2G";
    public static final String FILE_TYPE_TCU3G = "TCU3G";
    public static final String FILE_TYPE_BDD = "BDD";
    public static final String FILE_TYPE_ARMR_RAW = "ARMR_RAW";
    public static final String FILE_TYPE_VHA = "VHA";

    public static final String PROCESS_RECIEVE = "RECV";
    public static final String PROCESS_TRANSFORM = "TRANS";
    public static final String PROCESS_CLEANUP = "CLEAN";

    public static final String FAILURE_TYPE_UNEXPECTED = "UNEXP";
    public static final String FAILURE_TYPE_METRICS = "METRC";
    public static final String FAILURE_TYPE_QUEUE_NOT_FOUND = "QNTFD";
    public static final String FAILURE_TYPE_QUEUE_STATUS = "QSTAT";
    public static final String FAILURE_TYPE_CONSUME_FAILED = "CONFL";
    public static final String FAILURE_TYPE_FILE_NOT_PROCESSED = "OLDFL";
    public static final String FAILURE_TYPE_QUEUE_NOT_PROCESSED = "OLDQU";
    public static final String FAILURE_TYPE_FILE_DELETE_FAILED = "DELFL";
    public static final String FAILURE_TYPE_NO_DATA_RECEIVED = "NODAT";
    public static final String FAILURE_TYPE_VHR_NO_DATA = "VHRNO";
    public static final String FAILURE_TYPE_VHR_TOO_MUCH_DATA = "VHRTM";
    public static final String FAILURE_TYPE_TOO_MUCH_DATA = "TMDAT";
    public static final String FAILURE_TYPE_EV_NO_DATA = "EVNO";
    public static final String FAILURE_TYPE_EV2_NO_DATA = "EV2NO";
    public static final String FAILURE_TYPE_INVALID_MESSAGE_TYPE = "INVMT";
    public static final String FAILURE_TYPE_CC_NO_DATA = "CCNO";
    public static final String FAILURE_TYPE_CC_NO_MESSAGE_FOUND = "CCNMF";
    public static final String FAILURE_TYPE_CC_MARKER_BYTES_NOT_FOUND = "CCMBN";
    public static final String FAILURE_TYPE_INVALID_CHARACTERS = "IVCHR";
    public static final String FAILURE_TYPE_AUD_NO_DATA = "AUDNO";
    public static final String FAILURE_TYPE_DECODE_FAILED = "DCDFL";

    public static final String EMAIL_FREQUENCY_ALWAYS = "AL";
    public static final String EMAIL_FREQUENCY_HOURLY = "HR";
    public static final String EMAIL_FREQUENCY_DAILY = "DY";
    public static final String EMAIL_FREQUENCY_WEEKLY = "WK";
    public static final String EMAIL_FREQUENCY_NEVER = "NV";

    public static final String SOURCE_SYSTEM_APPLINK_POLICY = "AppPolicy";
    public static final String SOURCE_SYSTEM_ACCENTURE = "Accenture";
    public static final String SOURCE_SYSTEM_AIRBIQUITY = "Airbiquity";
    public static final String SOURCE_SYSTEM_CVDP = "CVDP";

    public static final String FILE_STATUS_RECEIVE_SUCCESSFUL = "RecvGood";
    public static final String FILE_STATUS_TRANSFORMATION_INPROCESS = "TransInpro";
    public static final String FILE_STATUS_TRANSFORMATION_SUCCESSFUL = "TransGood";
    public static final String FILE_STATUS_TRANSFORMATION_FAILED = "TransFail";
    public static final String FILE_STATUS_TRANSFORMATION_REPROCESS = "TransRepro";
    public static final String FILE_STATUS_TRANSFORMATION_CANCELED = "TransCancl";
    public static final String FILE_STATUS_CONSUME_SUCCESSFUL = "ConsmGood";
    public static final String FILE_STATUS_CONSUME_FAILED = "ConsmFail";

    public static final String ENCODING_TYPE_NOT_APPLICABLE = "NA";
    public static final String ENCODING_TYPE_C1MCA = "C1MCA";
    public static final String ENCODING_TYPE_CGEA = "CGEA";

    public static final Integer MAX_LENGTH_VIN = 17;
    public static final Integer MAX_LENGTH_VIN_HASH = 64;
    public static final Integer MAX_LENGTH_UNIQUE_ID = 15;
    public static final Integer MAX_LENGTH_CLASSNAME = 100;
    public static final Integer MAX_LENGTH_METHODNAME = 50;
    public static final Integer MAX_LENGTH_MESSAGEDETAILS = 2000;
    public static final String HIVE_DEFAULT_PARTITION_DATE = "1970-01-01";
    public static final String HIVE_DEFAULT_DELIMITER = "\001";
    public static final String HIVE_UNKNOWN_PARTITION = "UNKNOWN";
    public static final String HIVE_MULTI_PARTITION = "MULTI";
    public static final String R4H_VENDOR_NAME = "AWS";
    public static final Integer BASE16 = 16;
    public static final Integer twelve = 12;

    public static final Integer BASE08 = 8;
    public static final Integer zero = 0;
    public static final Integer one = 1;
    public static final Integer two = 2;
    public static final Integer three = 3;
    public static final Integer four = 4;
    public static final Integer five = 5;
    public static final Integer six = 6;
    public static final Integer seven = 7;
    public static final Integer eight = 8;
    /*======================================================================
     *  Session/Request Constants
     *======================================================================*/

    public static final String ENV_PROPERTY_SAVE_ENDPOINT = "SAVEUrl";

    /*======================================================================
     *  ECLIPSELINK Named Actions
     *======================================================================*/

    public static final String ECLIPSELINK_COUNT_QUEUE_BY_FILETYPE_AND_MINFILERECEIVEDDATETIME =
            "countQueueByFileTypeAndMinFileReceivedDateTime";
    public static final String ECLIPSELINK_QUERY_QUEUE_BY_FILETYPE_AND_STATUS_AND_MAXSTATUSDATETIME =
            "queryQueueByFileTypeAndStatusAndMaxStatusDateTime";
    public static final String ECLIPSELINK_COUNT_QUEUE_BY_FILETYPE_AND_STATUS_AND_MAXSTATUSDATETIME =
            "countQueueByFileTypeAndStatusAndMaxStatusDateTime";
    public static final String ECLIPSELINK_COUNT_QUEUE_BY_FILETYPE_AND_BATCHID =
            "queryQueueByFileTypeAndBatchId";
    public static final String ECLIPSELINK_DELETE_MESSAGELOG_BY_FILETYPE_AND_MAXDATETIME =
            "deleteMessageLogByFileTypeAndMaxDateTime";
    public static final String ECLIPSELINK_DELETE_METRICS_BY_FILETYPE_AND_MAXDATETIME =
            "deleteMetricsByFileTypeAndMaxDateTime";

    /*======================================================================
     *  Environment Properties
     *======================================================================*/
    public static final String PROPERTY_GROUP_ENV = "environment";
    public static final String PROPERTY_GROUP_DYNAMIC = "dynamic";
    public static final String PROPERTY_GROUP_COMMON_ENV = "commonEnvironment";
    public static final String PROPERTY_GROUP_COMMON_DYNAMIC = "commonDynamic";

    public static final String ENV_PROPERTY_OBSCURE_PII = "obscurePii";
    public static final String ENV_PROPERTY_DYNAMIC_PROPERTIES_PATH_SEPARATOR =
            "pathSeparator";
    public static final String ENV_PROPERTY_DYNAMIC_PROPERTIES_PATH = "dynamicPropertiesPath";
    public static final String ENV_PROPERTY_DYNAMIC_PROPERTIES_KEYPATH = "keyPath";

    public static final String ENV_PROPERTY_KERBEROS_PRINCIPAL = "kerberosPrincipal";
    public static final String ENV_PROPERTY_KEYTAB_FILE = "keytabFile";
    public static final String ENV_PROPERTY_KRB5_FILE = "krb5File";
    public static final String ENV_PROPERTY_JAAS_CONF_FILE = "jaasConfFile";
    public static final String ENV_PROPERTY_JAAS_LOGIN_CONTEXT = "jaasLoginContext";
    public static final String ENV_PROPERTY_HBASE_NAMESPACE = "hbaseNamespace";
    public static final String ENV_PROPERTY_HIVE_URL = "hiveUrl";
    public static final String ENV_PROPERTY_HIVE_DATABASE_NAME = "hiveDatabaseName";
    public static final String ENV_PROPERTY_HIVE_SITE_XML = "hiveSiteXml";
    public static final String ENV_PROPERTY_MAPRED_SITE_URL = "mapredSiteXml";
    public static final String ENV_PROPERTY_YARN_SITE_URL = "yarnSiteXml";
    public static final String ENV_PROPERTY_CORE_SITE_URL = "coreSiteXml";
    public static final String ENV_PROPERTY_HDFS_SITE_URL = "hdfsSiteXml";
    public static final String ENV_PROPERTY_HBASE_SITE_URL = "hbaseSiteXml";
    public static final String ENV_PROPERTY_USE_THRIFT_SASL = "useThriftSASL";
    public static final String ENV_PROPERTY_THRIFT_URL = "thriftURL";

    public static final String ENV_GROUP_PURGEDATADRIVER = "purgeDataDriver";
    public static final String ENV_GROUP_HIVEMergeSmallFilesDriver = "hiveMergeSmallFilesDriver";
    public static final String ENV_GROUP_HIVEGDPRDRIVER = "hiveGDPRDriver";
    public static final String ENV_GROUP_PURGEINVALIDTABLEDRIVER = "purgeInvalidTableDriver";
    public static final String ENV_GROUP_HIVE_IMPORT_EXPORT_DRIVER = "hiveImportExport";
    public static final String ENV_GROUP_CCGLOADMASTERDRIVER = "ccgLoadMasterDriver";
    public static final String ENV_GROUP_LOADPART2SPECDRIVER = "loadPart2SpecDriver";
    public static final String ENV_GROUP_LOADSCAVVINDRIVER = "loadScavVinDriver";
    public static final String ENV_GROUP_TRANSFORMVDDSCANSIGNALRULESDRIVER = "transformVddsCanSignalRulesDriver";
    public static final String ENV_GROUP_DIDDECODER = "didDecoder";
    public static final String ENV_GROUP_HIVEPARM = "hiveparm";
    public static final String ENV_GROUP_TEZ = "tez";
    public static final String ENV_GROUP_MR = "mr";

    public static final String ENV_PROPERTY_ENUMERATION_TABLE = "enumerationTable";
    public static final String ENV_PROPERTY_ENUMERATION_HDFS_FOLDER = "enumerationHdfsFolder";
    public static final String ENV_PROPERTY_ENUMERATION_NAS_FOLDER = "enumerationNasFolder";
    public static final String ENV_PROPERTY_ENUMERATION_FILE_NAME = "enumerationFileName";

    public static final String ENV_PROPERTY_METRICS_TABLE_PATH = "metricsTablePath";

    public static final String ENV_PROPERTY_CVDP_SQLSERVER_URL = "cvdpSqlServerUrl";
    public static final String ENV_PROPERTY_CVDP_SQLSERVER_USERNAME = "cvdpSqlServerUserName";
    public static final String ENV_PROPERTY_CVDP_SQLSERVER_ENCRYPTED_PASSWORD = "cvdpSqlServerEncryptedPassword";
    public static final String ENV_GROUP_CPA = "cpa";

    /*======================================================================
     *  DynaProp Namespaces/PropertyGroups/Properties
     *======================================================================*/

    public static final String DYNAPROP_NAMESPACE_EMAIL_COMMON_SETTINGS = "email";
    public static final String DYNAPROP_PROPERTY_ENVIRONMENT = "Environment";
    public static final String DYNAPROP_PROPERTY_EMAIL_ENABLED = "EmailEnabled";
    public static final String DYNAPROP_PROPERTY_REPLY_EMAIL_ADDRESS = "ReplyEmailAddress";
    public static final String DYNAPROP_PROPERTY_STMP_HOST_SERVER = "SmtpHostServer";

    public static final String DYNAPROP_NAMESPACE_WEB_SERVICES = "WebServices";

    public static final String DYNAPROP_NAMESPACE_SCHEDULED_JOBS = "ScheduledJobs";
    public static final String DYNAPROP_NAMESPACE_SCHEDULE = "Schedule";
    public static final String DYNAPROP_PROPERTY_ENABLED = "Enabled";
    public static final String DYNAPROP_PROPERTY_AUTHORIZED_USERS = "AuthorizedUsers";
    public static final String DYNAPROP_PROPERTY_JOB_SERVER = "JobServer";
    public static final String DYNAPROP_PROPERTY_JOB_SERVER_ALL = "All";
    public static final String DYNAPROP_PROPERTY_SCHEDULEEXPRESIONSECOND =
            "ScheduleExpressionSecond";
    public static final String DYNAPROP_PROPERTY_SCHEDULEEXPRESIONMINUTE =
            "ScheduleExpressionMinute";
    public static final String DYNAPROP_PROPERTY_SCHEDULEEXPRESIONHOUR =
            "ScheduleExpressionHour";
    public static final String DYNAPROP_PROPERTY_SCHEDULEEXPRESIONDAYOFMONTH =
            "ScheduleExpressionDayOfMonth";
    public static final String DYNAPROP_PROPERTY_SCHEDULEEXPRESIONMONTH =
            "ScheduleExpressionMonth";
    public static final String DYNAPROP_PROPERTY_SCHEDULEEXPRESIONDAYOFWEEK =
            "ScheduleExpressionDayOfWeek";
    public static final String DYNAPROP_PROPERTY_SCHEDULEEXPRESIONYEAR =
            "ScheduleExpressionYear";

    public static final String DYNAPROP_PROPERTY_EMAIL_ADDRESSES = "EmailAddresses";
    public static final String DYNAPROP_PROPERTY_EMAIL_SUBJECT = "EmailSubject";
    public static final String DYNAPROP_PROPERTY_EMAIL_MESSAGE = "EmailMessage";
    public static final String DYNAPROP_PROPERTY_EMAIL_ATTACHMENT_NAME =
            "EmailAttachmentName";
    public static final String DYNAPROP_PROPERTY_EMAIL_ATTACHMENT_TYPE =
            "EmailAttachmentType";

    public static final String DYNAPROP_PROPERTY_JDBC_JNDINAME = "JdbcJndiname";
    public static final String DYNAPROP_PROPERTY_JDBC_USERID = "JdbcUserId";
    public static final String DYNAPROP_PROPERTY_JDBC_PASSWORD = "JdbcPassword";
    public static final String DYNAPROP_PROPERTY_JDBC_URL = "JdbcUrl";

    public static final String DYNAPROP_NAMESPACE_FTP_CONNECTIONS = "FtpConnections";
    public static final String DYNAPROP_PROPERTY_FTP_CONNECTION = "FtpConnection";
    public static final String DYNAPROP_PROPERTY_FTP_PROTOCOL = "FtpProtocol";
    public static final String DYNAPROP_PROPERTY_FTP_USERID = "FtpUserId";
    public static final String DYNAPROP_PROPERTY_FTP_PASSWORD = "FtpPassword";
    public static final String DYNAPROP_PROPERTY_FTP_HOST = "FtpHost";
    public static final String DYNAPROP_PROPERTY_FTP_PORT = "FtpPort";
    public static final String DYNAPROP_PROPERTY_FTP_FILE_PATH = "FtpPath";
    public static final String DYNAPROP_PROPERTY_FTP_FILE_NAME = "FtpFileName";
    public static final String DYNAPROP_PROPERTY_FTP_FILE_NAME_PATTERN = "FtpFileNamePattern";
    public static final String DYNAPROP_PROPERTY_FTP_TYPE = "FtpType";

    public static final String DYNAPROP_PROPERTY_NAS_FILE = "NasFile";
    public static final String DYNAPROP_PROPERTY_NAS_FILE_PATH = "NasPath";
    public static final String DYNAPROP_PROPERTY_NAS_FILE_NAME = "NasFileName";
    public static final String DYNAPROP_PROPERTY_NAS_FILE_NAME_PATTERN = "NasFileNamePattern";

    public static final String DYNAPROP_GROUP_SAVE_ACCESS = "SaveAccess";
    public static final String DYNAPROP_PROPERTY_SAVE_COOKIE = "WSLXCookie";
    public static final String DYNAPROP_PROPERTY_SAVE_ENDPOINT = "Endpoint";
    public static final String DYNAPROP_PROPERTY_MAX_SAVE_BATCH = "SaveBatchSize";

    public static final String DYNAPROP_GROUP_COMMON_SETTINGS = "_CommonSettings";
    public static final String DYNAPROP_GROUP_NGSDN_COUNTRY_TO_REGION =
            "ngsdnCountryToRegion";
    public static final String DYNAPROP_GROUP_PII_TOKEN_TABLE_LIGHTHOUSE_ID =
            "customerTokenTableLighthouseId";
    public static final String DYNAPROP_GROUP_TOKEN_LHID_TABLE =
            "lightHouseIdLookupTable";
    public static final String DYNAPROP_PROPERTY_PII_TOKEN_TABLE_NAME = "tableName";
    public static final String DYNAPROP_PROPERTY_PII_TOKEN_TABLE_PII_FIELD = "piiField";
    public static final String DYNAPROP_PROPERTY_PII_TOKEN_TABLE_TOKEN_FIELD = "tokenField";
    public static final String DYNAPROP_GROUP_DNS = "DNSServerList";
    public static final String DYNAPROP_PROPERTY_SERVER1 = "Server1";
    public static final String DYNAPROP_PROPERTY_SERVER2 = "Server2";
    public static final String DYNAPROP_PROPERTY_SERVER3 = "Server3";
    public static final String DYNAPROP_PROPERTY_USE_PROXY = "useProxy";
    public static final String DYNAPROP_PROPERTY_PROXY1 = "proxy.id1";
    public static final String DYNAPROP_PROPERTY_PROXY2 = "proxy.id2";
    public static final String DYNAPROP_PROPERTY_PROXY_PORT = "proxy.port";
    public static final String DYNAPROP_PROPERTY_HOST = "Host";
    public static final String DYNAPROP_PROPERTY_PORT = "Port";
    public static final String DYNAPROP_PROPERTY_USERID = "id";
    public static final String DYNAPROP_PROPERTY_USERID_PASSWORD = "password";
    public static final String DYNAPROP_PROPERTY_AUTH_PRIVATE_KEY_FILE = "AuthPrivateKeyFile";
    public static final String DYNAPROP_PROPERTY_KEYSTORE = "KeyStore";
    public static final String DYNAPROP_PROPERTY_KEYSTORE_PASSWORD = "KeyStorePassword";
    public static final String DYNAPROP_PROPERTY_KEYSTORE_KEY = "KeyStoreKey";
    public static final String DYNAPROP_PROPERTY_KEYSTORE_KEY_PASS = "KeyPassword";
    public static final String DYNAPROP_PROPERTY_CONNECT_TYPE = "connectType";
    public static final String DYNAPROP_PROPERTY_TIMEOUT = "timeout";
    public static final String DYNAPROP_PROPERTY_GRANT_TYPE = "grant_type";
    public static final String DYNAPROP_PROPERTY_TOKEN_ENDPOINT = "endpoint";
    public static final String DYNAPROP_PROPERTY_RESOURCE_ID = "resource";
    public static final String DYNAPROP_PROPERTY_CLIENT_ID = "client_id";
    public static final String DYNAPROP_PROPERTY_CLIENT_SECRET = "client_secret";
    public static final String DYNAPROP_PROPERTY_APPLICATION_ID = "application_id";

    // File Destination Properties
    public static final String DYNAPROP_GROUP_DESTINATION = "Destination";
    public static final String DYNAPROP_PROPERTY_FILE_PATH = "Path";
    public static final String DYNAPROP_PROPERTY_FILE = "File";
    public static final String DYNAPROP_PROPERTY_FILE_NAME = "FileName";
    public static final String DYNAPROP_PROPERTY_FILE_NAME_PATTERN = "FileNamePattern";
    public static final String DYNAPROP_PROPERTY_USE_PORT_NUM_WITH_KEYFILE =
            "UsePortNumWithKeyFile";
    public static final String DYNAPROP_PROPERTY_LAST_RECORD_PROCESSED =
            "Last-Record-Processed";
    public static final String DYNAPROP_GROUP_VEHICLE_HEALTH_REPORT = "VehicleHealthReport";
    public static final String DYNAPROP_GROUP_APPUSAGE = "AppUsage";
    public static final String DYNAPROP_GROUP_NAS_RECEIVE_APPRUSAGE_FILE =
            "NasReceiveAppFile";
    public static final String DYNAPROP_GROUP_NAS_RECEIVE_VHR_FILE = "NasReceiveVhrFile";

    public static final String DYNAPROP_GROUP_TRANSFORM_EV = "TransformEv";
    public static final String DYNAPROP_GROUP_NAS_EV_FILE = "NasEvFile";
    public static final String DYNAPROP_GROUP_NAS_EV2_FILE = "NasEv2File";
    public static final String DYNAPROP_GROUP_EV_COLUMNS = "EvColumns";
    public static final String DYNAPROP_PROPERTY_BASE_64_COL = "Base64";
    public static final String DYNAPROP_PROPERTY_MESSAGE_ID_COL = "MessageId";
    public static final String DYNAPROP_GROUP_EV2_COLUMNS = "Ev2Columns";
    public static final String DYNAPROP_PROPERTY_BEGIN_ID_COL = "BeginId";
    public static final String DYNAPROP_PROPERTY_END_ID_COL = "EndId";
    public static final String DYNAPROP_PROPERTY_TIMESTAMP_COL = "Timestamp";
    public static final String DYNAPROP_PROPERTY_VALUE_CHARGE_PCT_COL = "ValueChargePct";

    public static final String DYNAPROP_GROUP_TRANSFORM_VIN = "TransformVin";
    public static final String DYNAPROP_PROPERTY_NAS_VIN_FILE = "NasVinFile";
    public static final String DYNAPROP_PROPERTY_FTP_VIN_FILE = "FtpVinFile";
    public static final String DYNAPROP_PROPERTY_MAX_VINS_PER_FILE = "MaxVinsPerFile";

    public static final String DYNAPROP_GROUP_TRANSFORM_VHR = "TransformVhr";
    public static final String DYNAPROP_PROPERTY_NAS_VHR_FILE = "NasVhrFile";
    public static final String DYNAPROP_PROPERTY_MAX_VHRS_PER_FILE = "MaxVhrsPerFile";
    public static final String DYNAPROP_PROPERTY_MONITOR_ESN = "MonitorEsn";
    public static final String DYNAPROP_PROPERTY_ORDER_CHILD_OBJECTS = "OrderChildObjects";

    public static final String DYNAPROP_GROUP_RECEIVE_NAS_VHR = "ReceiveNasVhr";
    public static final String DYNAPROP_PROPERTY_NAS_CSV = "NasCsv";

    public static final String DYNAPROP_GROUP_TRANSFORM_APPUSAGE = "TransformAppUsage";
    public static final String DYNAPROP_GROUP_NAS_AUD_FILE = "NasAudFile";
    public static final String DYNAPROP_PROPERTY_MAX_APP_USAGE_PER_FILE =
            "MaxAppUsagePerFile";

    public static final String DYNAPROP_GROUP_PROCESS_GIVIS_VHR = "ProcessGivisVhr";
    public static final String DYNAPROP_PROPERTY_NAS_RAW = "NasRaw";
    public static final String DYNAPROP_PROPERTY_NAS_TRANSFORM = "NasTransform";
    public static final String DYNAPROP_PROPERTY_RECEIVE = "Receive";
    public static final String DYNAPROP_PROPERTY_TRANSFER = "Transfer";
    public static final String DYNAPROP_PROPERTY_TRANSFORM = "Transform";

    public static final String DYNAPROP_GROUP_PROCESS_CLEAN_UP = "CleanUp";
    public static final String DYNAPROP_PROPERTY_FILE_TYPES = "FileTypes";
    public static final String DYNAPROP_GROUP_NAS_COMPLETED_FOLDER = "NasCompletedFolder";
    public static final String DYNAPROP_GROUP_NAS_FAILED_FOLDER = "NasFailedFolder";
    public static final String DYNAPROP_GROUP_NAS_FAILED_LOG_FOLDER = "NasFailedLogFolder";
    public static final String DYNAPROP_GROUP_NAS_READY_FOLDER = "NasReadyFolder";
    public static final String DYNAPROP_GROUP_NAS_TEMP_FOLDER = "NasTempFolder";
    public static final String DYNAPROP_GROUP_NAS_RAW_FOLDER = "NasRawFolder";
    public static final String DYNAPROP_PROPERTY_CLEANUP_COMPLETED_FOLDER_ENABELED =
            "CleanUpCompletedFolderEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_FAILED_FOLDER_ENABELED =
            "CleanUpFailedFolderEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_TEMP_FOLDER_ENABELED =
            "CleanUpTempFolderEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_READY_FOLDER_ENABELED =
            "CleanUpReadyFolderEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_RECEIVE_SUCCESSFUL_STATUS_ENABELED =
            "CleanUpReceiveSuccessfulStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_TRANSFORM_INPROCESS_STATUS_ENABELED =
            "CleanUpTransformInprocessStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_TRANSFORM_SUCCESSFUL_STATUS_ENABELED =
            "CleanUpTransformSuccessfulStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_TRANSFORM_FAILED_STATUS_ENABELED =
            "CleanUpTransformFailedStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_TRANSFORM_REPROCESS_STATUS_ENABELED =
            "CleanUpTransformReprocessStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_TRANSFORM_CANCELED_STATUS_ENABELED =
            "CleanUpTransformCanceledStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_CONSUME_SUCCESSFUL_STATUS_ENABELED =
            "CleanUpConsumeSuccessfulStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_CONSUME_FAILED_STATUS_ENABELED =
            "CleanUpConsumeFailedStatusEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_NO_DATA_RECEIVED_ENABELED =
            "CleanUpNoDataReceivedEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_METRICS_ENABELED =
            "CleanUpMetricsEnabled";
    public static final String DYNAPROP_PROPERTY_CLEANUP_MESSAGE_LOG =
            "CleanUpMessageLogEnabled";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_READY_FOLDER =
            "MinHoursInReadyFolder";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_TEMP_FOLDER =
            "MinHoursInTempFolder";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_RECEIVE_SUCCESSFUL_STATUS =
            "MinHoursInReceiveSuccessfulStatus";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_TRANSFORM_INPROCESS_STATUS =
            "MinHoursInTransformInprocessStatus";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_TRANSFORM_SUCCESSFUL_STATUS =
            "MinHoursInTransformSuccessfulStatus";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_TRANSFORM_FAILED_STATUS =
            "MinHoursInTransformFailedStatus";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_TRANSFORM_REPROCESS_STATUS =
            "MinHoursInTransformReprocessStatus";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_TRANSFORM_CANCELED_STATUS =
            "MinHoursInTransformCanceledStatus";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_CONSUME_SUCCESSFUL_STATUS =
            "MinHoursInConsumeSuccessfulStatus";
    public static final String DYNAPROP_PROPERTY_MIN_HOURS_IN_CONSUME_FAILED_STATUS =
            "MinHoursInConsumeFailedStatus";
    public static final String DYNAPROP_PROPERTY_MAX_HOURS_NO_DATA_RECEIVED =
            "MaxHoursNoDataReceived";
    public static final String DYNAPROP_PROPERTY_MIN_DAYS_METRICS = "MinDaysMetrics";
    public static final String DYNAPROP_PROPERTY_MIN_DAYS_MESSAGE_LOG = "MinDaysMessageLog";

    public static final String FILE_SOURCE_TELOGIS = "Telogis";
    public static final String FILE_SOURCE_ACCENTURE = "Accenture";
    public static final String DYNAPROP_PROPERTY_DATA_SOURCE_VENDOR = "Vendor";
    public static final String DYNAPROP_PROPERTY_VENDOR_NAME = "VendorName";
    public static final String DYNAPROP_PROPERTY_FILE_TYPE = "FileType";
    public static final String FILE_TYPE_CC = "CC";
    public static final String DYNAPROP_GROUP_TRANSFORM_CC = "TransformCC";
    public static final String DYNAPROP_GROUP_RECEIVE_CC = "ReceiveCC";
    public static final String DYNAPROP_GROUP_RECEIVE_EV = "ReceiveEV";
    public static final String DYNAPROP_GROUP_RECEIVE_SDN = "ReceiveSDN";
    public static final String DYNAPROP_GROUP_PUBLISH_SDN = "ReceiveSDN";
    public static final String DYNAPROP_GROUP_DATA_SOURCE = "DataSource";
    public static final String DYNAPROP_PROPERTY_MIN_ELIGIBLE_DAYS_TO_DELETE =
            "EligibleDaysToDelete";
    public static final String DYNAPROP_PROPERTY_VALIDATE_CHECKSUM = "ValidateChecksum";

    public static final String DYNAPROP_GROUP_TRANSFORM_TTI = "TransformTTI";
    public static final String DYNAPROP_GROUP_NAS_TTI_FILE = "NasTtiFile";
    public static final String FILE_TYPE_TTI = "TTI";
    public static final String DYNAPROP_PROPERTY_NAS_TRANSFORM_FILE = "NasCCFile";

    public static final String DYNAPROP_PROPERTY_OBSCURE_GPS = "obscureGps";

    public static final String DYNAPROP_PROPERTY_ZOOKEEPER_QUORUM = "zookeeperQuorum";
    public static final String DYNAPROP_PROPERTY_ZNODE_PARENT = "zookeeperZnodeParent";
    public static final String DYNAPROP_PROPERTY_REGIONSERVER_PRINCIPAL =
            "hbaseRegionserverKerberosPrincipal";
    public static final String DYNAPROP_PROPERTY_REGIONSERVER_KEYTAB_FILE =
            "hbaseRegionServerKeytabFile";
    public static final String DYNAPROP_PROPERTY_AUTHENTICATION =
            "hbaseSecurityAuthentication";

    public static final String DYNAPROP_GROUP_PROPERTY_REQUEST_PROPERTY = "requestProperty";
    public static final String DYNAPROP_PROPERTY_NUM_OF_DAYS = "numOfDays";
    public static final String DYNAPROP_PROPERTY_NUM_OF_MINS = "numOfMins";
    public static final String DYNAPROP_PROPERTY_ENDPOINT = "endpoint";
    public static final String DYNAPROP_PROPERTY_DATE_FORMAT = "dateFormat";
    public static final String DYNAPROP_PROPERTY_QUERY_STR = "queryStr";
    public static final String DYNAPROP_PROPERTY_REQUEST_METHOD = "method";
    public static final String DYNAPROP_PROPERTY_UNZIP = "unzip";
    public static final String DYNAPROP_PROPERTY_INCLUDE_EMPTY_FILES = "includeEmptyFiles";

    public static final String DYNAPROP_PROPERTY_BING_KEY = "bingKey";
    public static final String DYNAPROP_PROPERTY_BING_URL = "bingUrl";
    public static final String PROPERTY_GROUP_COMMON_KEYFILE = "keyfile";
    public static final String DYNAPROP_PROPERTY_TRUSTSTORENAME = "publicTrustStoreName";
    public static final String DYNAPROP_PROPERTY_TRUSTSTOREPASS = "publicTrustStorePassword";

    public static final String DYNAPROP_PROPERTY_MAX_ENTRIES_PER_ARCHIVE = "maxEntriesPerArchive";

    //Archive properties
    public static final String DYNAPROP_GROUP_ARCHIVE = "archive";
    public static final String DYNAPROP_CONST_SEPARATOR = ".";
    public static final String DYNAPROP_ARCHIVE_ROOT_DIR = "archiveRootDirectory";
    public static final String DYNAPROP_EXCEPTION_FILE_PATH = "exceptionFilePath";
    public static final String DYNAPROP_INPUT_PATH_FOR_MAPPER = "inputPathForMappers";
    public static final String DYNAPROP_OUTPUT_PATH_FOR_MAPPER = "outputPathForMappers";
    public static final String DYNAPROP_DAYS_BEFORE_DELETE = "daysBeforeDeleting";
    public static final String DYNAPROP_DAYS_BEFORE_ARCHIVE = "daysBeforeArchiving";
    public static final String DYNAPROP_BEGINNING_FILE_PATH = "beginningFilePath";

    //Decoding properties
    public static final String DYNAPROP_GROUP_DECODING = "decoding";
    public static final String DYNAPROP_PROPERTY_CAN_DECODING_URL = "canDecodingUrl";
    public static final String DYNAPROP_PROPERTY_DID_DECODING_URL = "didDecodingUrl";

    // Common Properties
    public static final String PROXY = "proxy";
    public static final String ID = "id";
    public static final String PORT = "port";
    public static final String KEY_DIR = "KEYS"; // Directory to store keys

    public static final String CC_INITIAL_MARKER_BYTES = "5061103E";
    public static final String CC_GROUP01_MARKER_BYTES = "05169975";
    public static final String CC_GROUP02_MARKER_BYTES = "05169976";
    public static final String CC_GROUP03_MARKER_BYTES = "B340185C";
    // Failure Messages
    public static final String CC_STATUS_FATAL_CRC_NOT_MATCH = "Message CRC not matched";
    public static final String CC_STATUS_INVALID_MESSAGE_TYPE = "Invalid Message Type";
    public static final String CC_VIN_CONTAINS_UNICODE_CHARS =
            "Vin contains null characters in unicode format";
    public static final String CC_PARTNO_CONTAINS_UNICODE_CHARS =
            "PartNumber value contains null characters in unicode format";
    public static final String CC_NO_MARKER_BYTES =
            "No Marker Bytes (50 61 10 3E) Found in Crew Chief data file";
    public static final String CC_NO_MESSAGE_FOUND =
            "No Message found in Crew Chief data file";
    public static final String FTCP_ALERT_GENERIC_AND_NONGENERIC =
            "Message contained both a GenericAlert and a NonGenericAlert";
    public static final String FTCP_CMD_RESP_TIME_SENS_AND_NON_TIME_SENS =
            "Message contained both a TimeSensitiveCommandResponse and a NonTimeSensitiveCommandResponse";
    public static final String FTCP_MULTIPLE_GENERIC_ALERTS =
            "Message contained multiple Generic Alerts";
    public static final String FTCP_MULTIPLE_NONGENERIC_ALERTS =
            "Message contained multiple Non-Generic Alerts";
    public static final String FTCP_MULTIPLE_TIME_SENSITIVE_COMMAND_RESPONSES =
            "Message contained multiple Time Sensitive Command Responses";
    public static final String FTCP_MULTIPLE_NON_TIME_SENSITIVE_COMMAND_RESPONSES =
            "Message contained multiple Non-Time Sensitive Command Responses";

    public static final String MESSAGE_DECODE_FAILED =
            "The file {0} contains {1} records that failed to decode (out of {2} total records).";

    public static final String USER_NAME = "user.name";
    public static final String UNKNOWN_USER = "UNKNOWN USER";

    public static final char INVALID_DATA_EXCEPTION = 'E';
    public static final char JSON_FAILED_EXCEPTION = 'F';
    public static final char JSON_INVALID_EXCEPTION = 'I';

    // Can be removed later
    public static final char JSON_F_EXCEPTION = 'F';
    public static final char JSON_I_EXCEPTION = 'I';

    public static final String FILE_TYPE = "file.type";
    public static final String HBASE_NONDUP_FILE_INSERT = "deddata";

    public static final String DUPLICATE_DATA = "duplicate data";
    public static final String INVALID_DATA_INPUT = "invalid input";

    public static final String DUPLICATE_LOOKUP_TABLE = "duplicate_lookup_table";
    public static final String VIN_SHA256_TABLE = "vin_sha256_table";
    public static final String VIN_HASH_TABLE = "vin_hash_table";

    // Storm Message Deserialization properties
    /*=======================================================================
     * Decoding Properties
     */
    public static final String CONST_MESSAGEPART_DELIM = "\\|###\\|";
    public static final String CONST_MESSAGEPART_ATTRIBUTE_DELIM = "\\|@\\|";
    public static final String CONST_MESSAGE_PAYLOAD_IDENTIFIER = "payload";

    /*=======================================================================
     * Serializer Properties
     */
    public static final String CONST_SERIALIZER_MESSAGEPART_DELIM = "|###|";
    public static final String CONST_SERIALIZER_ATTRIBUTE_DELIM = "|@|";

    /*=======================================================================
     * Message Chunking Keys
     */
    public static final String CONST_MESSAGE_SOURCE = "Source";
    public static final String CONST_MESSAGE_LIGHTHOUSEID = "LighthouseIDs";
    public static final String CONST_MESSAGE_ACTIVITYID = "ActivityID";
    public static final String CONST_MESSAGE_MESSAGETYPE = "MessageType";
    public static final String CONST_MESSAGE_ARRIVALTIME = "ArrivalTime";
    public static final String CONST_MESSAGE_REGIONCODE = "RegionCode";
    public static final String CONST_MESSAGE_PAYLOAD = "payload";
    public static final String CONST_MESSAGE_STATUS = "GeneratedStatus";
    public static final String CONST_MESSAGE_MESSAGENAME = "MessageName";
    public static final String CONST_MESSAGE_VIN_NB = "Vin";

    // Common
    public static final String CONNECT_USING_HTTPS_CLIENT = "HttpsClient";

    public static final String CONNECT_USING_HTTP_CLIENT = "HttpClient";

    public static final String INVALID_TABLE_PII_REMOVED = "PII Removed.";

    public static final String UOM_KM = "km";
    public static final String UOM_MI = "mi";
    public static final Double CONST_KM_TO_MILES_FACTOR = 0.621371;
    public static final Double CONST_MI_TO_KM_FACTOR = 1.60934;

    // TFL - TLS Version
    public static final String TLS_VERSION = "tlsVersion";

    // De-dup job parameters
    public static final String DEDUP_JOB_PARAMETER_WORKFLOW_ID = "workflowId";
    public static final String DEDUP_JOB_PARAMETER_HBASE_LOOKUP_TABLE = "hbaseLookupTable";
    public static final String DEDUP_JOB_NAMED_OUTPUT_DUP = "Dup";
    public static final String DEDUP_JOB_NAMED_OUTPUT_NONDUP = "NonDup";

    // DID Decoding Constants
    public static final String DID_DECODING_RULES_GDX_JSON_FILE_NAME = "gdxDidDecodingRules.json";
    public static final String DID_DECODING_RULES_MDX_JSON_FILE_NAME = "mdxDidDecodingRules.json";

    //CVDP SQL Server Constants
    public static final String DYNAPROP_GROUP_CVDP_SQL_SERVER   = "cvdpSqlServer";

}
