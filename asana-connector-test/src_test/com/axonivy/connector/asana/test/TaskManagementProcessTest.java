package com.axonivy.connector.asana.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.connector.asana.model.GetTasksRequest;
import com.axonivy.connector.asana.test.mock.MockData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.History;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
public class TaskManagementProcessTest {

	private static final BpmProcess taskManagement = BpmProcess.name("TaskManagement");

	@Test
	void testTaskCreation(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("create(Map<String, Object>)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute(MockData.getMockDataMap());

		History history = result.history();
		assertThat(history.elementNames()).contains("create(Map<String, Object>)");
	}

	@Test
	void testTaskUpdate(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("update(Map<String, Object>,String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("1", MockData.getMockDataMap());

		History history = result.history();
		assertThat(history.elementNames()).contains("update(Map<String, Object>,String)");
	}

	@Test
	void testTasksList(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("getTaskList(GetTasksRequest)");
		GetTasksRequest request = MockData.getMockGetTasksRequest();
		ExecutionResult result = bpmClient.start().subProcess(startable).execute(request);

		History history = result.history();
		assertThat(history.elementNames()).contains("getTaskList(GetTasksRequest)");
	}

	@Test
	void taskRetrieve(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("retrieve(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("1");

		History history = result.history();
		assertThat(history.elementNames()).contains("retrieve(String)");
	}

	@Test
	void taskDelete(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("delete(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("1");

		History history = result.history();
		assertThat(history.elementNames()).contains("delete(String)");
	}

}
