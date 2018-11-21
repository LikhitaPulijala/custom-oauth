package com.appup.workflows.external.plugins.identities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.appup.workflows.external.plugins.CustomIdentityPlugin;

@Setter
@Getter
@NoArgsConstructor
@com.appup.core.annotation.Plugin(type = "quickbooks-oauth-plugin", name = "quickbooks-oauth", category = IdentityConstants.OAUTH, executor = QuickBooksPluginHandler.class)
public class QuickBooksPlugin extends CustomIdentityPlugin {
}