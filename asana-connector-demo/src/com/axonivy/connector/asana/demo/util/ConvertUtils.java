package com.axonivy.connector.asana.demo.util;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.axonivy.connector.asana.demo.model.CreateTaskRequest;
import com.axonivy.connector.asana.demo.model.TaskDetails;

public class ConvertUtils {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static Map<String, Object> toCreateData(CreateTaskRequest request) {
		Map<String, Object> data = new HashMap<>();
		data.put("name", request.getName());
		data.put("assignee", request.getAssigneeId());
		data.put("workspace", request.getWorkspaceId());
		data.put("projects", Collections.singletonList(request.getProjectId()));
		data.put("due_on", formatter.format(request.getDueDate()));
		data.put("start_on", formatter.format(request.getStartOn()));
		return data;
	}

	public static Map<String, Object> toUpdateData(TaskDetails request) {
		Map<String, Object> data = new HashMap<>();
		data.put("name", request.getName());
		data.put("assignee", request.getAssigneeId());
		data.put("completed", request.isCompleted());
		data.put("start_on", formatter.format(request.getStartOn()));
		data.put("due_on", formatter.format(request.getDueDate()));
		return data;
	}

	public static List<TaskDetails> convertToTasks(List<com.asana.models.Task> tasks) {
		List<TaskDetails> result = tasks.stream().map(task -> TaskDetails.from(task)).collect(Collectors.toList());
		return result;
	}

}
