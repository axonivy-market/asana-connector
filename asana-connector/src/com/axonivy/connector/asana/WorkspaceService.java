package com.axonivy.connector.asana;

import java.io.IOException;
import java.util.List;

import com.asana.Client;

import ch.ivyteam.ivy.environment.Ivy;

public class WorkspaceService {
	private static Client client = Client
			.accessToken("2/1209074611478328/1209074635879771:2119d88676db69f9c500e2f1a6846b69");

	public static List<Workspace> getWorkspaces() throws IOException {
		List<Workspace> result = client.workspaces.getWorkspaces().option("pretty", true).execute().stream()
				.map(w -> new Workspace(w.name, w.gid)).toList();
		return result;
	}

	public static List<User> getUsersFromWorkspace(String workspaceId) throws IOException {
		Ivy.log().info(workspaceId);
		List<User> result = client.users.getUsersForWorkspace(workspaceId).option("pretty", true).execute().stream()
				.map(w -> new User(w.name, w.gid)).toList();
		return result;
	}

}
