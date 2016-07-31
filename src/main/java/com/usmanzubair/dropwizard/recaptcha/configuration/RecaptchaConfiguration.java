package com.usmanzubair.dropwizard.recaptcha.configuration;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class RecaptchaConfiguration extends Configuration {
	@NotNull
	@JsonProperty("api_key")
	private String apiKey;
	
	public String getApiKey() {
		return apiKey;
	}
}
