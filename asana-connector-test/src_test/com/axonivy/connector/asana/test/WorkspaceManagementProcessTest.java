package com.axonivy.connector.asana.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.History;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
public class WorkspaceManagementProcessTest {

	private static final BpmProcess workspaceManagement = BpmProcess.name("WorkspaceManagement");

	@Test
	void testGetWorkspaces(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = workspaceManagement.elementName("getWorkspaces()");
		ExecutionResult result = bpmClient.start().subProcess(startable).execute();

		History history = result.history();
		assertThat(history.elementNames()).contains("getWorkspaces()");
	}

	@Test
	void testGetUsersFromWorkspace(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = workspaceManagement.elementName("getUsersFromWorkspace(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("123");

		History history = result.history();
		assertThat(history.elementNames()).contains("getUsersFromWorkspace(String)");
	}
	
	@Test
	void testGetProjectsFromWorkspace(BpmClient bpmClient) throws NoSuchFieldException {
		BpmElement startable = workspaceManagement.elementName("getProjectsFromWorkspace(String)");

		ExecutionResult result = bpmClient.start().subProcess(startable).execute("123");

		History history = result.history();
		assertThat(history.elementNames()).contains("getProjectsFromWorkspace(String)");
	}

}
