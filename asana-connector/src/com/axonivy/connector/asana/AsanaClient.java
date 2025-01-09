package com.axonivy.connector.asana;

import com.asana.Client;

import ch.ivyteam.ivy.environment.Ivy;

public class AsanaClient {
	
	public static Client client = Client.accessToken(Ivy.var().get("asana.accessToken"));

}
