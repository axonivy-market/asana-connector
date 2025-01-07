package com.axonivy.connector.asana;

public class Workspace {
	
	@Override
	public String toString() {
		return "Workspace [name=" + name + ", id=" + id + "]";
	}

	public Workspace() {
		super();
	}

	public Workspace(String name, String id) {
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

	private String name;
	
	private String id;
	

}
