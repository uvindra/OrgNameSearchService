package org.wso2.carbon.sample.service;

public class OrgData {
	private String orgID;;
	private String orgName;
	
	OrgData() {}
	
	OrgData(String orgID, String orgName) {
		this.orgID = orgID;
		this.orgName = orgName;
	}
	
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getOrgID() {
		return orgID;
	}
	
	public String getOrgName() {
		return orgName;
	}
}
