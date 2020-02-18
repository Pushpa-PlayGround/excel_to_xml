package com.ms.ndcinstructions.service;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;
import com.ms.ndcinstructions.transfer.AllocationProdDestResponse;
import com.ms.ndcinstructions.transfer.AllocationProdResponse;
import com.ms.ndcinstructions.transfer.AllocationResponse;
import com.ms.ndcinstructions.transfer.AllocationResponse.Destinations;
import com.ms.ndcinstructions.transfer.AllocationResponse.Destinations.Destination;
import com.ms.ndcinstructions.transfer.AllocationResponse.Destinations.Destination.AllocationQuantity;
import com.ms.ndcinstructions.transfer.BlobStorageExcelData;
import com.ms.ndcinstructions.transfer.sapHeader.IntegrationExtensionType;
import com.ms.ndcinstructions.transfer.NDCMnsDocument;
import com.ms.ndcinstructions.transfer.NDCPayLoad;
import com.ms.ndcinstructions.transfer.SAPHeader;
import com.ms.ndcinstructions.transfer.sapHeader.SAPMnSDocument;
import com.ms.ndcinstructions.transfer.SourceStock;
import com.ms.ndcinstructions.transfer.SourceStock.SourceQuantity;
import com.ms.ndcinstructions.transfer.UnitDefinitions;
import com.ms.ndcinstructions.transfer.UnitDefinitions.UnitDefinition;

@Service
public class NdcInstructionGenerationService {
	
	private   JAXBContext jaxbContextNdc;
	
	@PostConstruct
	void initializeMap() throws DatatypeConfigurationException, JAXBException {
		jaxbContextNdc=JAXBContext.newInstance(SAPMnSDocument.class);
	} 
	
	public String generateNdcInstruction(Map<String,List<BlobStorageExcelData>> excelData) {
		
		    String sourceLocationId = "DH";
		    String formattedDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());    
		    
            AllocationProdDestResponse allocReqProdDest = null;
            AllocationProdResponse response = new AllocationProdResponse(); 
           
       		List<AllocationProdResponse> allocationProdResponseList = null;
       		List<AllocationProdResponse> allocationProdResponseListRes = null;
       		
       		List<AllocationProdDestResponse> allocationProdDestResponseList = null;
    		List<AllocationProdDestResponse> allocationProdDestResponseListRes = null;
           
       		AllocationProdResponse allocResponse = null;
    		AllocationProdResponse allocResponseRes = null;
    		AllocationProdDestResponse allocationProdDestResposeDto = null;
    		AllocationProdDestResponse allocationProdDestResposeDtoRes = null;
    		boolean recordExist = false;
           List<AllocationProdResponse> allocReqProdList = new ArrayList<>();
//           List<AllocationProdResponse> allocReqList = new ArrayList<>();
           List<AllocationProdDestResponse> allocReqProdDestList = new ArrayList<>();
//           List<AllocationProdDestResponse> allocReqDestList = new ArrayList<>();
           
			
			allocationProdResponseList = new ArrayList<AllocationProdResponse>();
			allocationProdResponseListRes = new ArrayList<AllocationProdResponse>();
           
