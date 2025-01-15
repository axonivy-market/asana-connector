package com.axonivy.connector.asana.test.mock;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.asana.models.Task;
import com.asana.models.User;
import com.asana.models.Workspace;
import com.axonivy.connector.asana.model.GetTasksRequest;
import com.google.api.client.util.DateTime;

public class MockData {
	
	public static final String TASK_ID = "12345";
	
	public static final String PROJECT_ID = "12345";
	
	public static final String GID = "12345";
	
	public static Task getMockTask() {
		Task task = new Task();
		task.gid = TASK_ID;
		task.assignee = new User();
		task.createdAt = new DateTime(new Date());
		task.workspace = new Workspace();
		task.modifiedAt = new DateTime(new Date());
		return task;
	}
	
	public static Map<String, Object> getMockDataMap() {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "1");
		data.put("assignee", "12");
		data.put("workspace", "12");
		data.put("due_on", "2101-02-12");
		return data;
	}
	
	public static GetTasksRequest getMockGetTasksRequest() {
		GetTasksRequest request = new GetTasksRequest();
		request.setProjectGid(MockData.PROJECT_ID);
		request.setOptFields(Arrays.asList("name", "assignee.name", "created_at", "start_on", "due_on", "completed",
				"workspace.name", "modified_at"));
		return request;
	}

}
