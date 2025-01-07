package com.axonivy.connector.asana;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.asana.Client;
import com.asana.models.Task;
import com.asana.requests.ItemRequest;

import ch.ivyteam.ivy.environment.Ivy;

public class TaskDetails {

	public TaskDetails() {

	}

	public TaskDetails(String taskId, String assigneeName, String assigneeId, String createdAt, boolean completed,
			String name, LocalDate startOn, String workspace, String workspaceId, LocalDate dueDate,
			String modifiedAt) {
		super();
		this.taskId = taskId;
		this.assigneeName = assigneeName;
		this.assigneeId = assigneeId;
		this.createdAt = createdAt;
		this.completed = completed;
		this.name = name;
		this.startOn = startOn;
		this.workspace = workspace;
		this.workspaceId = workspaceId;
		this.dueDate = dueDate;
		this.modifiedAt = modifiedAt;
	}

	@Override
	public String toString() {
		return "TaskDetails [taskId=" + taskId + ", assigneeName=" + assigneeName + ", assigneeId=" + assigneeId
				+ ", createdAt=" + createdAt + ", completed=" + completed + ", name=" + name + ", startOn=" + startOn
				+ ", workspace=" + workspace + ", workspaceId=" + workspaceId + ", dueDate=" + dueDate + ", modifiedAt="
				+ modifiedAt + "]";
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartOn() {
		return startOn;
	}

	public void setStartOn(LocalDate startOn) {
		this.startOn = startOn;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String taskId;

	public String assigneeName;

	public String assigneeId;

	public String createdAt;

	public boolean completed;

	public String name;

	public LocalDate startOn;

	public String workspace;

	public String workspaceId;

	public LocalDate dueDate;

	public String modifiedAt;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");

	public static TaskDetails from(Task task) {
		LocalDate startOn = task.startOn != null ? LocalDate.parse(task.startOn.toString()) : LocalDate.now();
		LocalDate dueDate = task.dueOn != null ? LocalDate.parse(task.dueOn.toString()) : LocalDate.now();

		return new TaskDetails(task.gid, task.assignee.name, task.assignee.gid,
				ZonedDateTime.parse(task.createdAt.toString())
						.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
				task.completed, task.name, startOn, task.workspace.name, task.workspace.gid, dueDate, ZonedDateTime
						.parse(task.modifiedAt.toString()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
	}


	public static ItemRequest<Task> to(Client client, TaskDetails task) {
		Ivy.log().info(task);
		return client.tasks.update(task.getTaskId()).data("name", task.name).data("assignee", task.assigneeId)
				.data("completed", task.completed).data("start_on", formatter.format(task.startOn))
				.data("due_on", formatter.format(task.dueDate)).option("pretty", true);
	}

}
