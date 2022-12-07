package com.ford.datafactory.batch.decoder;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.ford.datafactory.batch.bo.VinBO;
import com.ford.datafactory.batch.util.*;
import org.codehaus.jackson.map.ObjectMapper;

public class VinDataDecoder implements IMultipleRecordDecoder{

	private static final String CLASS_NAME = VinDataDecoder.class.getName();
	private static final Logger log = Logger.getLogger(CLASS_NAME);
	
	ObjectMapper objectMapper = new ObjectMapper();
	VinBO VinBO;
	String processStatusDetails = "";
	//String headerTimestamp= null;
	boolean resultStatus = false; 
	
	public static final String headerDate = "HEADER_DATETIME";
	public static final String vinId = "VIN_ID";
	public static final String vinAuditCd = "VIN_AUDIT_CD";
	public static final String vinAuditCu = "VIN_AUDIT_CU";
	public static final String vinAuditMd = "VIN_AUDIT_MD";
	public static final String vinAuditMu = "VIN_AUDIT_MU";
	public static final String vinAuditRd = "VIN_AUDIT_RD";
	public static final String vinAuditRu = "VIN_AUDIT_RU";
	public static final String vinChangeDate = "VIN_CHANGE_DATE";
	public static final String vinCode = "VIN_CODE";
	public static final String vinMake = "VIN_MAKE";
	public static final String vinModel = "VIN_MODEL";
	public static final String vinRefCusId = "VIN_REF_CUS_ID";
	public static final String vinSource = "VIN_SOURCE";
	public static final String vinStatus = "VIN_STATUS";
	public static final String vinYear = "VIN_YEAR";
	public static final String ctrCodeHome = "CTR_CODE_Home";
	public static final String prgCode = "PRG_CODE";
	public static final String vinProductTypeVehicleCode = "VIN_PRODUCT_TYPE_VEHICLE_CODE";
	public static final String vinCommonName = "VIN_COMMON_NAME";
	public static final String vinVehicleLineFeatureCode = "VIN_VEHICLE_LINE_FEATURE_CODE";
	public static final String vinSaleDate = "VIN_SALE_DATE";
	public static final String vinFuelType = "VIN_FUEL_TYPE";
	public static final String vinProductType = "VIN_PRODUCT_TYPE";
	public static final String vinTransmissionDescription = "VIN_TRANSMISSION_DESCRIPTION";
	public static final String vinBodyStyleDescription = "VIN_BODY_STYLE_DESCRIPTION";
	public static final String vinBodyStyle = "VIN_BODY_STYLE";
	public static final String vinDriveDescription = "VIN_DRIVE_DESCRIPTION";
	public static final String vinEngineFeatureCode = "VIN_ENGINE_FEATURE_CODE";
	public static final String vinEngineDescription = "VIN_ENGINE_DESCRIPTION";
	public static final String vinBrandCode = "VIN_BRAND_CODE";
	public static final String vinVersion = "VIN_VERSION";
	public static final String vinBuildDate = "VIN_BUILD_DATE";
	public static final String vinVersionDescription = "VIN_VERSION_DESCRIPTION";
	public static final String vinTransmission = "VIN_TRANSMISSION";
	public static final String vinEngineFuelCapabilityCod = "VIN_ENGINE_FUEL_CAPABILITY_COD";
	public static final String vinEngineFuelCapabilityDes = "VIN_ENGINE_FUEL_CAPABILITY_DES";
	public static final String vinTerrorityDescription = "VIN_TERRORITY_DESCRIPTION";
	public static final String vinTerrority = "VIN_TERRORITY";
	public static final String vinLocalVehicleLineDescrip = "VIN_LOCAL_VEHICLE_LINE_DESCRIP";
	
	/*public VinDataDecoder(String headertimestamp){
		
		this.headerTimestamp = headertimestamp;
	}*/


