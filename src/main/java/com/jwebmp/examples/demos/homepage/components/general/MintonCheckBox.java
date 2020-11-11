package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Label;
import com.jwebmp.core.base.html.attributes.InputTypes;
import com.jwebmp.core.base.html.inputs.InputCheckBoxType;

import jakarta.validation.constraints.NotNull;

public class MintonCheckBox<J extends MintonCheckBox<J>>
		extends DivSimple<J>
{
	private boolean checked;
	private String label;
	private String clazz;
	private InputCheckBoxType checkBox;

	public MintonCheckBox(boolean checked, String label, String clazz)
	{
		this.checked = checked;
		this.label = label;
		this.clazz = clazz;
		checkBox = new InputCheckBoxType();
	}

	public MintonCheckBox(String label, boolean checked, String clazz)
	{
		this.checked = checked;
		this.label = label;
		this.clazz = clazz;
		checkBox = new InputCheckBoxType();
	}

	public MintonCheckBox()
	{
		checkBox = new InputCheckBoxType();
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			addClass(InputTypes.Checkbox.toString());
			checkBox.setChecked(checked);
			Label labelComponent = new Label();
			labelComponent.setForInputComponent(checkBox);
			if (label != null)
			{
				labelComponent.setText(label);
			}
			add(checkBox);
			add(labelComponent);
			addClass(clazz);
		}
		super.preConfigure();
	}

	public boolean isChecked()
	{
		return checked;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setChecked(boolean checked)
	{
		this.checked = checked;
		return (J) this;
	}

	public String getLabel()
	{
		return label;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setLabel(String label)
	{
		this.label = label;
		return (J) this;
	}

	public String getClazz()
	{
		return clazz;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setClazz(String clazz)
	{
		this.clazz = clazz;
		return (J) this;
	}

	public InputCheckBoxType getCheckBox()
	{
		return checkBox;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setCheckBox(InputCheckBoxType checkBox)
	{
		this.checkBox = checkBox;
		return (J) this;
	}
}
