package com.jwebmp.examples.demos.homepage.display.login;

import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.attributes.InputPasswordTypeAttributes;
import com.jwebmp.core.base.html.inputs.InputEmailType;
import com.jwebmp.core.base.html.inputs.InputPasswordType;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.general.MintonCheckBox;
import com.jwebmp.examples.demos.homepage.display.forgotpassword.ForgotPasswordEvent;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonSizeOptions;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSSpacingOptions;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;

import static com.jwebmp.core.base.html.attributes.InputButtonTypeAttributes.*;

public class LoginPart
		extends DisplayPart
{
	public LoginPart()
	{
		super();
		BSCardBody<?> body = addCardBody();

		BSRow loginFormRow = new BSRow<>();
		//		loginFormRow.add(loginForm(true, false, true));

		body.add(new AlertMessage(null).setAddDismissButton(false));
		body.add(loginFormRow);

		//body.add(buildButtonRow(loginForm, loginInputGroup, inputGroup));

		BSNavTabs<?> tabs = new BSNavTabs<>();
		tabs.addTab("Login", new Div<>().add(loginForm(true, false, true)), true);
		tabs.addTab("Register", new Div<>().add(loginForm(true, true, false)), false);
		tabs.addTab("Forgot Password", new Div<>().add(loginForm(false, false, false)), false);

		body.add(tabs);

		addStyle("margin-bottom:1rem;");
	}

	private BSForm loginForm(boolean includePassword, boolean includeConfirmPassword, boolean rememberMe)
	{
		BSForm<?> loginForm = new BSForm<>();
		loginForm.asMe()
		         .setStyleInput(true);
		loginForm.addClass(BSColumnOptions.Col_12);

		BSFormInputGroup<?, InputEmailType<?>> loginInputGroup = buildLoginInput(loginForm);
		if (includePassword)
		{
			BSFormInputGroup<?, InputPasswordType<?>> inputGroup = buildPasswordInput(loginForm);
		}
		if (includeConfirmPassword)
		{
			BSFormInputGroup<?, InputPasswordType<?>> inputGroup = buildConfirmPasswordInput(loginForm);
		}

		if (rememberMe)
		{
			BSRow checkRow = new BSRow();
			MintonCheckBox checkBox;
			checkRow.add(checkBox = new MintonCheckBox<>("Remember Me Forever", true, "checkbox-purple").addClass(BSColumnOptions.Col_12));
			checkBox.getCheckBox()
			        .bind("subscribe.rememberMe")
			        .addStyle("margin-left", "5px;")
			        .addStyle("margin-top", "15px;");
			loginForm.add(checkRow);
		}

		if (!includeConfirmPassword && rememberMe)
		{
			BSButton submitButton = buildLoginButton(loginForm);
			submitButton.addEvent(new LoginEvent(submitButton));
		}
		else if (includeConfirmPassword && !rememberMe)
		{
			BSButton submitButton = buildRegisterButton(loginForm);
			submitButton.addEvent(new RegisterEvent(submitButton));
		}
		else if (!includePassword && !includeConfirmPassword && !rememberMe)
		{
			BSButton submitButton = buildForgotPasswordButton(loginForm);
			submitButton.addEvent(new ForgotPasswordEvent(submitButton));
		}

		return loginForm;
	}

	/**
	 * Id's are set so you can see the angular built
	 *
	 * @param loginForm
	 *
	 * @return
	 */
	private BSFormInputGroup<?, InputEmailType<?>> buildLoginInput(BSForm<?> loginForm)
	{
		BSFormInputGroup<?, InputEmailType<?>> loginInputGroup = loginForm.createEmailInput("subscribe.emailAddress", null, true)
		                                                                  .prepend(FontAwesome.icon(FontAwesomeIcons.at));
		loginInputGroup
				.setStyleInputGroupTextWithValidation(true)
				.updateOnBlur()
				.addClass(BSSpacingOptions.Margin_Bottom_3);

		loginInputGroup.getInput()
		               .setRequired()
		               .addAttribute(AutoComplete, "username")
		               .setPlaceholder("Email Address")
		               .setPattern("regex.emailField");

		loginInputGroup.addMessage(InputErrorValidations.pattern, "Please enter a valid email address");

		loginForm.add(loginInputGroup);
		return loginInputGroup;
	}

	private BSFormInputGroup<?, InputPasswordType<?>> buildPasswordInput(BSForm<?> loginForm)
	{
		BSFormInputGroup<?, InputPasswordType<?>> inputGroup = loginForm.createPasswordInput("subscribe.password", null, true)
		                                                                .prepend(FontAwesome.icon(FontAwesomeIcons.key));

		inputGroup.addClass(BSSpacingOptions.Margin_Bottom_3)
		          .getInput()
		          .setMinimumLength(6)
		          .setRequired()
		          .addAttribute(InputPasswordTypeAttributes.AutoComplete, "current-password")
		          .setPlaceholder("Password")
		          .setPattern("regex.password");
		inputGroup.addMessage(InputErrorValidations.pattern, "Password needs to be at least 6 characters long and have a number.");

		loginForm.add(inputGroup);
		return inputGroup;
	}

	private BSFormInputGroup<?, InputPasswordType<?>> buildConfirmPasswordInput(BSForm<?> loginForm)
	{
		BSFormInputGroup<?, InputPasswordType<?>> inputGroup = loginForm.createPasswordInput("subscribe.confirmPassword", null, true)
		                                                                .prepend(FontAwesome.icon(FontAwesomeIcons.key));

		inputGroup.addClass(BSSpacingOptions.Margin_Bottom_3)
		          .getInput()
		          .setMinimumLength(6)
		          .setRequired()
		          .addAttribute(InputPasswordTypeAttributes.AutoComplete, "new-password")
		          .setPlaceholder("Confirm Password")
		          .setPattern("regex.password");
		inputGroup.addMessage(InputErrorValidations.pattern, "Password needs to be at least 6 characters long and have a number.");

		loginForm.add(inputGroup);
		return inputGroup;
	}

	private BSButton buildLoginButton(BSForm<?> loginForm)
	{
		BSButton loginButton = loginForm.createSubmitButton(BSButtonOptions.Btn_Primary, BSButtonSizeOptions.Btn_Block);
		loginButton.setText("Log Me In");
		loginButton.addClass("waves-effect waves-light waves-primary")
		           .addClass(BSSpacingOptions.Margin_Top_3)
		           .addClass(BSSpacingOptions.Margin_Bottom_2);
		loginButton.addStyle("margin-top", "0px !important;");

		loginForm.add(loginButton);
		return loginButton;
	}

	private BSButton buildRegisterButton(BSForm<?> loginForm)
	{
		BSButton loginButton = loginForm.createSubmitButton(BSButtonOptions.Btn_Primary, BSButtonSizeOptions.Btn_Block);
		loginButton.setText("Register");
		loginButton.addClass("waves-effect waves-light waves-primary")
		           .addClass(BSSpacingOptions.Margin_Top_3)
		           .addClass(BSSpacingOptions.Margin_Bottom_2);
		loginButton.addStyle("margin-top", "0px !important;");

		loginForm.add(loginButton);
		return loginButton;
	}

	private BSButton buildForgotPasswordButton(BSForm<?> loginForm)
	{
		BSButton loginButton = loginForm.createSubmitButton(BSButtonOptions.Btn_Primary, BSButtonSizeOptions.Btn_Block);
		loginButton.setText("Forgot Password");
		loginButton.addClass("waves-effect waves-light waves-primary")
		           .addClass(BSSpacingOptions.Margin_Top_3)
		           .addClass(BSSpacingOptions.Margin_Bottom_2);
		loginButton.addStyle("margin-top", "0px !important;");

		loginForm.add(loginButton);
		return loginButton;
	}
}
