//package com.appup.workflows.external.steps.identities;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.appup.core.constants.StepStatus;
//import com.appup.core.server.HttpStatusCode;
//import com.appup.core.util.TemplateUtil;
//import com.appup.core.workflow.step.Context;
//import com.appup.core.workflow.step.KeyValue;
//import com.appup.core.workflow.step.executor.StepExecutor;
//import com.appup.workflows.external.plugins.CustomIdentityPlugin;
//import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
//import com.github.scribejava.core.builder.ServiceBuilder;
//import com.github.scribejava.core.builder.api.BaseApi;
//import com.github.scribejava.core.model.OAuth1RequestToken;
//import com.github.scribejava.core.oauth.CustomOAuth20Service;
//import com.github.scribejava.core.oauth.OAuth10aService;
//import com.github.scribejava.core.oauth.OAuth20Service;
//
///**
// * @author prem <prem@agilecrm.com>
// */
//
//public class OauthExtStepExecutor implements StepExecutor<OauthExtStep> {
//
//	@Override
//	public String execute(OauthExtStep step, Map map, Context context)
//			throws Exception {
//
//		CustomOAuth20Service customOAuth20Service;
//		OAuth20Service auth20Service;
//		OAuth10aService auth10aService;
//		OAuth1RequestToken requestToken;
//		String authorizationUrl = null;
//		StringBuilder scope = null;
//
//		CustomIdentityPluginHandler pluginExecutor = context.getPluginManager()
//				.getPluginByName(step.getPlugin_name(),
//						CustomIdentityPluginHandler.class);
//
//		ServiceBuilder serviceBuilder = pluginExecutor.get();
//		CustomIdentityPlugin plugin = pluginExecutor.getConfig();
//
//		boolean isV2 = plugin.getOauth_version().equalsIgnoreCase(
//				String.valueOf(2));
//
//		// If redirect URL value is null then throw an exception with message
//		if (plugin.getRedirect_url().isEmpty()) {
//			throw new RuntimeException("Required Redirect URL");
//		}
//
//		// Concatenating additional params
//		Map<String, String> params = new HashMap<>();
//		if (step.getParams() != null && !step.getParams().isEmpty()) {
//			for (KeyValue param : step.getParams()) {
//				params.put(
//						param.getKey(),
//						TemplateUtil.getValue(step.getValue_type(),
//								param.getValue(), map));
//			}
//		}
//
//		Boolean isCustomPlugin = false;
//		try {
//			String customPlugin = plugin.getCustom_oauth();
//			if (customPlugin != null) {
//				isCustomPlugin = Boolean.parseBoolean(customPlugin);
//			}
//		} catch (Exception e) {
//			System.out.println("Error while casting customplugin attribute.");
//		}
//
//		if (isCustomPlugin) {
//			customOAuth20Service = serviceBuilder.callback(
//					plugin.getRedirect_url()).build(
//					(BaseApi<CustomOAuth20Service>) pluginExecutor
//							.getScribeClass());
//
//			authorizationUrl = pluginExecutor.getAuthorizationUrl(
//					customOAuth20Service, params, step.getAdditional_data());
//		} else {
//			if (isV2) {
//				auth20Service = serviceBuilder.callback(
//						plugin.getRedirect_url()).build(
//						(BaseApi<OAuth20Service>) pluginExecutor
//								.getScribeClass());
//
//				authorizationUrl = auth20Service.getAuthorizationUrl(params);
//			}
//		}
//
//		context.getResponse().setStatus(HttpStatusCode.FOUND);
//		context.getResponse().sendRedirect(authorizationUrl);
//		return StepStatus.SUCCESS;
//	}
//
//}
package com.appup.workflows.external.steps.identities;

import java.util.HashMap;
import java.util.Map;

import com.appup.core.constants.StepStatus;
import com.appup.core.server.HttpStatusCode;
import com.appup.core.util.TemplateUtil;
import com.appup.core.workflow.step.Context;
import com.appup.core.workflow.step.KeyValue;
import com.appup.core.workflow.step.executor.StepExecutor;
import com.appup.workflows.external.plugins.CustomIdentityPlugin;
import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.BaseApi;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.CustomOAuth20Service;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;

public class OauthExtStepExecutor implements StepExecutor<OauthExtStep> {

	@Override
	public String execute(OauthExtStep step, Map map, Context context) throws Exception {

		CustomOAuth20Service customOAuth20Service;
		OAuth20Service auth20Service;
		OAuth10aService auth10aService;
		OAuth1RequestToken requestToken;
		String authorizationUrl = null;
		StringBuilder scope = null;
//	    boolean isV2 = identityPlugin.getOauth_version().equalsIgnoreCase(String.valueOf(2));


		CustomIdentityPluginHandler pluginExecutor = context.getPluginManager().getPluginByName(step.getPlugin_name(),
				CustomIdentityPluginHandler.class);
		System.out.println("********************GET METHOD*********************");

		ServiceBuilder serviceBuilder = pluginExecutor.get();
		System.out.println("********************pluginExecutor.get()*********************");
		System.out.println(pluginExecutor.get());

		System.out.println("********************AFTER GET METHOD*********************");

		CustomIdentityPlugin plugin = pluginExecutor.getConfig();

		boolean isV2 = plugin.getOauth_version().equalsIgnoreCase(String.valueOf(2));

		// If redirect URL value is null then throw an exception with message
		if (plugin.getRedirect_url().isEmpty()) {
			throw new RuntimeException("Required Redirect URL");
		}

		// Concatenating additional params
		Map<String, String> params = new HashMap<>();
		if (step.getParams() != null && !step.getParams().isEmpty()) {
			for (KeyValue param : step.getParams()) {
				params.put(param.getKey(), TemplateUtil.getValue(step.getValue_type(), param.getValue(), map));
			}
		}

		Map<String, String> additionalData = new HashMap<>();
		if (step.getAdditional_data() != null && !step.getAdditional_data().isEmpty()) {
			for (KeyValue additional : step.getAdditional_data()) {
				additionalData.put(additional.getKey(),
						TemplateUtil.getValue(step.getValue_type(), additional.getValue(), map));
			}
		}

		Boolean isCustomPlugin = false;
		try {
			String customPlugin = plugin.getCustom_oauth();
			if (customPlugin != null) {
				isCustomPlugin = Boolean.parseBoolean(customPlugin);
			}
		} catch (Exception e) {
			System.out.println("Error while casting cutomplugin attribute.");
		}

		System.out.println(" ***** log congif ***** ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");

		System.out.println(step.getPlugin_name());
		System.out.println(isCustomPlugin);
		System.out.println(plugin.getRedirect_url());
		System.out.println(isV2);

		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println(" ***** log config ***** ");

		if (isCustomPlugin) {
			customOAuth20Service = serviceBuilder.callback(plugin.getRedirect_url())
					.build((BaseApi<CustomOAuth20Service>) pluginExecutor.getScribeClass());

			authorizationUrl = pluginExecutor.getAuthorizationUrl(customOAuth20Service, params, additionalData);
		} else {
			if (isV2) {
				auth20Service = serviceBuilder.callback(plugin.getRedirect_url())
						.build((BaseApi<OAuth20Service>) pluginExecutor.getScribeClass());

				authorizationUrl = auth20Service.getAuthorizationUrl(params);
			}
		}

		System.out.println(" ***** log authorizationUrl ***** ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");

		System.out.println(authorizationUrl);

		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println(" ***** log authorizationUrl ***** ");

		context.getResponse().setStatus(HttpStatusCode.FOUND);
		context.getResponse().sendRedirect(authorizationUrl);
		return StepStatus.SUCCESS;
	}

}
