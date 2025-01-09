package com.axonivy.connector.asana.demo;

import java.time.LocalDate;

public class CreateTaskRequest {

	@Override
	public String toString() {
		return "CreateTaskRequest [name=" + name + ", workspaceId=" + workspaceId + ", assigneeId=" + assigneeId
				+ ", dueDate=" + dueDate + "]";
	}

	public CreateTaskRequest(String name, String workspaceId, String assigneeId, LocalDate dueDate) {
		super();
		this.name = name;
		this.workspaceId = workspaceId;
		this.assigneeId = assigneeId;
		this.dueDate = dueDate;
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

	private String name;

	private String workspaceId;

	private String assigneeId;

	private LocalDate dueDate;

}
