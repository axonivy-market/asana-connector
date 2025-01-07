package com.axonivy.connector.asana;

import java.io.IOException;

import com.asana.Client;

import ch.ivyteam.ivy.environment.Ivy;

public class TaskService {

	private static Client client = Client.accessToken(Ivy.var().get("asana.accessToken"));

	public static TaskDetails getTask(String taskId) throws IOException {
		return TaskDetails.from(client.tasks.getTask(taskId).option("pretty", true).execute());

	}

	public static String createTask(CreateTaskRequest request) throws IOException {
		return CreateTaskRequest.to(client, request).execute().gid;
	}

	public static String updateTask(TaskDetails task) throws IOException {
		return TaskDetails.to(client, task).execute().gid;
	}

	public static void deleteTask(String taskId) throws IOException {
		client.tasks.deleteTask(taskId).option("pretty", true).execute();
	}

}
