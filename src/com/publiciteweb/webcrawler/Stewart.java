package com.publiciteweb.webcrawler;

public class Stewart {
	private boolean onSpecificPattern = true;
	private String root, pattern;
	private boolean stayOnSite = true;
	private boolean acceptAdressParameter = true;
	private String statement;
	private String attribute;
	private String attributeValue;
	private boolean onSpecificCode = true;
	private String name = "";

	public Stewart() {
	}

	public Stewart(String pattern) {
		this.pattern = pattern;
		crawlerLog.debug("pattern : " + pattern);
	}

	protected boolean basicValidationURL(String href) {
		if (validateNullValue(href.toString())) {
			crawlerLog.debug("validateNullValue : false");
			return false;
		}

		if (validateExtension(href.toString())) {
			crawlerLog.debug("validateExtension : false");
			return false;
		}

		if (validateSpecialChar(href.toString())) {
			crawlerLog.debug("validateSpecialChar : false");
			return false;
		}

		if (acceptAdressParameter == false) {
			if (validateAdressParameter(href.toString()) == true) {
				crawlerLog.debug("acceptAdressParameter : false");
				return false;
			}
		}

		if (isStayOnSite()) {
			if (href.contains(root)) {
				return true;
			} else {
				return false;
			}
		}

		return true;
	}


	public boolean validateURL(String href) {
		if (basicValidationURL(href) == false) {
			return false;
		}
		return true;
	}

	public boolean validateNullValue(String string) {
		return string == null;
	}

	public boolean validateSpecialChar(String string) {
		if ((string.indexOf("{") >= 0) || (string.indexOf("}") >= 0) || (string.indexOf("%space%") >= 0)) {
			return true;
		}
		return false;
	}

	public boolean validateAdressParameter(String string) {
		if (string.indexOf("?") >= 0) {
			return true;
		}
		return false;
	}

	public boolean validateExtension(String string) {
		if (string.indexOf(".css") >= 0) {
			return true;
		}
		if (string.indexOf(".ico") >= 0) {
			return true;
		}
		if (string.indexOf(".pdf") >= 0) {
			return true;
		}
		if (string.indexOf("javascript") >= 0) {
			return true;
		}
		if (string.indexOf("#") >= 0) {
			return true;
		}
		return false;
	}

	public boolean isOnSpecificPattern()
	{
		return onSpecificPattern;
	}

	public void setOnSpecificPattern( boolean onSpecificPattern )
	{
		this.onSpecificPattern = onSpecificPattern;
	}

	public String getRoot()
	{
		return root;
	}

	public void setRoot( String root )
	{
		this.root = root;
	}

	public String getPattern()
	{
		return pattern;
	}

	public void setPattern( String pattern )
	{
		this.pattern = pattern;
	}

	public boolean isStayOnSite()
	{
		return stayOnSite;
	}

	public void setStayOnSite( boolean stayOnSite )
	{
		this.stayOnSite = stayOnSite;
	}

	public boolean isAcceptAdressParameter()
	{
		return acceptAdressParameter;
	}

	public void setAcceptAdressParameter( boolean acceptAdressParameter )
	{
		this.acceptAdressParameter = acceptAdressParameter;
	}

	public String getStatement()
	{
		return statement;
	}

	public void setStatement( String statement )
	{
		this.statement = statement;
	}

	public String getAttribute()
	{
		return attribute;
	}

	public void setAttribute( String attribute )
	{
		this.attribute = attribute;
	}

	public String getAttributeValue()
	{
		return attributeValue;
	}

	public void setAttributeValue( String attributeValue )
	{
		this.attributeValue = attributeValue;
	}

	public boolean isOnSpecificCode()
	{
		return onSpecificCode;
	}

	public void setOnSpecificCode( boolean onSpecificCode )
	{
		this.onSpecificCode = onSpecificCode;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String toString() {
		return root;
	}

}