	@Override
	public String decode(String headerTimestamp,String line) throws Exception {
		final String METHOD_NAME = "decode";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		processStatusDetails = "";
		VinBO = new VinBO();
		
		try {
			
			ArrayList<String> vinData = CsvUtil.parseCsvRecord(line.toString());

			if (!validateSkipRecords(vinData)) {	
				if (vinData.size() == 39) {
					// Fail processing 
					validateAllMandatoryFields(vinData);
					validateMandatoryFields(vinData);
					
			        setVinMappings(vinData, headerTimestamp);
                    
                    setRawPayload(vinData);
                    
                    validateAndSetShaKey(vinData, headerTimestamp);
                    
					// Finally -- set process states
					resultStatus = FordPassAprHelper.setProcessState();
					
					if (resultStatus){	
						
						VinBO.setProcess_status_details(FordPassAprHelper.setProcessDetails());	
						
						VinBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_FAILED);
					} else {		
						
						VinBO.setProcess_status_details(null);		
						
						VinBO.setProcess_status(CvdpConstants.FILE_STATUS_TRANSFORMATION_SUCCESSFUL);
					}
					
					VinBO.setProcess_status_date_time_utc(FordPassAprHelper
							.GetUTCdatetimeAsString());
					
					// reset 
					FordPassAprHelper.resetProcessState();
					
					return this.objectMapper.writeValueAsString(VinBO);
					
				}else {
					throw new Exception(
							FordPassAprConstants.VIN_DATATYPE + FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);
				}
				
		
			}else{
				return null;
			}
		} catch (Exception e) {
			if (e.getMessage() == null)
				throw new Exception(
						FordPassAprConstants.VIN_DATATYPE + FordPassAprConstants.PROCESSING_EXCEPTION);		
				else
					throw new Exception(e.getMessage());
		}
		
	}
	
       private void setVinMappings(ArrayList<String> vinData, String headerTimestamp) {
		
		final String METHOD_NAME = "setVinMappings";
		
		log.entering(CLASS_NAME, METHOD_NAME);
		
		VinBO.setPartition_year(FordPassAprHelper.intParser(
				FordPassAprHelper.transformISOPartitionDate(
						headerTimestamp, headerDate), headerDate));
		
		VinBO.setHeader_timestamp_utc(FordPassAprHelper.parseHeaderDateTimestamp(headerTimestamp , headerDate));
		
		VinBO.setVin_id(FordPassAprHelper.longParser(vinData.get(0), vinId));
		
		VinBO.setVin_audit_cd(FordPassAprHelper.parseDateTimestamp(vinData.get(1),vinAuditCd));
		
		VinBO.setVin_audit_cu(FordPassAprHelper.longParser(vinData.get(2),vinAuditCu));
		
		VinBO.setVin_audit_md(FordPassAprHelper.parseDateTimestamp(vinData.get(3),vinAuditMd));
		
		VinBO.setVin_audit_mu(FordPassAprHelper.longParser(vinData.get(4),vinAuditMu));
		
		VinBO.setVin_audit_rd(FordPassAprHelper.parseDateTimestamp(vinData.get(5),vinAuditRd));
						
		VinBO.setVin_audit_ru(FordPassAprHelper.longParser(vinData.get(6),vinAuditRu));
		
		VinBO.setVin_change_date(FordPassAprHelper.parseDateTimestamp(vinData.get(7),vinChangeDate));
					
		VinBO.setVin_code(FordPassAprHelper.stringParser(vinData.get(8),vinCode));
		
		VinBO.setVin_make(FordPassAprHelper.stringParser(vinData.get(9),vinMake));
		
		VinBO.setVin_model(FordPassAprHelper.stringParser(vinData.get(10),vinModel));
		
		VinBO.setVin_ref_cus_id(FordPassAprHelper.longParser(vinData.get(11),vinRefCusId));
		
		VinBO.setVin_source(FordPassAprHelper.stringParser(vinData.get(12),vinSource));
		
		VinBO.setVin_status(FordPassAprHelper.stringParser(vinData.get(13),vinStatus));
		
		VinBO.setVin_year(FordPassAprHelper.stringParser(vinData.get(14),vinYear));
		
		VinBO.setCtr_code_home(FordPassAprHelper.stringParser(vinData.get(15),ctrCodeHome));
		
		/*
		 * 
		 * FPA �  enhancement CR# F189505 - US1125438 - Re arranging attributes of 
		 * accx_custom1,prgCode ,vinFuelType,vinSaleDate,vinVehicleLineFeatureCode,vinCommonName attributes
		 *
		 * */
		
		VinBO.setPrg_code(FordPassAprHelper.stringParser(vinData.get(16), prgCode));
		
		VinBO.setVin_product_type_vehicle_code(FordPassAprHelper.stringParser(vinData.get(18), vinProductTypeVehicleCode));
		
		VinBO.setVin_common_name(FordPassAprHelper.stringParser(vinData.get(19),vinCommonName));
		
		VinBO.setVin_vehicle_line_feature_code(FordPassAprHelper.stringParser(vinData.get(20),vinVehicleLineFeatureCode));


		//VinBO.setVin_sale_date(FordPassAprHelper.parseDateFormatYMD(vinData.get(21),vinSaleDate)); // F211923 Changes
		VinBO.setVin_sale_date(FordPassAprHelper.parseDateTimestamp(vinData.get(21),vinSaleDate));
		
		VinBO.setVin_fuel_type(FordPassAprHelper.stringParser(vinData.get(17),vinFuelType));
		
		VinBO.setVin_product_type(FordPassAprHelper.stringParser(vinData.get(22),vinProductType));
		
		VinBO.setVin_transmission_description(FordPassAprHelper.stringParser(vinData.get(23),vinTransmissionDescription));
		
		VinBO.setVin_body_style_description(FordPassAprHelper.stringParser(vinData.get(24),vinBodyStyleDescription));
		
		VinBO.setVin_body_style(FordPassAprHelper.stringParser(vinData.get(25),vinBodyStyle));
		
		VinBO.setVin_drive_description(FordPassAprHelper.stringParser(vinData.get(26),vinDriveDescription));
		
		VinBO.setVin_engine_feature_code(FordPassAprHelper.stringParser(vinData.get(27),vinEngineFeatureCode));
		
		VinBO.setVin_engine_description(FordPassAprHelper.stringParser(vinData.get(28),vinEngineDescription));
		
		VinBO.setVin_brand_code(FordPassAprHelper.stringParser(vinData.get(29),vinBrandCode));
		
		VinBO.setVin_version(FordPassAprHelper.stringParser(vinData.get(30),vinVersion));
		
		VinBO.setVin_build_date(FordPassAprHelper.stringParser(vinData.get(31),vinBuildDate));
		
		VinBO.setVin_version_description(FordPassAprHelper.stringParser(vinData.get(32),vinVersionDescription));
		
		VinBO.setVin_transmission(FordPassAprHelper.stringParser(vinData.get(33),vinTransmission));
		
		VinBO.setVin_engine_fuel_capability_cod(FordPassAprHelper.stringParser(vinData.get(34),vinEngineFuelCapabilityCod));
		
		VinBO.setVin_engine_fuel_capability_des(FordPassAprHelper.stringParser(vinData.get(35),vinEngineFuelCapabilityDes));
		
		VinBO.setVin_terrority_description(FordPassAprHelper.stringParser(vinData.get(36),vinTerrorityDescription));
		
		VinBO.setVin_terrority(FordPassAprHelper.stringParser(vinData.get(37),vinTerrority));
		
		VinBO.setVin_local_vehicle_line_descrip(FordPassAprHelper.stringParser(vinData.get(38),vinLocalVehicleLineDescrip));
		
	}

	private void setRawPayload(ArrayList<String> vinData) {
		
		StringBuffer sbf = new StringBuffer();		
		sbf.append(vinData.get(0));
        for(int i=1; i < vinData.size(); i++){
                sbf.append(",").append(vinData.get(i));
        }		
        VinBO.setRaw_payload(sbf.toString());	
	}
	
	private boolean validateSkipRecords(ArrayList<String> vinData)
			throws Exception {
		boolean skipRecord = false;
		if (vinData.get(0) != null) {
			if (vinData.get(0).equalsIgnoreCase(
					FordPassAprConstants.APR_HEADER)
					|| vinData.get(0).equalsIgnoreCase(
							FordPassAprConstants.APR_TAIL)) {
				skipRecord = true;
			} else {
				skipRecord = false;
			}
		} else {
			if (vinData.size() == 39) {
				skipRecord = false;

			} else {
				throw new Exception(FordPassAprConstants.VIN_DATATYPE
						+ FordPassAprConstants.RAW_PAYLOAD_ERR_DETAILS);

			}
		}
		return skipRecord;
	}

	
	private void validateAllMandatoryFields(ArrayList<String> vinData ) throws Exception{
		final String METHOD_NAME = "validateAllMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		
		if (vinData.get(0) == null && vinData.get(1) == null
				&& vinData.get(8) == null && vinData.get(9) == null && vinData.get(10) == null 
				&& vinData.get(11) == null &&vinData.get(12) == null
				&& vinData.get(13) == null && vinData.get(14) == null && vinData.get(15) == null)

			throw new Exception(
					FordPassAprConstants.VIN_DATATYPE
							+ FordPassAprConstants.REQUIRED_FIELDS);		
	}
	
	//Sets record to transfail if any of the required field is empty	
	private void validateMandatoryFields(ArrayList<String> vinData){
		final String METHOD_NAME = "validateMandatoryFields";
		log.entering(CLASS_NAME, METHOD_NAME);
		FordPassAprHelper.validateMandatoryField(vinData.get(0), vinId);
		FordPassAprHelper.validateMandatoryField(vinData.get(1), vinAuditCd);
		FordPassAprHelper.validateMandatoryField(vinData.get(8), vinCode);
		FordPassAprHelper.validateMandatoryField(vinData.get(9), vinMake);
		FordPassAprHelper.validateMandatoryField(vinData.get(10), vinModel);
		FordPassAprHelper.validateMandatoryField(vinData.get(11), vinRefCusId);
		FordPassAprHelper.validateMandatoryField(vinData.get(12), vinSource);
		FordPassAprHelper.validateMandatoryField(vinData.get(13), vinStatus);
		FordPassAprHelper.validateMandatoryField(vinData.get(14), vinYear);
		FordPassAprHelper.validateMandatoryField(vinData.get(15), ctrCodeHome);					
	}
	
	private void validateAndSetShaKey(ArrayList<String> vinData, String headerTimestamp) throws Exception {
		final String METHOD_NAME = "validateShaKey";
		log.entering(CLASS_NAME, METHOD_NAME);
				try {
					
					VinBO.setSha_key(HashUtil.calculateSHA256(vinData.get(0)+vinData.get(11)+vinData.get(3) +","+ headerTimestamp));

				} catch (Exception e) {
					
					e.printStackTrace();
					throw new Exception(
							FordPassAprConstants.VIN_DATATYPE
									+ FordPassAprConstants.PAYLOAD_SHAKEY_ERR_DETAILS);
				}
			}
		
	}