           for (Map.Entry<String, List<BlobStorageExcelData>> entry : excelData.entrySet()) {
        	   
        	   String productRequestId = "";
        	   
        	   List<BlobStorageExcelData> destResponseValues = entry.getValue();
        	   
        	   allocResponse = new AllocationProdResponse();
        	   allocResponse.setUpcEan(entry.getKey());
        	   allocResponse.setSourceLocationId(sourceLocationId);
        	   
        	   productRequestId = sourceLocationId + entry.getKey() + formattedDate;
        	   allocResponse.setProductRequestId(productRequestId);
        	   
        	   allocResponse.setMinTradeableQty(destResponseValues.get(0).getUpt());
        	   allocResponse.setQuantity(destResponseValues.get(0).getQuantity());
        	   
        	   allocResponseRes = new AllocationProdResponse();
        	   allocResponseRes.setUpcEan(entry.getKey());
        	   allocResponseRes.setSourceLocationId(sourceLocationId);
        	   allocResponseRes.setProductRequestId(productRequestId);
        	   allocResponseRes.setMinTradeableQty(destResponseValues.get(0).getUpt());
        	   allocResponseRes.setQuantity(destResponseValues.get(0).getQuantity());
        	   
        	   allocationProdDestResponseList = new ArrayList<AllocationProdDestResponse>();
			   allocationProdDestResponseListRes = new ArrayList<AllocationProdDestResponse>();
               
               for(BlobStorageExcelData destResponse : destResponseValues) {
            	   
            	   allocationProdDestResposeDto = new  AllocationProdDestResponse();
            	   
            	   allocationProdDestResposeDto.setAllocatedQuantity(destResponse.getAllocatedQuantity());
					allocationProdDestResposeDto.setAllocationType(destResponse.getAllocationType());
					allocationProdDestResposeDto.setIntoStoreDate(destResponse.getIntoStoreDate());
					allocationProdDestResposeDto.setStoreId(destResponse.getStoreId());	
					allocationProdDestResposeDto.setCross_dock(destResponse.getCrossDock());
					allocationProdDestResponseList.add(allocationProdDestResposeDto);
					
					
					if(!allocationProdDestResponseListRes.isEmpty() && allocationProdDestResponseListRes!=null) {
						for(AllocationProdDestResponse allocationProdDestResponseNew : allocationProdDestResponseListRes) {
							recordExist = false;
							if(destResponse.getIntoStoreDate().equals(allocationProdDestResponseNew.getIntoStoreDate()) &&
									destResponse.getStoreId().equals(allocationProdDestResponseNew.getStoreId()) ) {
								allocationProdDestResponseNew.setAllocatedQuantity(Integer.valueOf(destResponse.getAllocatedQuantity()) + Integer.valueOf(allocationProdDestResponseNew.getAllocatedQuantity()));
								recordExist = true;
								break;
							 }
						   }
							if(!recordExist) {
								allocationProdDestResposeDtoRes = new AllocationProdDestResponse();
								allocationProdDestResposeDtoRes.setAllocatedQuantity(destResponse.getAllocatedQuantity());
								allocationProdDestResposeDtoRes.setAllocationType(destResponse.getAllocationType());
								allocationProdDestResposeDtoRes.setIntoStoreDate(destResponse.getIntoStoreDate());
								allocationProdDestResposeDtoRes.setStoreId(destResponse.getStoreId());	
								allocationProdDestResposeDtoRes.setCross_dock(destResponse.getCrossDock());
								allocationProdDestResponseListRes.add(allocationProdDestResposeDtoRes);
							   }
						} else {
							allocationProdDestResposeDtoRes = new AllocationProdDestResponse();
							allocationProdDestResposeDtoRes.setAllocatedQuantity(destResponse.getAllocatedQuantity());
							allocationProdDestResposeDtoRes.setAllocationType(destResponse.getAllocationType());
							allocationProdDestResposeDtoRes.setIntoStoreDate(destResponse.getIntoStoreDate());
							allocationProdDestResposeDtoRes.setStoreId(destResponse.getStoreId());	
							allocationProdDestResposeDtoRes.setCross_dock(destResponse.getCrossDock());
							allocationProdDestResponseListRes.add(allocationProdDestResposeDtoRes);
						}
               		}
               
               allocResponse.setAllocReqProdDest(allocationProdDestResponseList);
			   allocationProdResponseList.add(allocResponse);

				allocResponseRes.setAllocReqProdDest(allocationProdDestResponseListRes);
				allocationProdResponseListRes.add(allocResponseRes);
               
           }
           response.setAllocReqProdList(allocationProdResponseList);
           response.setAllocReqProdList(null);
   		   response.setAllocReqProdList(allocationProdResponseListRes);
   		   
   		   System.out.println("  value of response ::  " +  response);
           
