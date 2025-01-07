package com.axonivy.connector.asana;

import java.io.IOException;
import java.util.List;

import com.asana.Client;

import ch.ivyteam.ivy.environment.Ivy;

public class WorkspaceService {

	private static Client client = Client.accessToken(Ivy.var().get("asana.accessToken"));

	public static List<Workspace> getWorkspaces() throws IOException {
		List<Workspace> result = client.workspaces.getWorkspaces().option("pretty", true).execute().stream()
				.map(w -> new Workspace(w.name, w.gid)).toList();
		return result;
	}

	public static List<User> getUsersFromWorkspace(String workspaceId) throws IOException {	
		List<User> result = client.users.getUsersForWorkspace(workspaceId).option("pretty", true).execute().stream()
				.map(w -> new User(w.name, w.gid)).toList();
		return result;
	}

}
