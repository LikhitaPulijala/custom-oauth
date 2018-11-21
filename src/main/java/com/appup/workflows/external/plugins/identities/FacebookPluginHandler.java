package com.appup.workflows.external.plugins.identities;

import java.util.Map;

import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.CustomOAuth20Service;

/**
 * GoToMeetingPlugin Configuration class for GoToMeeting.
 *
 * @author prem <prem@agilecrm.com>
 */

public class FacebookPluginHandler implements CustomIdentityPluginHandler<FacebookPlugin, FacebookApi> {

	ServiceBuilder service = null;
	FacebookPlugin config = null;

	@Override
	public void init(FacebookPlugin pluginConfig) throws Exception {
		config = pluginConfig;
		service = new ServiceBuilder(config.getKey()).apiSecret(config.getSecret());
		config.setOauth_version("2");

		// Concatenating scopes if not null
		if (config.getScope() != null && !config.getScope().isEmpty()) {
			service.scope(config.getScope().replace(",", "+"));
		}
	}

	/**
	 * Initialize the GoToMeetingPlugin
	 *
	 * @param pluginConfig
	 */

	@Override
	public ServiceBuilder get() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public FacebookPlugin getConfig() {
		// TODO Auto-generated method stub
		return config;
	}

	/**
	 * Get GoToMeetingApi20 Instance
	 */

	@Override
	public FacebookApi getScribeClass() {
		// TODO Auto-generated method stub
		return FacebookApi.instance();
	}

	@Override
	public boolean destroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAuthorizationUrl(CustomOAuth20Service service, Map<String, String> params,
			Map<String, String> additionalData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2AccessToken getAccessToken(String code, CustomOAuth20Service service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2AccessToken getNewAccessToken(String refreshToken, CustomOAuth20Service serivce) {
		// TODO Auto-generated method stub
		return null;
	}

}