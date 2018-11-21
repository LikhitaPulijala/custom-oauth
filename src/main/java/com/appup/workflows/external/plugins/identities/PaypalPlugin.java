package com.appup.workflows.external.plugins.identities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

/**
 * PaypalPlugin Configuration class for Paypal.
 *
 * @author prem <prem@agilecrm.com>
 */

@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(type = "paypal-oauth-plugin", name = "paypal-oauth", category = IdentityConstants.OAUTH, executor = PaypalPluginHandler.class)
public class PaypalPlugin extends CustomIdentityPlugin {
}