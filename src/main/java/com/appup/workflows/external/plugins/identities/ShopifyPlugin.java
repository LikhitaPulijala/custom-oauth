package com.appup.workflows.external.plugins.identities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(type = "shopify-oauth-plugin", name = "shopify-oauth", category = IdentityConstants.OAUTH, executor = ShopifyPluginHandler.class)
public class ShopifyPlugin extends CustomIdentityPlugin {
}