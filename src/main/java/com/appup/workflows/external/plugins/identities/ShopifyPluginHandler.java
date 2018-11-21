package com.appup.workflows.external.plugins.identities;

import java.util.HashMap;
import java.util.Map;

import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.appup.workflows.external.util.StepExtConstants;
import com.github.scribejava.apis.ShopifyApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.CustomOAuth20Service;

public class ShopifyPluginHandler implements CustomIdentityPluginHandler<ShopifyPlugin, ShopifyApi20> {

	ServiceBuilder service = null;
	ShopifyPlugin config = null;

	@Override
	public void init(ShopifyPlugin pluginConfig) throws Exception {
		config = pluginConfig;
		service = new ServiceBuilder(config.getKey()).apiSecret(config.getSecret());
		config.setOauth_version("2");
		config.setShop_name(config.getShop_name());

		// Concatenating scopes if not null
		if (config.getScope() != null && !config.getScope().isEmpty()) {
			service.scope(config.getScope().replace(",", "+"));
		}
	}

	@Override
	public boolean destroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ServiceBuilder get() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public ShopifyPlugin getConfig() {
		// TODO Auto-generated method stub
		return config;
	}

	@Override
	public ShopifyApi20 getScribeClass() {
		// TODO Auto-generated method stub
		return ShopifyApi20.instance();
	}

	@Override
	public String getAuthorizationUrl(CustomOAuth20Service service, Map<String, String> params,
			Map<String, String> additionalData) {
		String authorizationUrl = service.getAuthorizationUrl(params);
		
		System.out.println(authorizationUrl);

		if (additionalData.size() > 0) {
			authorizationUrl = authorizationUrl.replace(StepExtConstants.SHOPIFY_STORE_NAME,
					additionalData.get(StepExtConstants.SHOPIFY_STORE_NAME));
		}

		return authorizationUrl;
	}

	@Override
	public OAuth2AccessToken getAccessToken(String code, CustomOAuth20Service service) {

		Map<String, String> bodyParams = new HashMap<String, String>();
		bodyParams.put(OAuthConstants.CLIENT_ID, config.getKey());
		bodyParams.put(OAuthConstants.CLIENT_SECRET, config.getSecret());
		bodyParams.put(OAuthConstants.CODE, code);

		try {
			return service.getAccessToken(code, null, bodyParams, null);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	@Override
	public OAuth2AccessToken getNewAccessToken(String refreshToken, CustomOAuth20Service service) {
		return null;
	}

}
