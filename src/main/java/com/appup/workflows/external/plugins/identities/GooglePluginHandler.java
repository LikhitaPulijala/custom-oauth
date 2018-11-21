//package com.appup.workflows.external.plugins.identities;
//
//import java.util.Map;
//
//import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
//import com.github.scribejava.apis.GoogleApi20;
//import com.github.scribejava.core.builder.ServiceBuilder;
//import com.github.scribejava.core.model.OAuth2AccessToken;
//import com.github.scribejava.core.oauth.CustomOAuth20Service;
//
///**
// * Generic Identity Plugin for Scribe
// */
//public class GooglePluginHandler implements CustomIdentityPluginHandler<GooglePlugin, GoogleApi20> {
//  ServiceBuilder service = null;
//  GooglePlugin config = null;
//
//  /**
//   * Initialize the OAuth Plugin
//   *
//   * @param pluginConfig
//   */
//  @Override
//  public void init(GooglePlugin pluginConfig) {
//    config = pluginConfig;
//    service = new ServiceBuilder(config.getKey()).apiSecret(config.getSecret());
//    config.setOauth_version("2");
//  }
//
//  @Override
//  public ServiceBuilder get() {
//    return service;
//  }
//
//
//  public GooglePlugin getConfig() {
//    return this.config;
//  }
//
//  /**
//   * Get Scribe API based on the provider if none then throw Exception
//   *
//   * @return {@link com.github.scribejava.core.builder.api.DefaultApi20} Instance
//   * @throws Exception
//   */
//  public GoogleApi20 getScribeClass() {
//    return GoogleApi20.instance();
//  }
//
//
//  @Override
//  public boolean destroy() {
//
//    return true;
//  }
//
//@Override
//public String getAuthorizationUrl(CustomOAuth20Service service, Map<String, String> params,
//		Map<String, String> additionalData) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public OAuth2AccessToken getAccessToken(String code, CustomOAuth20Service service) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public OAuth2AccessToken getNewAccessToken(String refreshToken, CustomOAuth20Service serivce) {
//	// TODO Auto-generated method stub
//	return null;
//}
//}

package com.appup.workflows.external.plugins.identities;

import java.util.Map;

import com.appup.workflows.external.plugins.CustomIdentityPluginHandler;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.CustomOAuth20Service;

/**
 * GoToMeetingPlugin Configuration class for GoToMeeting.
 *
 * @author prem <prem@agilecrm.com>
 */

public class GooglePluginHandler implements CustomIdentityPluginHandler<GooglePlugin, GoogleApi20> {

	ServiceBuilder service = null;
	GooglePlugin config = null;

	@Override
	public void init(GooglePlugin pluginConfig) throws Exception {
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
	public GooglePlugin getConfig() {
		// TODO Auto-generated method stub
		return config;
	}

	/**
	 * Get GoToMeetingApi20 Instance
	 */

	@Override
	public GoogleApi20 getScribeClass() {
		// TODO Auto-generated method stub
		return GoogleApi20.instance();
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