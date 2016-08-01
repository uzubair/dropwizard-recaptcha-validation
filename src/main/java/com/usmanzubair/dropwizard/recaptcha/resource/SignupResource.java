package com.usmanzubair.dropwizard.recaptcha.resource;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.usmanzubair.dropwizard.recaptcha.configuration.RecaptchaConfiguration;
import com.usmanzubair.dropwizard.recaptcha.service.RecaptchaService;

@Path("/v1")
public class SignupResource {
	private RecaptchaService recaptchaService;
	
	@Inject
	public SignupResource(RecaptchaService recaptchaService) {
		this.recaptchaService = recaptchaService;
	}
	
	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signup(@Context HttpServletRequestWrapper request, SignupRequest signupRequest) {
		if (signupRequest == null) {
			// TODO: add logging
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (!recaptchaService.verifyRecaptcha(signupRequest.getRecaptcha())) {
			// TODO: Add logging and error message
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		// TODO: signup the user
		
		return Response.status(Status.NO_CONTENT).build();
	}
}
