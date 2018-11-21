package com.appup.workflows.external.plugins.identities;

import java.util.Map;

import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.github.scribejava.apis.PayPalApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.CustomOAuth20Service;

/**
 * PaypalPlugin Configuration class for Paypal.
 *
 * @author prem <prem@agilecrm.com>
 */

public class PaypalPluginHandler implements CustomIdentityPluginHandler<PaypalPlugin, PayPalApi20> {

	ServiceBuilder service = null;
	PaypalPlugin config = null;

	public void init(PaypalPlugin pluginConfig) throws Exception {
		config = pluginConfig;
		service = new ServiceBuilder(config.getKey()).apiSecret(config.getSecret());
		config.setOauth_version("2");

		// Concatenating scopes if not null
		if (config.getScope() != null && !config.getScope().isEmpty()) {
			service.scope(config.getScope().replace(",", "+"));
		}
	}

	/**
	 * Initialize the PaypalPlugin
	 *
	 * @param pluginConfig
	 */

	public ServiceBuilder get() {
		// TODO Auto-generated method stub
		return service;
	}

	public PaypalPlugin getConfig() {
		// TODO Auto-generated method stub
		return config;
	}

	/**
	 * Get PayPalApi20 Instance
	 */

	public PayPalApi20 getScribeClass() {
		// TODO Auto-generated method stub
		return PayPalApi20.instance();
	}

	public boolean destroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAuthorizationUrl(CustomOAuth20Service customOAuth20Service, Map<String, String> params,
			Map<String, String> additionalData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2AccessToken getAccessToken(String code, CustomOAuth20Service customOAuth20Service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2AccessToken getNewAccessToken(String refreshToken, CustomOAuth20Service customOAuth20Service) {
		// TODO Auto-generated method stub
		return null;
	}

}
