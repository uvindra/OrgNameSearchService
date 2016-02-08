package org.wso2.carbon.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {	
	@XmlElement
	private int totalMatches;
	@XmlElement
	private List<OrgData> orgDataList = new ArrayList<OrgData>();
	
	public int getTotalMatches() {
		return totalMatches;
	}

	public List<OrgData> getOrgDataList() {
		return orgDataList;
	}
	
	public void setTotalMatches(int matchCount) {
		this.totalMatches = matchCount;
	}
	
	public void setOrgData(OrgData orgData) {
		this.orgDataList.add(orgData);
	}
	
	
}
