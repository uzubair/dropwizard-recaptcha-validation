package com.usmanzubair.dropwizard.recaptcha.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignupRequest {
	private final String fullname;
	private final String email;
	private final String recaptcha;
	
	@JsonCreator
	public SignupRequest(@JsonProperty("fullname") String fullname, @JsonProperty("email") String email, @JsonProperty("recaptcha") String recaptcha) {
		this.fullname = fullname;
		this.email = email;
		this.recaptcha = recaptcha;
	}

	public String getFullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public String getRecaptcha() {
		return recaptcha;
	}
}