          /* for (Map.Entry<String, List<BlobStorageExcelData>> entry : excelData.entrySet()) {
        	   
        	   allocaReqProdResponse=new AllocationProdResponse();
        	   allocReqProdDest = new AllocationProdDestResponse();
        	   
        	   List<BlobStorageExcelData> destResponseValues = entry.getValue();
        	   
        	   allocaReqProdResponse.setUpcEan(entry.getKey());
        	   allocaReqProdResponse.setSourceLocationId(sourceLocationId);
        	   allocaReqProdResponse.setProductRequestId(productRequestId);
        	   allocaReqProdResponse.setMinTradeableQty(destResponseValues.get(0).getUpt());
               allocaReqProdResponse.setQuantity(destResponseValues.get(0).getQuantity());
               
               allocReqProdList.add(allocaReqProdResponse2);
        	   //Setting Destination values 
        	   for(BlobStorageExcelData response : destResponseValues) {
        		   allocReqProdDest.setAllocatedQuantity(response.getQuantity());           
                   allocReqProdDest.setAllocationType(response.getAllocationType());
                   allocReqProdDest.setIntoStoreDate(response.getIntoStoreDate());
                   allocReqProdDest.setStoreId(response.getStoreId());
                   allocReqProdDest.setUpcEan(response.getUpcEan());
                   allocReqProdDest.setUpt(response.getUpt());
                   allocReqProdDest.setReceiptDate(response.getReceiptDate());
                   allocReqProdDest.setDepotId((sourceLocationId));
                   allocReqProdDest.setCross_dock(response.getCrossDock());
                   allocReqProdDestList.add(allocReqProdDest);
        	   }
        	   
           }*/
           
           
           
          
      /*      for(BlobStorageExcelData response : prodResponse)
           {
             allocaReqProdResponse=new AllocationProdResponse();
             allocaReqProdResponse2=new AllocationProdResponse();
             allocReqProdDest = new AllocationProdDestResponse();

             allocaReqProdResponse.setSourceLocationId(sourceLocationId);
             allocaReqProdResponse.setMinTradeableQty(response.getUpt());
             allocaReqProdResponse.setQuantity(response.getQuantity());
             allocaReqProdResponse.setUpcEan(response.getUpcEan());
             allocaReqProdResponse.setProductRequestId(productRequestId);
             
             allocaReqProdResponse2.setSourceLocationId(sourceLocationId);
             allocaReqProdResponse2.setMinTradeableQty(response.getUpt());
             allocaReqProdResponse2.setQuantity(response.getQuantity());
             allocaReqProdResponse2.setUpcEan(response.getUpcEan());
             allocaReqProdResponse2.setProductRequestId(productRequestId);
             
             allocReqProdList.add(allocaReqProdResponse2);
            
//             allocaReqProdResponse.setAllocReqProdList(allocReqProdList);
            
             allocReqProdDest.setAllocatedQuantity(response.getQuantity());           
             allocReqProdDest.setAllocationType(response.getAllocationType());
             allocReqProdDest.setIntoStoreDate(response.getIntoStoreDate());
             allocReqProdDest.setStoreId(response.getStoreId());
             allocReqProdDest.setUpcEan(response.getUpcEan());
             allocReqProdDest.setUpt(response.getUpt());
             allocReqProdDest.setReceiptDate(response.getReceiptDate());
             allocReqProdDest.setDepotId((sourceLocationId));
             allocReqProdDest.setCross_dock(response.getCrossDock());
             
             allocReqProdDestList.add(allocReqProdDest);
            
//         allocaReqProdResponse.setAllocReqProdDest(allocReqProdDestList);
           }   */         
            
        /*    allocaReqProdResponse.setAllocReqProdDest(allocReqProdDestList);
            allocaReqProdResponse.setAllocReqProdList(allocReqProdList);*/
              
            System.out.println("AllocReqProdList size:" + allocReqProdList.size());
//       System.out.println("AllocReqProdList complete List" + allocReqProdList);
          
