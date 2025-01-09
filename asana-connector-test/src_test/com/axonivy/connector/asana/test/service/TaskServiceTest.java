package com.axonivy.connector.asana.test.service;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.asana.Client;
import com.asana.models.Task;
import com.asana.models.User;
import com.asana.models.Workspace;
import com.asana.requests.ItemRequest;
import com.asana.resources.Tasks;
import com.axonivy.connector.asana.AsanaClient;
import com.axonivy.connector.asana.TaskService;
import com.google.api.client.util.DateTime;
import com.google.gson.JsonElement;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
public class TaskServiceTest {

	@Mock
	private Client mockClient;

	@Mock
	private ItemRequest<Task> mockItemRequest;

	@Mock
	private ItemRequest<JsonElement> mockDeleteRequest;

	@Mock
	private Tasks mockTasks;

	private static final String TASK_ID = "12345";

	private Task task;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockClient.tasks = mockTasks;
		AsanaClient.client = mockClient;
		task = new Task();
		task.gid = TASK_ID;
		task.assignee = new User();
		task.createdAt = new DateTime(new Date());
		task.workspace = new Workspace();
		task.modifiedAt = new DateTime(new Date());
	}

	@Test
	public void testGetTask() throws Exception {
		when(mockTasks.getTask(TASK_ID)).thenReturn(mockItemRequest);
		when(mockItemRequest.option("pretty", true)).thenReturn(mockItemRequest);
		when(mockItemRequest.execute()).thenReturn(task);

		Task taskDetails = TaskService.getTask(TASK_ID);

		Assertions.assertNotNull(taskDetails);
		Assertions.assertEquals(TASK_ID, taskDetails.gid);
		verify(mockClient.tasks, times(1)).getTask(TASK_ID);
	}

	@Test
	public void testCreateTask() throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "1");
		data.put("assignee", "12");
		data.put("workspace", "12");
		data.put("due_on", "2101-02-12");

		when(mockTasks.createTask()).thenReturn(mockItemRequest);
		when(mockItemRequest.data(anyMap())).thenReturn(mockItemRequest);
		when(mockItemRequest.option("pretty", true)).thenReturn(mockItemRequest);
		when(mockItemRequest.execute()).thenReturn(task);

		Task result = TaskService.createTask(data);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(TASK_ID, result.gid);
		verify(mockClient.tasks, times(1)).createTask();
	}

	@Test
	public void testUpdateTask() throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "1");
		data.put("assignee", "12");
		data.put("workspace", "12");
		data.put("due_on", "2101-02-12");

		when(mockTasks.update(TASK_ID)).thenReturn(mockItemRequest);
		when(mockItemRequest.data(anyMap())).thenReturn(mockItemRequest);
		when(mockItemRequest.option("pretty", true)).thenReturn(mockItemRequest);
		when(mockItemRequest.execute()).thenReturn(task);

		Task result = TaskService.updateTask(TASK_ID, data);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(TASK_ID, result.gid);
		verify(mockClient.tasks, times(1)).update(TASK_ID);
	}

	@Test
	public void deleteTask() throws Exception {
		when(mockTasks.deleteTask(TASK_ID)).thenReturn(mockDeleteRequest);
		when(mockDeleteRequest.option("pretty", true)).thenReturn(mockDeleteRequest);
		when(mockDeleteRequest.execute()).thenReturn(null);

		TaskService.deleteTask(TASK_ID);

		verify(mockClient.tasks, times(1)).deleteTask(TASK_ID);
	}

}
