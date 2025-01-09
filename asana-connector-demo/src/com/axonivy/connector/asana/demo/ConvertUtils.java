package com.axonivy.connector.asana.demo;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertUtils {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");

	public static Map<String, Object> toCreateData(CreateTaskRequest request) {
		Map<String, Object> data = new HashMap<>();
		data.put("name", request.getName());
		data.put("assignee", request.getAssigneeId());
		data.put("workspace", request.getWorkspaceId());
		data.put("due_on", formatter.format(request.getDueDate()));
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

	public static List<Workspace> convertToWorkspace(List<com.asana.models.Workspace> workspaces) {
		return workspaces.stream().map(w -> new Workspace(w.name, w.gid)).collect(Collectors.toList());
	}

	public static List<User> convertToUsers(List<com.asana.models.User> users) {
		return users.stream().map(w -> new User(w.name, w.gid)).collect(Collectors.toList());
	}

}
