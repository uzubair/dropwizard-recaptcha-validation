package com.usmanzubair.dropwizard.recaptcha.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class WebServiceAppConfiguration extends Configuration {
	/**
	 * Http settings defined in configuration.yml file
	 */
	@Valid
	@NotNull
	@JsonProperty("http")
	private final HttpConfiguration httpConfiguration = new HttpConfiguration();
	
	/**
	 * Recaptcha settings defined in configuration.yml file
	 */
	@Valid
	@NotNull
	@JsonProperty("recaptcha")
	private final RecaptchaConfiguration recaptchaConfiguration = new RecaptchaConfiguration();
	
	public HttpConfiguration getHttpConfiguration() {
		return httpConfiguration;
	}
	
	public RecaptchaConfiguration getRecaptchaConfiguration() {
		return recaptchaConfiguration;
	}
}
