package com.usmanzubair.dropwizard.recaptcha.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.usmanzubair.dropwizard.recaptcha.configuration.RecaptchaConfiguration;

public class RecaptchaServiceImpl implements RecaptchaService {

	private static final String RECAPTCHA_VERIFICATION_SERVER_URL = "https://www.google.com/recaptcha/api/siteverify";
	
	private final RecaptchaConfiguration recaptchaConfiguration;
	private final ObjectMapper objectMapper;
	
	@Inject
	public RecaptchaServiceImpl(RecaptchaConfiguration recaptchaConfiguration, ObjectMapper objectMapper) {
		this.recaptchaConfiguration = recaptchaConfiguration;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public boolean verifyRecaptcha(String recaptcha) {
        if (StringUtils.isBlank(recaptcha)) {
            // TODO: Add logging
            return false;
        }
        
        final HttpClient httpClient = HttpClients.createDefault();
        final HttpPost request = new HttpPost(RECAPTCHA_VERIFICATION_SERVER_URL);
        HttpResponse response = null;

        try {
            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("secret", recaptchaConfiguration.getApiKey()));
            params.add(new BasicNameValuePair("response", recaptcha));
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            response = httpClient.execute(request);

            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpServletResponse.SC_OK) {
                final InputStream inputStream = response.getEntity().getContent();
                final RecaptchaResponse recaptchaResponse = objectMapper.readValue(inputStream, RecaptchaResponse.class);

                return recaptchaResponse.isSuccess();
            }

            // TODO: Add logging
            return false;
        } catch (final Exception e) {
            //TODO: Add logging
            return false;
        } finally {
            request.releaseConnection();
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
	}
	
    static class RecaptchaResponse {

        private final boolean success;

        private final String challengeTimeStamp;

        private final String hostname;

        private final List<String> errorCodes;

        @JsonCreator
        public RecaptchaResponse(@JsonProperty("success") boolean success, @JsonProperty("challenge_ts") String challengeTimeStamp, @JsonProperty("hostname") String hostname, @JsonProperty("error-codes") List<String> errorCodes) {
            this.success = success;
            this.challengeTimeStamp = challengeTimeStamp;
            this.hostname = hostname;
            this.errorCodes = errorCodes;
        }

        @JsonProperty
        public boolean isSuccess() {
            return success;
        }

        @JsonProperty
        public String getChallengeTimestamp() {
            return challengeTimeStamp;
        }

        @JsonProperty
        public String getHostname() {
            return hostname;
        }

        @JsonProperty
        public List<String> getErrorCodes() {
            return errorCodes;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }
    }
}
