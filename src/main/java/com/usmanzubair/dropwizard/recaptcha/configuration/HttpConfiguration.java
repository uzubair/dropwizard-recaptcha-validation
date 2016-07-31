package com.usmanzubair.dropwizard.recaptcha.configuration;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class HttpConfiguration extends Configuration {
	@NotNull
	@JsonProperty
	private String port;
	
	@NotNull
	@JsonProperty
	private String adminPort;
	
	@NotNull
	@JsonProperty
	private String rootPath;
	
	public String getPort() {
		return port;
	}

	public String getAdminPort() {
		return adminPort;
	}

	public String getRootPath() {
		return rootPath;
	}
}
