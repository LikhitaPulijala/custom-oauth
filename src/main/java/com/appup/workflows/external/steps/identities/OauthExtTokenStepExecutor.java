package com.appup.workflows.external.steps.identities;

import java.util.Map;

import com.appup.core.constants.StepStatus;
import com.appup.core.server.HttpRequest;
import com.appup.core.workflow.step.Context;
import com.appup.core.workflow.step.executor.StepExecutor;
import com.appup.workflows.external.plugins.CustomIdentityPlugin;
import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.BaseApi;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.CustomOAuth20Service;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * @author prem <prem@agilecrm.com>
 */

public class OauthExtTokenStepExecutor implements
		StepExecutor<OauthExtTokenStep> {

	@Override
	public String execute(OauthExtTokenStep step, Map map, Context context)
			throws Exception {

		HttpRequest httpRequest = context.getRequest();
		CustomOAuth20Service customOAuth20Service;
		OAuth20Service auth20Service = null;
		OAuth10aService auth10aService;
		StringBuilder scope;
		JsonElement jsonElement = null;
		OAuth1RequestToken requestToken;
		OAuth1AccessToken auth1AccessToken;
		OAuth2AccessToken auth2AccessToken;

		CustomIdentityPluginHandler pluginExecutor = context.getPluginManager()
				.getPluginByName(step.getPlugin_name(),
						CustomIdentityPluginHandler.class);
		ServiceBuilder serviceBuilder = pluginExecutor.get();
		CustomIdentityPlugin plugin = pluginExecutor.getConfig();

		Gson gSon = new Gson();

		boolean isV2 = plugin.getOauth_version().equalsIgnoreCase(
				String.valueOf(2));

		Boolean isCustomPlugin = false;
		try {
			String customPlugin = plugin.getCustom_oauth();

			System.out.println(customPlugin);

			if (customPlugin != null) {
				isCustomPlugin = Boolean.parseBoolean(customPlugin);
			}
		} catch (Exception e) {
			System.out.println("Error while casting cutomplugin attribute.");
		}

		String code = null;

		if (httpRequest.getQueryParameters().get("code") != null) {
			code = httpRequest.getQueryParameters().get("code").getFirst();
		}

		if (serviceBuilder != null && plugin.getRedirect_url() != null) {
			serviceBuilder.callback(plugin.getRedirect_url());
		}

		if (isCustomPlugin) {
			if (code != null) {
				customOAuth20Service = serviceBuilder
						.build((BaseApi<CustomOAuth20Service>) pluginExecutor
								.getScribeClass());


				auth2AccessToken = pluginExecutor.getAccessToken(code,
						customOAuth20Service);

				jsonElement = gSon.toJsonTree(auth2AccessToken);
			}
		} else {
			if (isV2) {


				if (code != null) {
					auth20Service = serviceBuilder
							.build((BaseApi<OAuth20Service>) pluginExecutor
									.getScribeClass());
					// get code from the exchange object and fetches the token
					// using
					// code of respective identity provider and extract token


					auth2AccessToken = auth20Service.getAccessToken(code);


					jsonElement = gSon.toJsonTree(auth2AccessToken);
				}
			} else {
				if (httpRequest.getQueryParameters().get("oauth_verifier") != null) {
					auth10aService = serviceBuilder
							.build((BaseApi<OAuth10aService>) pluginExecutor
									.getScribeClass());
					requestToken = new OAuth1RequestToken(
							httpRequest.getQueryParameters().get("oauth_token")
									.getFirst(), context.getRequest()
									.getCookieMap().get("token_secret")
									.getValue());
					auth1AccessToken = auth10aService.getAccessToken(
							requestToken,
							httpRequest.getQueryParameters()
									.get("oauth_verifier").getFirst());
					jsonElement = gSon.toJsonTree(auth1AccessToken);
				}
			}

		}

		System.out.println(gSon.fromJson(jsonElement, Map.class));

		map.put(step.getOutput_variable(),
				gSon.fromJson(jsonElement, Map.class));
		return StepStatus.SUCCESS;
	}
}