            System.out.println("AllocReqProdDestList size:" + allocReqProdDestList.size());
//       System.out.println("AllocReqProdDestList complete List" + allocReqProdDestList);
            String sapResponseXMLString = generateSAPResponseXML(response);    
           return sapResponseXMLString;
	}
	
	private String generateSAPResponseXML(AllocationProdResponse allocaReqProdResponse) {
		String sapXml = null;
		AllocationResponse allocationResponse = null;
		UnitDefinitions unitDefinitions = null;
		SourceStock sourceStock = null;
		Destinations destinations = null;
		Destination destination = null;
		try {	
			if (allocaReqProdResponse != null) {
		List<AllocationProdResponse> allocationProdResponselst = allocaReqProdResponse.getAllocReqProdList();
		
		NDCMnsDocument ndcMnsDocument = new NDCMnsDocument();
		NDCPayLoad ndcPayload = new NDCPayLoad();
		
		List<AllocationResponse> allocResponseList = new ArrayList<AllocationResponse>();
		
		for (AllocationProdResponse allocResponse : allocationProdResponselst) {
			allocationResponse = new AllocationResponse();
	
			allocationResponse.setId(allocResponse.getProductRequestId());
			allocationResponse.setProductUPC(allocResponse.getUpcEan());
			allocationResponse
					.setSourceLocationId(allocResponse.getSourceLocationId());
			
			unitDefinitions = new UnitDefinitions();
			List<UnitDefinitions.UnitDefinition> unitDefinitionlst = unitDefinitions.getUnitDefinition();
			UnitDefinition unitDefinition = new UnitDefinition();
			unitDefinition.setPerUnit(allocResponse.getMinTradeableQty());
			unitDefinition.setId("1");
			unitDefinitionlst.add(unitDefinition);
			
			sourceStock = new SourceStock();
			List<SourceStock.SourceQuantity> lst = sourceStock.getSourceQuantity();
			SourceQuantity sourceQuantity = new SourceQuantity();
			sourceQuantity.setQuantity(allocResponse.getQuantity());
			sourceQuantity.setUnitDefinitionId("1");
			lst.add(sourceQuantity);
			sourceStock.setSourceQuantity(lst);
			
			destinations = new Destinations();
			List<AllocationResponse.Destinations.Destination> destinationslst = destinations.getDestination();
			List<AllocationProdDestResponse> AllocationProdDestResponselst = allocResponse
					.getAllocReqProdDest();
			for (AllocationProdDestResponse destResponse : AllocationProdDestResponselst) {

			String getNdcFilterDepots = "DX,DY";
			if(!Arrays.asList(getNdcFilterDepots.split(",")).contains(destResponse.getStoreId())){		
	        		destination = new Destination();
					// changes start
					destination.setLocationId(destResponse.getStoreId());
					// changes ends
					if (destResponse.getCross_dock() != null) {
						destination.setXDockLocationId(destResponse.getCross_dock());
					}
					final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
					
					DateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
					String intoDepotDate = destResponse.getIntoStoreDate().toString();
					Date intoDepotDt = df.parse(intoDepotDate);
					String intoDepotDateFormat = DATE_FORMAT_YYYY_MM_DD;
					Format intoDepotDateFormatter = new SimpleDateFormat(intoDepotDateFormat);

					destination.setIntoStoreDate(DatatypeFactory.newInstance()
							.newXMLGregorianCalendar(intoDepotDateFormatter.format(intoDepotDt)));

					destination.setOnHandQuantity("0");
					destination.setTargetInventory("0");
					List<AllocationResponse.Destinations.Destination.AllocationQuantity> allocationQty = destination
							.getAllocationQuantity();
					AllocationQuantity allocationQuantity = new AllocationQuantity();
					allocationQuantity.setAllocationType(destResponse.getAllocationType());
					allocationQuantity.setUnitDefinitionId("1");
					allocationQuantity.setQuantity(destResponse.getAllocatedQuantity());
					allocationQty.add(allocationQuantity);
					destination.setAllocationQuantity(allocationQty);
					destinationslst.add(destination);
				}else{
					System.out.println("E-Com Store and Dx Filtered "+destResponse.getStoreId());
				}
			}
			
			allocResponseList.add(allocationResponse);
			destinations.setDestination(destinationslst);
			allocationResponse.setUnitDefinitions(unitDefinitions);
			allocationResponse.setSourceStock(sourceStock);
			allocationResponse.setDestinations(destinations);
		}
		
		System.out.println("Final Response List Size" + allocResponseList.size());
		System.out.println("Final Response List" + allocResponseList.toString());
         ndcPayload.setAllocationResponse(allocResponseList);
         ndcMnsDocument.setPayload(ndcPayload);
         sapXml = convertMNSDocToSAP(ndcMnsDocument);
         System.out.println( "  Fianl XML being ::: " + sapXml );
		} 
		}catch (Exception e) {
			
		}
		return sapXml;
		}	
	
	public String convertMNSDocToSAP(NDCMnsDocument ndcMnsDocument) throws Exception {
		SAPMnSDocument SAPMnSDocument = null;
		try {
			System.out.println("convertMNSDocToSAP Method excute");
			SAPMnSDocument = new SAPMnSDocument();
			SAPMnSDocument = setHeaderTypeValues(ndcMnsDocument, SAPMnSDocument);
			SAPMnSDocument.setPayload(ndcMnsDocument.getPayload());
		} catch (Exception e) {
			System.out.println("Error while Generate NDCMnSDocument {}"+ e);
		}
		System.out.println("Before calling convertJaxBToString excute");
		return convertJaxBToString(SAPMnSDocument);

	}
