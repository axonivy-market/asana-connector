package com.axonivy.connector.asana.service;

import java.util.List;
import java.util.Map;

import com.asana.models.Task;
import com.axonivy.connector.asana.model.GetTasksRequest;

public class TaskService {

	public static Task getTask(String taskId) throws Exception {
		return AsanaClient.client.tasks.getTask(taskId).option("pretty", true).execute();

	}

	public static List<Task> getTaskList(GetTasksRequest request) throws Exception {
		return AsanaClient.client.tasks
				.getTasksForProject(request.getProjectGid(), request.getCompletedSince(), request.getOffset(),
						request.getLimit(), request.getOptFields(), request.getOptPretty())
				.option("pretty", true).execute();
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
