package com.axonivy.connector.asana.model;

import java.util.List;

public class GetTasksRequest {

	private String projectGid;

	private String completedSince;

	private String offset;

	private Integer limit;

	private List<String> optFields;

	private Boolean optPretty;

	public GetTasksRequest() {
	}

	public String getProjectGid() {
		return projectGid;
	}

	public void setProjectGid(String projectGid) {
		this.projectGid = projectGid;
	}

	public String getCompletedSince() {
		return completedSince;
	}

	public void setCompletedSince(String completedSince) {
		this.completedSince = completedSince;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<String> getOptFields() {
		return optFields;
	}

	public void setOptFields(List<String> optFields) {
		this.optFields = optFields;
	}

	public Boolean getOptPretty() {
		return optPretty;
	}

	public void setOptPretty(Boolean optPretty) {
		this.optPretty = optPretty;
	}

	public GetTasksRequest(String projectGid, String completedSince, String offset, Integer limit,
			List<String> optFields, Boolean optPretty) {
		this.projectGid = projectGid;
		this.completedSince = completedSince;
		this.offset = offset;
		this.limit = limit;
		this.optFields = optFields;
		this.optPretty = optPretty;
	}

}
