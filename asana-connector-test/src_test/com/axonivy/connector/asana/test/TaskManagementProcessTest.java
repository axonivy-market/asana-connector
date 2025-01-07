package com.axonivy.connector.asana.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.axonivy.connector.asana.CreateTaskRequest;
import com.axonivy.connector.asana.TaskDetails;

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
		BpmElement startable = taskManagement.elementName("create(CreateTaskRequest)");
		CreateTaskRequest requestData = new CreateTaskRequest();
		requestData.setAssigneeId("1");
		requestData.setName("Task");
		requestData.setDueDate(LocalDate.now());

		ExecutionResult result = bpmClient.start().subProcess(startable).execute(requestData);

		History history = result.history();
		assertThat(history.elementNames()).contains("create(CreateTaskRequest)");
	}

	@Test
	void taskUpdate(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = taskManagement.elementName("update(TaskDetails)");
		TaskDetails requestData = new TaskDetails();
		requestData.setAssigneeId("1");
		requestData.setName("Task");
		requestData.setAssigneeName("User");
		requestData.setCompleted(true);
		requestData.setCreatedAt(LocalDate.now().toString());
		requestData.setDueDate(LocalDate.now());
		requestData.setModifiedAt(LocalDate.now().toString());
		requestData.setStartOn(LocalDate.now());
		requestData.setTaskId("1");
		requestData.setWorkspace("WorkSpace");
		requestData.setWorkspaceId("1");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute(requestData);

		History history = result.history();
		assertThat(history.elementNames()).contains("update(TaskDetails)");
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
