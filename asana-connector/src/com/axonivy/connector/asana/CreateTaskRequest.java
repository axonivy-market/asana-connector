package com.axonivy.connector.asana;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.asana.Client;
import com.asana.models.Task;
import com.asana.requests.ItemRequest;

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

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");

	public static ItemRequest<Task> to(Client client, CreateTaskRequest task) {
		try {
			return client.tasks.createTask().data("name", task.name).data("workspace", task.workspaceId)
					.data("assignee", task.assigneeId).data("due_on", formatter.format(task.dueDate))
					.option("pretty", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
