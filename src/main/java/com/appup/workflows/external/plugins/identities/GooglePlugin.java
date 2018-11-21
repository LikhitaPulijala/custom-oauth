package com.appup.workflows.external.plugins.identities;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(
    type = "google-oauth-plugin",
    name = "Google Oauth",
    category = IdentityConstants.OAUTH,
    executor = GooglePluginHandler.class)
public class GooglePlugin extends CustomIdentityPlugin {

}
