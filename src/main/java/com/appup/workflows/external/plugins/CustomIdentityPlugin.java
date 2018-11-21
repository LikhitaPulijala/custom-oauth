package com.appup.workflows.external.plugins;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.appup.workflows.external.plugins.identities.IdentityPlugin;

/**
 * @author prem <prem@agilecrm.com>
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomIdentityPlugin extends IdentityPlugin {
	String custom_oauth;
	String redirect_url;
	String shop_name;
	String state;
	String oauth_version;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
}