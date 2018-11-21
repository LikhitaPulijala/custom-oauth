package com.appup.workflows.external.plugins;

import java.util.Map;

import com.appup.core.plugin.PluginHandler;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.BaseApi;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.CustomOAuth20Service;

public interface CustomIdentityPluginHandler<P extends CustomIdentityPlugin, B extends BaseApi>
		extends PluginHandler<P, ServiceBuilder> {

	public ServiceBuilder get();

	public P getConfig();

	/**
	 * Get Scribe API based on the provider if none then throw Exception
	 *
	 * @return {@link com.github.scribejava.core.builder.api.DefaultApi20} Instance
	 * @throws Exception
	 */
	public B getScribeClass();

	public boolean destroy();

	public String getAuthorizationUrl(CustomOAuth20Service service, Map<String, String> params,
			Map<String, String> additionalData);

	public OAuth2AccessToken getAccessToken(String code, CustomOAuth20Service service);

	public OAuth2AccessToken getNewAccessToken(String refreshToken, CustomOAuth20Service serivce);
}