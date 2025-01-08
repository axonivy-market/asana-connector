package com.axonivy.connector.asana;

import com.asana.Client;

import ch.ivyteam.ivy.environment.Ivy;

public class TaskService {

	public static Client client = Client.accessToken(Ivy.var().get("asana.accessToken"));

	public static TaskDetails getTask(String taskId) throws Exception {
		return TaskDetails.from(client.tasks.getTask(taskId).option("pretty", true).execute());

	}

	public static String createTask(CreateTaskRequest request) throws Exception {
		return CreateTaskRequest.toCreateItemRequest(client, request).execute().gid;
	}

	public static String updateTask(TaskDetails task) throws Exception {
		return TaskDetails.toUpdateItemRequest(client, task).execute().gid;
	}

	public static void deleteTask(String taskId) throws Exception {
		client.tasks.deleteTask(taskId).execute();
	}

}
