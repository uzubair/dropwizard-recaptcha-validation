package com.usmanzubair.dropwizard.recaptcha.service;

public interface RecaptchaService {
	boolean verifyRecaptcha(String recaptcha);
}
