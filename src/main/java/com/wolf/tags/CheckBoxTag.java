package com.wolf.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CheckBoxTag extends SimpleTagSupport {

	private String name;
	private String value;
	private String checked;
	private String disable;
	private String labelClassName;
	private String labelHtmlContent;

	public void doTag() throws JspException, IOException {

		String strDisable = "false";
		if(disable!=null && disable.equals("true")) strDisable = "true";
		String strChecked = "false";
		if(checked!=null) strChecked = "true";
		StringBuilder sb = new StringBuilder();
		String strLabelClassName = "";
		if(labelClassName!=null) strLabelClassName = labelClassName;
		String strLabelHtmlContent = "";
		if(labelHtmlContent!=null) strLabelHtmlContent = labelHtmlContent;
		
		
		sb.append("<span>\r\n  <div class=\"icheckbox_minimal-purple\" aria-checked=\"");sb.append(strChecked);sb.append("\" aria-disabled=\"");sb.append(strDisable);sb.append("\" style=\"position: relative;\">\r\n");
		sb.append("    <input type=\"checkbox\" class=\"j-icheck\" name=\"");sb.append(name);sb.append("\"  value=\"");sb.append(value);sb.append("\" style=\"position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);\">\r\n");
		sb.append("    <ins class=\"iCheck-helper\" style=\"position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);\"></ins>\r\n");
		sb.append("  </div>\r\n");
		sb.append("  <label for=\"");sb.append(name);sb.append("\" class=\"ilabel ");sb.append(strLabelClassName);sb.append("\"><span>");sb.append(strLabelHtmlContent);sb.append("</span></label>\r\n</span>");
		this.getJspContext().getOut().write(sb.toString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getLabelClassName() {
		return labelClassName;
	}

	public void setLabelClassName(String labelClassName) {
		this.labelClassName = labelClassName;
	}

	public String getLabelHtmlContent() {
		return labelHtmlContent;
	}

	public void setLabelHtmlContent(String labelHtmlContent) {
		this.labelHtmlContent = labelHtmlContent;
	}
}
