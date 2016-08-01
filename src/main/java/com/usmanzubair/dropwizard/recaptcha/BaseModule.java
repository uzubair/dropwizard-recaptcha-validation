package com.usmanzubair.dropwizard.recaptcha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.usmanzubair.dropwizard.recaptcha.configuration.RecaptchaConfiguration;
import com.usmanzubair.dropwizard.recaptcha.configuration.WebServiceAppConfiguration;
import com.usmanzubair.dropwizard.recaptcha.service.RecaptchaService;
import com.usmanzubair.dropwizard.recaptcha.service.RecaptchaServiceImpl;

public class BaseModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RecaptchaService.class).to(RecaptchaServiceImpl.class);
	}

	/**
	 * This provide ensures that we are using the same {@link ObjectMapper} throughout the application. It's properties
	 * are configured in {@link WebServiceApplication#initialize(io.dropwizard.setup.Bootstrap)}
	 */
	@Provides
	@Singleton
	public ObjectMapper providesObjectMapper() {
		return new ObjectMapper();
	}
	
	@Provides
	@Singleton
	public RecaptchaConfiguration providesRecaptchaConfiguration(WebServiceAppConfiguration configuration) {
		return configuration.getRecaptchaConfiguration();
	}
}
