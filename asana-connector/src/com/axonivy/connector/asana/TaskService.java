package com.axonivy.connector.asana;

import java.util.Map;

import com.asana.models.Task;

public class TaskService {

	public static Task getTask(String taskId) throws Exception {
		return AsanaClient.client.tasks.getTask(taskId).option("pretty", true).execute();

	}

	public static Task createTask(Map<String, Object> data) throws Exception {
		return AsanaClient.client.tasks.createTask().data(data).option("pretty", true).execute();
	}

	public static Task updateTask(String taskId, Map<String, Object> data) throws Exception {
		return AsanaClient.client.tasks.update(taskId).data(data).option("pretty", true).execute();
	}

	public static void deleteTask(String taskId) throws Exception {
		AsanaClient.client.tasks.deleteTask(taskId).execute();
	}

}
