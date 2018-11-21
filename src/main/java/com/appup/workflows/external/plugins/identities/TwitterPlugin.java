package com.appup.workflows.external.plugins.identities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(
    type = "twitter-oauth-plugin",
    name = "Twitter Oauth",
    category = IdentityConstants.OAUTH,
    executor = TwitterPluginHandler.class)
public class TwitterPlugin extends IdentityPlugin {

}
