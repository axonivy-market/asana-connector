package com.axonivy.connector.asana.demo.model;

import java.time.LocalDate;

public class CreateTaskRequest {

	private String name;

	private String workspaceId;

	private String assigneeId;

	private LocalDate dueDate;

	private String projectId;
	
	private LocalDate startOn;

	@Override
	public String toString() {
		return "CreateTaskRequest [name=" + name + ", workspaceId=" + workspaceId + ", assigneeId=" + assigneeId
				+ ", dueDate=" + dueDate + ", projectId=" + projectId + ", startOn=" + startOn + "]";
	}

	public CreateTaskRequest(String name, String workspaceId, String assigneeId, LocalDate dueDate, String projectId,
			LocalDate startOn) {
		super();
		this.name = name;
		this.workspaceId = workspaceId;
		this.assigneeId = assigneeId;
		this.dueDate = dueDate;
		this.projectId = projectId;
		this.startOn = startOn;
	}

	public CreateTaskRequest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public LocalDate getStartOn() {
		return startOn;
	}

	public void setStartOn(LocalDate startOn) {
		this.startOn = startOn;
	}

}
