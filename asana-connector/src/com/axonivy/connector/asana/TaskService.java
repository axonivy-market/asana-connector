package com.axonivy.connector.asana;

import java.io.IOException;

import com.asana.Client;

public class TaskService {
	private static Client client = Client
			.accessToken("2/1209074611478328/1209074635879771:2119d88676db69f9c500e2f1a6846b69");

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
