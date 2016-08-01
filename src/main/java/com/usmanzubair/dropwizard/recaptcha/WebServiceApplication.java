package com.usmanzubair.dropwizard.recaptcha;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.usmanzubair.dropwizard.recaptcha.configuration.WebServiceAppConfiguration;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebServiceApplication extends Application<WebServiceAppConfiguration> {

    private GuiceBundle<WebServiceAppConfiguration> guiceBundle;

    public GuiceBundle<WebServiceAppConfiguration> getGuiceBundle() {
        return guiceBundle;
    }
	
    @Override
    public void initialize(Bootstrap<WebServiceAppConfiguration> bootstrap) {  	
        // Google Guice will register bindings in BaseModule and will package scan
        // com.usmanzubair to automatically find and register resources, and health checks
        guiceBundle = GuiceBundle.<WebServiceAppConfiguration> newBuilder()
                .enableAutoConfig(getClass().getPackage().getName())
                .addModule(new BaseModule())
                .build();
        bootstrap.addBundle(guiceBundle);
        
        // asset bundle to serve static resources.
        final AssetsBundle assetBundle = new AssetsBundle("/assets/", "/", "index.html");
        bootstrap.addBundle(assetBundle);
    }
    
	@Override
	public void run(WebServiceAppConfiguration configuration, Environment environment) throws Exception {
	}

    public static void main(String[] args) throws Exception {
        new WebServiceApplication().run(args);
    }
}
