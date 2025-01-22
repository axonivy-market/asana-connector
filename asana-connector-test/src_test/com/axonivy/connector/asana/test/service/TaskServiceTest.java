package com.axonivy.connector.asana.test.service;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.asana.Client;
import com.asana.models.Task;
import com.asana.requests.CollectionRequest;
import com.asana.requests.ItemRequest;
import com.asana.resources.Tasks;
import com.axonivy.connector.asana.model.GetTasksRequest;
import com.axonivy.connector.asana.service.AsanaClient;
import com.axonivy.connector.asana.service.TaskService;
import com.axonivy.connector.asana.test.mock.MockData;
import com.google.gson.JsonElement;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
public class TaskServiceTest {

	@Mock
	private Client mockClient;

	@Mock
	private ItemRequest<Task> mockItemRequest;

	@Mock
	private CollectionRequest<Task> mockItemCollection;

	@Mock
	private ItemRequest<JsonElement> mockDeleteRequest;

	@Mock
	private Tasks mockTasks;

	private Task task;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockClient.tasks = mockTasks;
		AsanaClient.client = mockClient;
		task = MockData.getMockTask();
	}

	@Test
	public void testGetTask() throws Exception {
		when(mockTasks.getTask(MockData.TASK_ID)).thenReturn(mockItemRequest);
		when(mockItemRequest.option("pretty", true)).thenReturn(mockItemRequest);
		when(mockItemRequest.execute()).thenReturn(task);

		Task taskDetails = TaskService.getTask(MockData.TASK_ID);

		Assertions.assertNotNull(taskDetails);
		Assertions.assertEquals(MockData.TASK_ID, taskDetails.gid);
		verify(mockClient.tasks, times(1)).getTask(MockData.TASK_ID);
	}

	@Test
	public void testGetTaskList() throws Exception {
		GetTasksRequest request = MockData.getMockGetTasksRequest();
		when(mockTasks.getTasksForProject(request.getProjectGid(), request.getCompletedSince(), request.getOffset(),
				request.getLimit(), request.getOptFields(), request.getOptPretty())).thenReturn(mockItemCollection);
		when(mockItemCollection.option("pretty", true)).thenReturn(mockItemCollection);
		when(mockItemCollection.execute()).thenReturn(Collections.singletonList(task));

		List<Task> taskList = TaskService.getTaskList(request);

		Assertions.assertNotNull(taskList);
		Assertions.assertEquals(MockData.TASK_ID, taskList.get(0).gid);
		verify(mockClient.tasks, times(1)).getTasksForProject(request.getProjectGid(), request.getCompletedSince(),
				request.getOffset(), request.getLimit(), request.getOptFields(), request.getOptPretty());
	}

	@Test
	public void testCreateTask() throws Exception {
		when(mockTasks.createTask()).thenReturn(mockItemRequest);
		when(mockItemRequest.data(anyMap())).thenReturn(mockItemRequest);
		when(mockItemRequest.option("pretty", true)).thenReturn(mockItemRequest);
		when(mockItemRequest.execute()).thenReturn(task);

		Task result = TaskService.createTask(MockData.getMockDataMap());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(MockData.TASK_ID, result.gid);
		verify(mockClient.tasks, times(1)).createTask();
	}

	@Test
	public void testUpdateTask() throws Exception {
		when(mockTasks.update(MockData.TASK_ID)).thenReturn(mockItemRequest);
		when(mockItemRequest.data(anyMap())).thenReturn(mockItemRequest);
		when(mockItemRequest.option("pretty", true)).thenReturn(mockItemRequest);
		when(mockItemRequest.execute()).thenReturn(task);

		Task result = TaskService.updateTask(MockData.TASK_ID, MockData.getMockDataMap());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(MockData.TASK_ID, result.gid);
		verify(mockClient.tasks, times(1)).update(MockData.TASK_ID);
	}

	@Test
	public void deleteTask() throws Exception {
		when(mockTasks.deleteTask(MockData.TASK_ID)).thenReturn(mockDeleteRequest);
		when(mockDeleteRequest.option("pretty", true)).thenReturn(mockDeleteRequest);
		when(mockDeleteRequest.execute()).thenReturn(null);

		TaskService.deleteTask(MockData.TASK_ID);

		verify(mockClient.tasks, times(1)).deleteTask(MockData.TASK_ID);
	}

}
