package com.jwebmp.examples.demos.homepage.display.subscribe;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.HorizontalRule;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonDanger;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.bootstrap4.forms.groups.BSFormGroup;

public class SubscribeScreen
		extends Div
{
	public SubscribeScreen()
	{
		setID("content");
		addStyle("overflow-y:auto;overflow-x:hidden;");
		addStyle("overflow-x:hidden !important;");

		addClass("col-md-10 offset-md-1");

		add("<h1 class=\"card-heading \">Subscribe Today</h1>");
		add("Quick and Easy! Jump right into the action");
		add(buildForm());
	}

	public BSForm buildForm()
	{
		BSForm form = new BSForm();
		//form.addClass("col-offset-md-1 col-md-8");
		form.addStyle("border-color: transparent;border-width: 2px;border-style: solid;display: block;border-radius: 5px;padding: 25px;" + "padding-top:0px;");

		BSRow nameRow = new BSRow();

		BSFormGroup group = new BSFormGroup();
		group.addClass("col-md-6");
		//group.setLabel(new BSFormLabel("First Name : "));
		/*
		group.setInputComponent((BSFormTextInput) new BSFormTextInput().bind("subscribe.firstName")
		                                                               .setMinimumLength(3)
		                                                               .setRequired()
		                                                               .setPlaceholder("First Name"));
		BSFormTextInput.class.cast(group.getInputComponent())
		                     .setPattern("regex.textField");
		group.setMinLengthMessage("Needs at least 3 characters");
		group.setPatternMessage("Please only use english characters for your First Name");
		group.setAngularValidation(true);
		//group.setAsRow(true);
		group.setShowControlFeedback(true);

		nameRow.add(group);


		BSFormGroup lastNameGroup = new BSFormGroup<>().addClass("col-md-6");
		//lastNameGroup.setLabel(new BSFormLabel("Last Name : "));
		lastNameGroup.setInputComponent((BSFormTextInput) new BSFormTextInput().bind("subscribe.lastName"));
		BSFormTextInput.class.cast(lastNameGroup.getInputComponent())
		                     .setPattern("regex.textField")
		                     .setMinimumLength(3)
		                     .setRequired()
		                     .setPlaceholder("Last Name");
		lastNameGroup.setMinLengthMessage("Needs at least 3 characters");
		lastNameGroup.setPatternMessage("Please only use english characters for your Last Name");
		lastNameGroup.setAngularValidation(true);
		//lastNameGroup.setAsRow(true);
		lastNameGroup.setShowControlFeedback(true);
		nameRow.add(lastNameGroup);

		form.add(nameRow);

		BSRow detailsRow = new BSRow();

		BSFormGroup contactDetailsInput = new BSFormGroup<>().addClass("col-md-6");
		//idNumberInput.setLabel(new BSFormLabel("ID Number : "));
		contactDetailsInput.setInputComponent((BSFormTextInput) new BSFormTextInput().bind("subscribe.contactDetails")
		                                                                             .setRequired()
		                                                                             .setPlaceholder("Contact Details"));
		contactDetailsInput.setAngularValidation(true);
		//idNumberInput.setAsRow(true);
		contactDetailsInput.setShowControlFeedback(true);
		//		Input.class.cast(contactDetailsInput).removeAttribute("required");
		//idNumberInput.setHelpText(new BSFormHelpText("This is so we know that the content you upload is legit <3"));
		BSFormTextInput.class.cast(contactDetailsInput.getInputComponent())
		                     .setPattern("regex.saPhoneNumber");
		detailsRow.add(contactDetailsInput);

		form.add(detailsRow);

		BSRow passwordRow = new BSRow();

		BSFormGroup passwordInput = new BSFormGroup<>().addClass("col-md-6");
		//passwordInput.setLabel(new BSFormLabel("Password : "));
		passwordInput.setInputComponent((BSFormPasswordInput) new BSFormPasswordInput().bind("subscribe.password")
		                                                                               .setMinimumLength(4)
		                                                                               .setRequired()
		                                                                               .setPlaceholder("Password"));
		passwordInput.setMinLengthMessage("Needs at least 4 characters");
		passwordInput.setAngularValidation(true);
		//passwordInput.setAsRow(true);
		passwordInput.setShowControlFeedback(true);
		passwordRow.add(passwordInput);

		BSFormGroup confirmPasswordInput = new BSFormGroup<>().addClass("col-md-6");
		//confirmPasswordInput.setLabel(new BSFormLabel<>("Confirm Password : "));
		confirmPasswordInput.setInputComponent((BSFormPasswordInput) new BSFormPasswordInput().bind("subscribe.confirmPassword")
		                                                                                      .setMinimumLength(4)
		                                                                                      .setRequired()
		                                                                                      .setPlaceholder("Confirm Password"));
		confirmPasswordInput.setMinLengthMessage("Needs at least 4 characters");
		confirmPasswordInput.setAngularValidation(true);
		//confirmPasswordInput.setAsRow(true);
		confirmPasswordInput.setShowControlFeedback(true);
		passwordRow.add(confirmPasswordInput);
		form.add(passwordRow);


		BSRow personalRow = new BSRow();

		BSFormGroup emailInput = new BSFormGroup<>().addClass("col-md-6");
		//emailInput.setLabel(new BSFormLabel("Email Address : "));
		emailInput.setInputComponent((BSFormEmailInput) new BSFormEmailInput().bind("subscribe.emailAddress")
		                                                                      .setRequired()
		                                                                      .setPlaceholder("Email Address"));
		emailInput.setPatternMessage("Please enter a valid email address");
		emailInput.setAngularValidation(true);
		//emailInput.setAsRow(true);
		emailInput.setShowControlFeedback(true);

		personalRow.add(emailInput);

		BSFormGroup postalCode = new BSFormGroup<>().addClass("col-md-6");
		//emailInput.setLabel(new BSFormLabel("Email Address : "));
		postalCode.setInputComponent(new BSFormNumberInput<>().bind("subscribe.residentialPostalCode")
		                                                      .setRequired()
		                                                      .setPlaceholder("Residential Postal Code"));
		postalCode.setPatternMessage("Please enter a valid email address");
		postalCode.setAngularValidation(true);
		//emailInput.setAsRow(true);
		postalCode.setShowControlFeedback(true);
		personalRow.add(postalCode);

		form.add(personalRow);
*/

		BSButtonDanger submitButton = new BSButtonDanger();
		submitButton.setText("<i class=\"fa fa-thumbs-o-up\"></i>&nbsp;I'm Ready");

		SubscribeEvent sfe = new SubscribeEvent(submitButton);
		submitButton.addEvent(sfe);
		sfe.returnVariable("subscribe");

		form.add(HorizontalRule.getInstance());
		//	form.createSubmitButton(submitButton);
		form.add(submitButton);

		return form;
	}

}
