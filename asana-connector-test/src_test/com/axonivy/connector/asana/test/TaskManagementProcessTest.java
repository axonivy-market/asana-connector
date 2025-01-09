package com.axonivy.connector.asana.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

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
	void taskCreation(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("create(Map<String, Object>)");
		Map<String, Object> data = new HashMap<>();
		data.put("name", "1");
		data.put("assignee", "12");
		data.put("workspace", "12");
		data.put("due_on", "2101-02-12");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute(data);

		History history = result.history();
		assertThat(history.elementNames()).contains("create(Map<String, Object>)");
	}

	@Test
	void taskUpdate(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("update(Map<String, Object>,String)");
		Map<String, Object> data = new HashMap<>();
		data.put("name", "1");
		data.put("assignee", "12");
		data.put("workspace", "12");
		data.put("due_on", "2101-02-12");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("1", data);

		History history = result.history();
		assertThat(history.elementNames()).contains("update(Map<String, Object>,String)");
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
