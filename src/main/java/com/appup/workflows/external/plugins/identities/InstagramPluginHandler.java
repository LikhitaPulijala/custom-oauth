package com.appup.workflows.external.plugins.identities;

import java.util.HashMap;
import java.util.Map;

import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.appup.workflows.external.util.OAuthExtConstants;
import com.github.scribejava.apis.InstagramApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.CustomOAuth20Service;

/**
 * FreshbooksPlugin Configuration class for Freshbooks.
 *
 * @author prem <prem@agilecrm.com>
 */

public class InstagramPluginHandler implements CustomIdentityPluginHandler<InstagramPlugin, InstagramApi20> {

	ServiceBuilder service = null;
	InstagramPlugin config = null;

	@Override
	public void init(InstagramPlugin pluginConfig) throws Exception {
		config = pluginConfig;
		service = new ServiceBuilder(config.getKey()).apiSecret(config.getSecret());
		config.setOauth_version("2");

		// Concatenating scopes if not null
		if (config.getScope() != null && !config.getScope().isEmpty()) {
			service.scope(config.getScope().replace(",", "+"));
		}
	}

	/**
	 * Initialize the FreshbooksPlugin
	 *
	 * @param pluginConfig
	 */

	@Override
	public ServiceBuilder get() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public InstagramPlugin getConfig() {
		// TODO Auto-generated method stub
		return config;
	}

	/**
	 * Get FreshbooksApi20 Instance
	 */
	@Override
	public InstagramApi20 getScribeClass() {
		// TODO Auto-generated method stub
		return InstagramApi20.instance();
	}

	@Override
	public boolean destroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAuthorizationUrl(CustomOAuth20Service service, Map<String, String> params,
			Map<String, String> additionalData) {
		return service.getAuthorizationUrl(params);
	}

	@Override
	public OAuth2AccessToken getAccessToken(String code, CustomOAuth20Service service) {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(OAuthExtConstants.HEADER_KEY_CONTENT_TYPE, OAuthExtConstants.HEADER_VALUE_APPLICATION_URL_ENCODE);

		Map<String, String> bodyParams = new HashMap<String, String>();
		bodyParams.put(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
		bodyParams.put(OAuthConstants.CLIENT_ID, config.getKey());
		bodyParams.put(OAuthConstants.CLIENT_SECRET, config.getSecret());
		bodyParams.put(OAuthConstants.REDIRECT_URI, config.getRedirect_url());
		bodyParams.put(OAuthConstants.CODE, code);

		try {
			return service.getAccessToken(code, null, bodyParams, headers);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	@Override
	public OAuth2AccessToken getNewAccessToken(String refreshToken, CustomOAuth20Service service) {

		try {
			return service.getRefreshToken(refreshToken, null, null, null);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

}
