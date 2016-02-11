package org.wso2.carbon.sample.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;




@WebService(serviceName = "orgNameSearchService")
public class orgNameSearchService{

	private ArrayList<OrgData> orgDataStore = new ArrayList<OrgData>();
	private static final String JWT_HEADER_NAME = "x-jwt-assertion";

	
	@Resource
	private WebServiceContext wsCtxt;
	
	orgNameSearchService() {
		orgDataStore.add(new OrgData("org0001", "AAA"));
		orgDataStore.add(new OrgData("org0002", "AAB"));
		orgDataStore.add(new OrgData("org0003", "BBB"));
		orgDataStore.add(new OrgData("org0004", "CCC"));
		orgDataStore.add(new OrgData("org0005", "CCD"));
		orgDataStore.add(new OrgData("org0006", "EEE"));
		orgDataStore.add(new OrgData("org0007", "EFF"));
		orgDataStore.add(new OrgData("org0008", "AAC"));
		orgDataStore.add(new OrgData("org0009", "AAD"));
		orgDataStore.add(new OrgData("org0010", "AAK"));
		orgDataStore.add(new OrgData("org0011", "LDL"));
		orgDataStore.add(new OrgData("org0012", "BAA"));
		orgDataStore.add(new OrgData("org0013", "CAA"));
		orgDataStore.add(new OrgData("org0014", "CAB"));
		orgDataStore.add(new OrgData("org0015", "DAD"));
		orgDataStore.add(new OrgData("org0016", "JJJ"));
	}
	
	/** This is a sample web service operation */
	@WebMethod(operationName = "orgNameSearch")
	//@WebResult(name="EchoStructReturnMessage", targetNamespace="")
	//@XmlElement(name="Response", required=true)
	public Response orgNameSearch(@WebParam(name = "partialOrgName") String partialOrgName, 
						@WebParam(name = "pageNumber") int pageNumber,
						@WebParam(name = "pageSize") int pageSize) {
		printJWTAttributes();
		
		System.out.println("Requested page number : " +  pageNumber);
		System.out.println("Requested page size : " +  pageSize);
		
		Response response = new Response();
		
		ArrayList<OrgData> matchingData = new ArrayList<OrgData>(); 
		
		for (OrgData orgData : orgDataStore) {
			if (orgData.getOrgName().contains(partialOrgName)) {
				matchingData.add(orgData);			
			}
		}
		
		if (!matchingData.isEmpty()) {
			response.setTotalMatches(matchingData.size());
			
			int dataOffset = getDataOffset(pageNumber, pageSize);
			
			System.out.println("Total Matches found : " + matchingData.size());
			//System.out.println("dataOffset : " + dataOffset);
			
			if (matchingData.size() > dataOffset) {
				int itemCount = 0;
				
				while (matchingData.size() > dataOffset + itemCount) {
					// System.out.println("itemCount : " + itemCount);
					response.setOrgData(matchingData.get(dataOffset + itemCount++));
					
					if (itemCount == pageSize) {						
						break;
					}
				}
			}			
		}
		
		return response;
	}

	private int getDataOffset(int pageNumber, int pageSize) {
		if (pageNumber == 1) {
			return 0;
		}
		else {
			return (pageNumber - 1) * pageSize;
		}
	}
	
	
	private void printJWTAttributes()
	{
		MessageContext context = wsCtxt.getMessageContext();

		Map map = (Map)context.get(MessageContext.HTTP_REQUEST_HEADERS);
		
		List<String> jwtList = (List<String>) map.get(JWT_HEADER_NAME);
		
		if (jwtList != null) {
			String jwt = jwtList.get(0);
								
			System.out.println(System.getProperty("line.separator"));
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JWT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(jwt);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JWT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(System.getProperty("line.separator"));
		}		
		 
	}

}