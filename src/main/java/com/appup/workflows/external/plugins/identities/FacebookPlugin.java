package com.appup.workflows.external.plugins.identities;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FacebookOauthPlugin Configuration class for Facebook.
 *
 * @author vinay <vinay@appup.com>
 */
@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(
        type = "facebook-oauth-plugin",
        name = "Facebook Oauth",
        category = IdentityConstants.OAUTH,
        executor = FacebookPluginHandler.class)
public class FacebookPlugin extends CustomIdentityPlugin {

}
