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
@com.appup.core.annotation.Plugin(type = "stripe-oauth-plugin", name = "stripe-oauth", category = IdentityConstants.OAUTH,
executor = StripePluginHandler.class)
public class StripePlugin extends CustomIdentityPlugin {
}