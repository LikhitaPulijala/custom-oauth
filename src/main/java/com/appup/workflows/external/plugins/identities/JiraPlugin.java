package com.appup.workflows.external.plugins.identities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

/**
 * JiraPlugin Configuration class for Jira.
 *
 * @author prem <prem@agilecrm.com>
 */

@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(type = "jira-oauth-plugin", name = "jira-oauth", category = IdentityConstants.OAUTH, executor = JiraPluginHandler.class)
public class JiraPlugin extends CustomIdentityPlugin {
}