//	
	public SAPMnSDocument setHeaderTypeValues(NDCMnsDocument ndcMnsDocument, SAPMnSDocument mnSDocument) {
		try {
			SAPHeader header = new SAPHeader();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date date = new Date();
			XMLGregorianCalendar gDateFormatted1 = null;
			try {
				gDateFormatted1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
				System.out.println("gregorian format");
			} catch (Exception e) {
				System.out.println("Time stamp not created");
			}

			// header.setEnvelopeVersion(ApplicationConstants.ENVELOPVERSION);
			// header.setLanguage(ApplicationConstants.LANGUAGE);
			header.setMessageId(UUID.randomUUID().toString());
			header.setCorrelationId("");
			header.setCreatedTimestamp(gDateFormatted1);
			header.setSourceApplicationName("ASO");
			header.setInterfaceId("FOI1137");
			header.setInterfaceName("LiveNDCAllocationResponse");
			header.setPayloadName("AllocationResponse");
			header.setBatchMessageCount(1);
			header.setBatchRecordCount(ndcMnsDocument.getPayload().getAllocationResponse().size());
			header.setIntegrationExtension(new IntegrationExtensionType());
			mnSDocument.setHeader(header);
		} catch (Exception e) {
			System.out.println("Exception occured in setHeaderTypeValues");
		}
		return mnSDocument;

	} 
//	
	public String convertJaxBToString(SAPMnSDocument mnSDocument) throws FileNotFoundException {

		String wmsResponseString = null;
		try {
			System.out.println("convertJaxBToString Method excute");
//			JAXBContext context = JAXBContext.newInstance(SAPMnSDocument.class);
//			System.out.println("after initalize Jaxb COntext {}");
//			Marshaller marshaller = context.createMarshaller();
			Marshaller marshaller = jaxbContextNdc.createMarshaller();
			System.out.println("after Cretae marshaller  {}");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter stringWriter = new StringWriter();

			marshaller.marshal(mnSDocument, stringWriter);
			System.out.println("after un marshall  mnSDocument  {}");
			wmsResponseString = stringWriter.toString();
		} catch (JAXBException jaxbEx) {
			System.out.println(
					"JAXBException Occured inside com.mft.dms.service.AllocationOrchestrationService.generateRequestXML()"+
					jaxbEx);
		}
		return wmsResponseString;
	}


}
	
