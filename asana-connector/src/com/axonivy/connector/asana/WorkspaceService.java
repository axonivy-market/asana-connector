package com.axonivy.connector.asana;

import java.util.List;

import com.asana.models.Project;
import com.asana.models.User;
import com.asana.models.Workspace;

public class WorkspaceService {

	public static List<Workspace> getWorkspaces() throws Exception {
		return AsanaClient.client.workspaces.getWorkspaces().option("pretty", true).execute();
	}

	public static List<User> getUsersFromWorkspace(String workspaceId) throws Exception {
		return AsanaClient.client.users.getUsersForWorkspace(workspaceId).option("pretty", true).execute();
	}

	public static List<Project> getProjectsFromWorkspace(String workspaceId) throws Exception {

		List<Project> result = AsanaClient.client.projects.getProjects(null, null, workspaceId).option("pretty", true)
				.execute();
		return result;
	}

}
