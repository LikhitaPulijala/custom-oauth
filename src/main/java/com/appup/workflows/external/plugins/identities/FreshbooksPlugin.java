package com.appup.workflows.external.plugins.identities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

/**
 * FreshbooksPlugin Configuration class for Freshbooks.
 *
 * @author prem <prem@agilecrm.com>
 */
@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(type = "freshbooks-oauth-plugin", name = "freshbooks-oauth", category = IdentityConstants.OAUTH, executor = FreshbooksPluginHandler.class)
public class FreshbooksPlugin extends CustomIdentityPlugin {

}