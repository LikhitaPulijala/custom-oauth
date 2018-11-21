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
@com.appup.core.annotation.Plugin(type = "instagram-oauth-plugin", name = "instagram-oauth", category = IdentityConstants.OAUTH,
executor = InstagramPluginHandler.class)
public class InstagramPlugin extends CustomIdentityPlugin {
}