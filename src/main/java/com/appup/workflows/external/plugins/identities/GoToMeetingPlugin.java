package com.appup.workflows.external.plugins.identities;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * GoToMeetingPlugin Configuration class for GoToMeeting.
 *
 * @author prem <prem@agilecrm.com>
 */
@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(type = "gotomeeting-oauth-plugin", name = "gotomeeting-oauth", category = IdentityConstants.OAUTH, executor = GoToMeetingPluginHandler.class)
public class GoToMeetingPlugin extends CustomIdentityPlugin {

}