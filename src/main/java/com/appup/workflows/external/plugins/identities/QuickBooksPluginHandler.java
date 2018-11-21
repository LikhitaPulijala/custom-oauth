
package com.appup.workflows.external.plugins.identities;

import java.util.HashMap;
import java.util.Map;

import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.appup.workflows.external.util.OAuthExtConstants;
import com.github.scribejava.apis.QuickBooksApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.java8.Base64;
import com.github.scribejava.core.java8.Base64.Encoder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.CustomOAuth20Service;

public class QuickBooksPluginHandler implements CustomIdentityPluginHandler<QuickBooksPlugin, QuickBooksApi20> {

	ServiceBuilder service = null;
	QuickBooksPlugin config = null;

	@Override
	public void init(QuickBooksPlugin pluginConfig) throws Exception {
		config = pluginConfig;
		service = new ServiceBuilder(config.getKey()).apiSecret(config.getSecret()).state(config.getState());
		config.setOauth_version("2");
//		config.setState(config.getState());

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
	public QuickBooksPlugin getConfig() {
		// TODO Auto-generated method stub
		return config;
	}

	@Override
	public QuickBooksApi20 getScribeClass() {
		// TODO Auto-generated method stub
		return QuickBooksApi20.instance();
	}

	@Override
//	public String getAuthorizationUrl(CustomOAuth20Service service, Map<String, String> params,
//			Map<String, String> additionalData) {
//		String authorizationUrl = service.getAuthorizationUrl(params);
//		System.out.println("========@@@@@@@@@@@$$$$$$$$$$%%%%%%%%%%%%%%%%%%%%authorizationUrl========@@@@@@@@@@@$$$$$$$$$$%%%%%%%%%%%%%%%%%%%%");
//
//		System.out.println(authorizationUrl);
//
//		if (additionalData.size() > 0) {
//			authorizationUrl = authorizationUrl.replace(StepExtConstants.STATE,
//					additionalData.get(StepExtConstants.STATE));
//		}
//
//		return authorizationUrl;
//	}
	public String getAuthorizationUrl(CustomOAuth20Service service, Map<String, String> params,
			Map<String, String> additionalData) {
		return service.getAuthorizationUrl(params);
	}

	final String authorization = "Basic UTB2bXJRT2JWSlRYZ1U3Nm1mTHROZjRCWjRTN0NJS3ZiZm9adzhqUFU5YVhmVVAwQ3k6MG1xMGQzeDR4cGkyb0k5NjlqeHdwcTY5WTNaUWNHeUNpZ0ZGem1sNg==";

	@Override
	public OAuth2AccessToken getAccessToken(String code, CustomOAuth20Service service) {

		// Encode using basic encoder
//		String str=config.getKey():config.getSecret();

//		String encodedUrl = Base64.getEncoder().encodeToString(((config.getKey())+":"+(config.getSecret()))).getBytes();

//		String encodeData =
//				new String(Base64.getEncoder(((config.getKey())+":"+(config.getSecret()))));

		String encodeData = Base64.getEncoder().encodeToString((config.getKey() + ":" + config.getSecret()).getBytes());

		System.out.println(encodeData);
		String str1="Basic";
		String str=str1+" "+encodeData;
		System.out.println(str);

//        String base64encodedString = Base64.getEncoder((config.getKey()):(config.getSecret())).encodeToString(
//           "TutorialsPoint?java8".getBytes("utf-8"));
//        System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(OAuthExtConstants.ACCEPT, OAuthExtConstants.APPLICATION_JSON);
		headers.put(OAuthExtConstants.AUTHORIZATION, str);
		headers.put(OAuthExtConstants.HEADER_KEY_CONTENT_TYPE, OAuthExtConstants.HEADER_VALUE_APPLICATION_URL_ENCODE);

		Map<String, String> bodyParams = new HashMap<String, String>();
		bodyParams.put(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
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
		return null;
	}

}
