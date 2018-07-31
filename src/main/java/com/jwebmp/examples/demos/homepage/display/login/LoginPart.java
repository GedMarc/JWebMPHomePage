package com.jwebmp.examples.demos.homepage.display.login;

import com.jwebmp.core.Page;
import com.jwebmp.core.base.angular.AngularAttributes;
import com.jwebmp.core.base.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.HorizontalRule;
import com.jwebmp.core.base.html.attributes.InputPasswordTypeAttributes;
import com.jwebmp.core.base.html.inputs.InputEmailType;
import com.jwebmp.core.base.html.inputs.InputPasswordType;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.examples.demos.homepage.components.PrettyPrimaryButton;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.MintonCheckBox;
import com.jwebmp.examples.demos.homepage.display.forgotpassword.ForgotPasswordEvent;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonSizeOptions;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.groups.sets.BSFormInputGroup;
import com.jwebmp.plugins.bootstrap4.modal.BSModal;
import com.jwebmp.plugins.bootstrap4.options.BSColoursOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSSpacingOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;

import static com.jwebmp.core.base.html.attributes.InputButtonTypeAttributes.*;

public class LoginPart
		extends SourceCodeContentPanel
{
	public LoginPart()
	{
		super("Login", DisplayCodeParts.Login, null);
		setShowHeader(false);
		setShowCode(true);
		Div openSourcePane = new Div();
		setContent(openSourcePane);

		Div contents = new Div();

		BSRow loginFormRow = new BSRow<>();
		BSForm<?> loginForm = new BSForm<>().setID("loginForm");
		loginForm.asMe()
		         .setStyleInput(true);
		loginForm.addClass(BSColumnOptions.Col_12);
		loginFormRow.add(loginForm);

		loginForm.add(new AlertMessage(null));

		BSFormInputGroup<?, InputEmailType<?>> loginInputGroup = buildLoginInput(loginForm);

		BSFormInputGroup<?, InputPasswordType<?>> inputGroup = buildPasswordInput(loginForm);

		BSRow checkRow = new BSRow();
		MintonCheckBox checkBox;
		checkRow.add(checkBox = (MintonCheckBox) new MintonCheckBox<>("Remember Me Forever", true, "checkbox-purple").addClass(BSColumnOptions.Col_12));
		checkBox.getCheckBox()
		        .bind("subscribe.rememberMe")
		        .addStyle("margin-left", "5px;")
		        .addStyle("margin-top", "15px;");
		loginForm.add(checkRow);

		BSButton submitButton = buildSubmitButton(loginForm);
		submitButton.addEvent(new LoginEvent(submitButton));

		contents.add(loginFormRow);

		contents.add(buildButtonRow(loginForm, loginInputGroup, inputGroup));

		openSourcePane.add(contents);
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
		BSFormInputGroup<?, InputEmailType<?>> loginInputGroup = loginForm.addEmailInput("subscribe.emailAddress", null, true)
		                                                                  .prepend(FontAwesome.icon(FontAwesomeIcons.at));
		loginInputGroup.setID("loginInputGroup")
		               .setStyleInputGroupTextWithValidation(true)
		               .updateOnBlur()
		               .addClass(BSSpacingOptions.Margin_Bottom_3);

		loginInputGroup.getInput()
		               .setID("loginInputID")
		               .setRequired()
		               .addAttribute(AutoComplete, "username")
		               .setPlaceholder("Email Address")
		               .setPattern("regex.emailField");

		loginInputGroup.addMessage(InputErrorValidations.pattern, "Please enter a valid email address");
		return loginInputGroup;
	}

	private BSFormInputGroup<?, InputPasswordType<?>> buildPasswordInput(BSForm<?> loginForm)
	{
		BSFormInputGroup<?, InputPasswordType<?>> inputGroup = loginForm.addPasswordInput("subscribe.password", null, true)
		                                                                .prepend(FontAwesome.icon(FontAwesomeIcons.key));

		inputGroup.getInput()
		          .setMinimumLength(6)
		          .setRequired()
		          .addAttribute(InputPasswordTypeAttributes.AutoComplete, "current-password")
		          .setPlaceholder("Password")
		          .setPattern("regex.password");
		inputGroup.addMessage(InputErrorValidations.pattern, "Password needs to be at least 6 characters long and have a number.");
		return inputGroup;
	}

	private BSButton buildSubmitButton(BSForm<?> loginForm)
	{
		BSButton loginButton = loginForm.addSubmitButton(BSButtonOptions.Btn_Primary, BSButtonSizeOptions.Btn_Block);
		loginButton.setText("Log Me In");
		loginButton.addClass("waves-effect waves-primary")
		           .addClass(BSSpacingOptions.Margin_Top_3)
		           .addClass(BSSpacingOptions.Margin_Bottom_2);
		loginButton.addStyle("margin-top", "0px !important;");
		return loginButton;
	}

	private BSRow buildButtonRow(BSForm<?> loginForm, BSFormInputGroup loginInputGroup, BSFormInputGroup inputGroup)
	{
		BSRow row = new BSRow();
		row.resetHorizontalSinks();
		PrettyPrimaryButton<?> registerButton = new PrettyPrimaryButton<>().setText("Register")
		                                                                   .addClass(BSColoursOptions.Text_White)
		                                                                   .asButton()
		                                                                   .addAttribute(AngularAttributes.ngDisabled, "" +
		                                                                                                               loginForm.getName() +
		                                                                                                               "." +
		                                                                                                               loginInputGroup.getInput()
		                                                                                                                              .getName() +
		                                                                                                               ".$invalid || " +
		                                                                                                               loginForm.getName() +
		                                                                                                               "." +
		                                                                                                               inputGroup.getInput()
		                                                                                                                         .getName() +
		                                                                                                               ".$invalid");
		Page p = GuiceContext.get(Page.class);
		if (p.isMobileOrSmartTablet())
		{
			registerButton.addClass(BSColumnOptions.Col_12);
		}
		else
		{
			registerButton.addClass(BSColumnOptions.Col_5);
		}

		BSModal<?> confirmModal = buildConfirmPasswordModal();
		confirmModal.setBackdrop(false);
		row.add(confirmModal);

		confirmModal.addOpenButton(registerButton);

		row.add(registerButton);

		PrettyPrimaryButton forgotPassButton = new PrettyPrimaryButton<>().setText("Forgot Password")
		                                                                  .addClass(BSColoursOptions.Text_White)
		                                                                  .asButton()
		                                                                  .addAttribute(AngularAttributes.ngDisabled, loginForm.getName() +
		                                                                                                              "." +
		                                                                                                              loginInputGroup.getInput()
		                                                                                                                             .getName() +
		                                                                                                              ".$invalid");

		forgotPassButton.addEvent(new ForgotPasswordEvent(forgotPassButton));
		row.add(forgotPassButton);

		if (p.isMobileOrSmartTablet())
		{
			forgotPassButton.addClass(BSColumnOptions.Col_12);
		}
		else
		{
			forgotPassButton.addClass(BSColumnOptions.Col_6);
		}

		return row;
	}

	private BSModal<?> buildConfirmPasswordModal()
	{
		BSModal<?> modal = new BSModal<>();
		modal.setID("confirmPasswordModal");
		modal.setFade();
		modal.setFocus(true);
		modal.setModalDialogCenter(true);

		modal.addModalHeader(true)
		     .addTitle("Confirm your password");

		BSRow<?> inputRow = new BSRow<>();
		BSForm<?> inputForm = new BSForm<>();
		inputForm.addClass(BSColumnOptions.Col_12);
		BSFormInputGroup<?, InputPasswordType<?>> confirm = inputForm.addPasswordInput("subscribe.confirmPassword", null, false);
		confirm.getInput()
		       .setPattern("regex.password")
		       .addAttribute(InputPasswordTypeAttributes.AutoComplete, "new-password")
		       .setRequired();
		confirm.getInput()
		       .setMinimumLength(6);
		confirm.addMessage(InputErrorValidations.pattern, "Password needs to be at least 6 characters long, have a number and an uppercase character");
		inputForm.setStyleInput(true);
		BSButton submitButton = buildSubmitButton(inputForm);

		modal.asMe()
		     .createDismissButton(submitButton);

		submitButton.setText("Register");
		submitButton.addEvent(new RegisterEvent(submitButton));

		inputRow.add(inputForm);

		modal.getModalContent()
		     .add(inputRow);

		return modal;

	}
}
