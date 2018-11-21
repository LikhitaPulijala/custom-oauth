package com.appup.workflows.external.plugins.identities;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;

/**
 * Generic Identity Plugin for Scribe
 */
public class TwitterPluginHandler implements IdentityPluginHandler<TwitterPlugin, TwitterApi> {
  ServiceBuilder service = null;
  TwitterPlugin config = null;

  /**
   * Initialize the OAuth Plugin
   *
   * @param pluginConfig
   */
  @Override
  public void init(TwitterPlugin pluginConfig) {
    config = pluginConfig;
    service = new ServiceBuilder(config.getKey()).apiSecret(config.getSecret());
    config.setOauth_version("1");
  }

  @Override
  public ServiceBuilder get() {
    return service;
  }


  public TwitterPlugin getConfig() {
    return this.config;
  }

  /**
   * Get Scribe API based on the provider if none then throw Exception
   *
   * @return {@link com.github.scribejava.core.builder.api.DefaultApi20} Instance
   * @throws Exception
   */
  public TwitterApi getScribeClass() {
    return TwitterApi.instance();
  }


  @Override
  public boolean destroy() {

    return true;
  }
}
