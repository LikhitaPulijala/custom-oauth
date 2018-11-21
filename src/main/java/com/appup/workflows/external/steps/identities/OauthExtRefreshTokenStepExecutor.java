package com.appup.workflows.external.steps.identities;

import java.util.Map;

import com.appup.core.constants.StepStatus;
import com.appup.core.workflow.step.Context;
import com.appup.core.workflow.step.executor.StepExecutor;
import com.appup.workflows.external.plugins.CustomIdentityPlugin;
import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.BaseApi;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.CustomOAuth20Service;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class OauthExtRefreshTokenStepExecutor implements
		StepExecutor<OauthExtRefreshTokenStep> {

	@Override
	public String execute(OauthExtRefreshTokenStep step, Map map,
			Context context) throws Exception {

		CustomOAuth20Service customOAuth20Service;
		OAuth20Service service;
		OAuth2AccessToken accessToken;

		// Getting respective identity provider builder service
		CustomIdentityPluginHandler pluginExecutor = context.getPluginManager()
				.getPluginByName(step.getPlugin_name(),
						CustomIdentityPluginHandler.class);
		ServiceBuilder serviceBuilder = pluginExecutor.get();
		CustomIdentityPlugin plugin = pluginExecutor.getConfig();

		Boolean isCustomPlugin = false;
		try {
			String customPlugin = plugin.getCustom_oauth();
			if (customPlugin != null) {
				isCustomPlugin = Boolean.parseBoolean(customPlugin);
			}
		} catch (Exception e) {
			System.out.println("Error while casting cutomplugin attribute.");
		}

		if (isCustomPlugin) {
			customOAuth20Service = serviceBuilder.callback(
					plugin.getRedirect_url()).build(
					(BaseApi<CustomOAuth20Service>) pluginExecutor
							.getScribeClass());

			accessToken = pluginExecutor.getNewAccessToken(
					step.getRefresh_token(), customOAuth20Service);

		} else {
			// Getting Oauth20Service object based on type of Oauth
			service = serviceBuilder
					.build((BaseApi<OAuth20Service>) pluginExecutor
							.getScribeClass());

			// Getting refresh token using scribe methods
			accessToken = service.refreshAccessToken(step.getRefresh_token());
		}

		Gson gSon = new Gson();
		JsonElement jsonElement = gSon.toJsonTree(accessToken);
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		// Setting Complete Refresh token entity details to output variable
		map.put(step.getOutput_variable(), jsonObject.toString());
		return StepStatus.SUCCESS;
	}
}

