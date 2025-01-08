package com.axonivy.connector.asana.test.service;

import org.junit.jupiter.api.Assertions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.asana.Client;
import com.asana.models.User;
import com.asana.models.Workspace;
import com.asana.requests.CollectionRequest;
import com.asana.resources.Users;
import com.asana.resources.Workspaces;
import com.axonivy.connector.asana.WorkspaceService;

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
	private Workspaces mockWorkspaces;

	@Mock
	private Users mockUsers;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockClient.workspaces = mockWorkspaces;
		mockClient.users = mockUsers;
		WorkspaceService.client = mockClient;
	}

	@Test
	public void testGetWorkspaces() throws Exception {
		Workspace workspace = new Workspace();
		workspace.gid = "123";
		workspace.name = "workspace";
		List<Workspace> workspaces = Collections.singletonList(workspace);

		when(mockWorkspaces.getWorkspaces()).thenReturn(mockWorkspaceRequest);
		when(mockWorkspaceRequest.option("pretty", true)).thenReturn(mockWorkspaceRequest);
		when(mockWorkspaceRequest.execute()).thenReturn(workspaces);

		List<com.axonivy.connector.asana.Workspace> results = WorkspaceService.getWorkspaces();

		Assertions.assertNotNull(results);
		Assertions.assertEquals(results.size(), workspaces.size());
		verify(mockClient.workspaces, times(1)).getWorkspaces();
	}

	@Test
	public void testGetUsersFromWorkspace() throws Exception {
		String gid = "123";
		User user = new User();
		user.gid = "123";
		user.name = "workspace";
		List<User> users = Collections.singletonList(user);

		when(mockUsers.getUsersForWorkspace(gid)).thenReturn(mockUserRequest);
		when(mockUserRequest.option("pretty", true)).thenReturn(mockUserRequest);
		when(mockUserRequest.execute()).thenReturn(users);

		List<com.axonivy.connector.asana.User> results = WorkspaceService.getUsersFromWorkspace(gid);

		Assertions.assertNotNull(results);
		Assertions.assertEquals(results.size(), users.size());
		verify(mockClient.users, times(1)).getUsersForWorkspace(gid);
	}

}
