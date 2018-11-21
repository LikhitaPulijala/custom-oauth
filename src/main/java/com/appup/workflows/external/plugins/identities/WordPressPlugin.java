package com.appup.workflows.external.plugins.identities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

/**
 * StripePlugin Configuration class for Stripe.
 *
 * @author prem <prem@agilecrm.com>
 */

@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(type = "wordpress-oauth-plugin", name = "wordpress-oauth", category = IdentityConstants.OAUTH,
executor = WordPressPluginHandler.class)
public class WordPressPlugin extends CustomIdentityPlugin {
}