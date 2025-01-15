package com.axonivy.connector.asana.test.service;

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
import com.asana.models.Project;
import com.asana.models.User;
import com.asana.models.Workspace;
import com.asana.requests.CollectionRequest;
import com.asana.resources.Projects;
import com.asana.resources.Users;
import com.asana.resources.Workspaces;
import com.axonivy.connector.asana.service.AsanaClient;
import com.axonivy.connector.asana.service.WorkspaceService;
import com.axonivy.connector.asana.test.mock.MockData;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
public class WorkspaceServiceTest {

	@Mock
	private Client mockClient;

	@Mock
	private CollectionRequest<Workspace> mockWorkspaceRequest;

	@Mock
	private CollectionRequest<User> mockUserRequest;
	
	@Mock
	private CollectionRequest<Project> mockProjectRequest;

	@Mock
	private Workspaces mockWorkspaces;

	@Mock
	private Users mockUsers;
	
	@Mock
	private Projects mockProjects;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		AsanaClient.client = mockClient;
		mockClient.workspaces = mockWorkspaces;
		mockClient.users = mockUsers;
		mockClient.projects = mockProjects;
	}

	@Test
	public void testGetWorkspaces() throws Exception {
		Workspace workspace = new Workspace();
		workspace.gid = MockData.GID;
		workspace.name = "workspace";
		List<Workspace> workspaces = Collections.singletonList(workspace);

		when(mockWorkspaces.getWorkspaces()).thenReturn(mockWorkspaceRequest);
		when(mockWorkspaceRequest.option("pretty", true)).thenReturn(mockWorkspaceRequest);
		when(mockWorkspaceRequest.execute()).thenReturn(workspaces);

		List<Workspace> results = WorkspaceService.getWorkspaces();

		Assertions.assertNotNull(results);
		Assertions.assertEquals(results.size(), workspaces.size());
		verify(mockClient.workspaces, times(1)).getWorkspaces();
	}

	@Test
	public void testGetUsersFromWorkspace() throws Exception {
		User user = new User();
		user.gid = MockData.GID;
		user.name = "workspace";
		List<User> users = Collections.singletonList(user);

		when(mockUsers.getUsersForWorkspace(MockData.GID)).thenReturn(mockUserRequest);
		when(mockUserRequest.option("pretty", true)).thenReturn(mockUserRequest);
		when(mockUserRequest.execute()).thenReturn(users);

		List<User> results = WorkspaceService.getUsersFromWorkspace(MockData.GID);

		Assertions.assertNotNull(results);
		Assertions.assertEquals(results.size(), users.size());
		verify(mockClient.users, times(1)).getUsersForWorkspace(MockData.GID);
	}
	
	@Test
	public void testGetProjectsFromWorkspace() throws Exception {
		Project project = new Project();
		project.gid = MockData.GID;
		project.name = "workspace";
		List<Project> projects = Collections.singletonList(project);

		when(mockProjects.getProjects(null, null, MockData.GID)).thenReturn(mockProjectRequest);
		when(mockProjectRequest.option("pretty", true)).thenReturn(mockProjectRequest);
		when(mockProjectRequest.execute()).thenReturn(projects);

		List<Project> results = WorkspaceService.getProjectsFromWorkspace(MockData.GID);

		Assertions.assertNotNull(results);
		Assertions.assertEquals(results.size(), projects.size());
		verify(mockClient.projects, times(1)).getProjects(null, null, MockData.GID);
	}

}
