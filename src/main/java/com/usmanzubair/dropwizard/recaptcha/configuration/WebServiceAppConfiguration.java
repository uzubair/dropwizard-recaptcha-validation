package com.usmanzubair.dropwizard.recaptcha.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class WebServiceAppConfiguration extends Configuration {
	/**
	 * Recaptcha settings defined in configuration.yml file
	 */
	@Valid
	@NotNull
	@JsonProperty("recaptcha")
	private final RecaptchaConfiguration recaptchaConfiguration = new RecaptchaConfiguration();
	
	public RecaptchaConfiguration getRecaptchaConfiguration() {
		return recaptchaConfiguration;
	}
}
