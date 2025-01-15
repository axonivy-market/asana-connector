package com.axonivy.connector.asana.demo.model;

import java.util.List;
import java.util.stream.Collectors;

public class Resource {

	private String name;

	private String id;

	@Override
	public String toString() {
		return "Resource [name=" + name + ", id=" + id + "]";
	}

	public Resource() {
		super();
	}

	public Resource(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static List<Resource> fromWorkspaces(List<com.asana.models.Workspace> workspaces) {
		return workspaces.stream().map(w -> new Resource(w.name, w.gid)).collect(Collectors.toList());
	}

	public static List<Resource> fromUsers(List<com.asana.models.User> users) {
		return users.stream().map(w -> new Resource(w.name, w.gid)).collect(Collectors.toList());
	}

	public static List<Resource> fromProjects(List<com.asana.models.Project> projects) {
		return projects.stream().map(w -> new Resource(w.name, w.gid)).collect(Collectors.toList());
	}

}
