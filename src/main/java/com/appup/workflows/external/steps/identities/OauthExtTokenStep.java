package com.appup.workflows.external.steps.identities;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import com.appup.core.config.workflow.steps.Step;
import com.appup.core.workflow.step.KeyValue;
import com.appup.workflows.external.util.StepExtConstants;

/**
 * @author prem <prem@agilecrm.com>
 */

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@com.appup.core.annotation.Step(ui = "", type = StepExtConstants.OAUTH_EXT_TOKEN_STEP, executor = OauthExtTokenStepExecutor.class)
public class OauthExtTokenStep extends Step {

	String value_type;
	List<KeyValue> params;
	String redirect_url;
	String output_variable;
	List<KeyValue> additional_data;
	private String plugin_name;
}