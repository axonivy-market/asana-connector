package com.axonivy.connector.asana.service;

import com.asana.Client;

import ch.ivyteam.ivy.environment.Ivy;

public class AsanaClient {

	public static Client client = Client.accessToken(Ivy.var().get("asanaConnector.accessToken"));

}
