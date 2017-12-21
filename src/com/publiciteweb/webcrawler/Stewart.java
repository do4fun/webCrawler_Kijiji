package com.publiciteweb.webcrawler;

public class Stewart
{
	private boolean onSpecificPattern = true;
	private String root, pattern;
	private boolean stayOnSite = true;

	public Stewart()
	{}

	public Stewart( String root, String pattern )
	{
		this.pattern = pattern;
		this.root = root;
	}

	protected boolean basicValidationURL( String href )
	{
		if ( validateNullValue( href.toString() ) )
		{
			return false;
		}

		if ( validateExtension( href.toString() ) )
		{
			return false;
		}

		if ( validateSpecialChar( href.toString() ) )
		{
			return false;
		}

		if ( isStayOnSite() )
		{
			if ( href.contains( root ) )
			{
				return true;
			}else
			{
				return false;
			}
		}

		return true;
	}

	public boolean validateURL( String href )
	{
		if ( basicValidationURL( href ) == false )
		{
			return false;
		}
		return true;
	}

	public boolean validateNullValue( String string )
	{
		return string == null;
	}

	public boolean validateSpecialChar( String string )
	{
		if ( ( string.indexOf( "{" ) >= 0 ) || ( string.indexOf( "}" ) >= 0 ) || ( string.indexOf( "%space%" ) >= 0 ) )
		{
			return true;
		}
		return false;
	}

	public boolean validateAdressParameter( String string )
	{
		if ( string.indexOf( "?" ) >= 0 )
		{
			return true;
		}
		return false;
	}

	public boolean validateExtension( String string )
	{
		if ( string.indexOf( ".css" ) >= 0 )
		{
			return true;
		}
		if ( string.indexOf( ".ico" ) >= 0 )
		{
			return true;
		}
		if ( string.indexOf( ".pdf" ) >= 0 )
		{
			return true;
		}
		if ( string.indexOf( "javascript" ) >= 0 )
		{
			return true;
		}
		if ( string.indexOf( "#" ) >= 0 )
		{
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

	public String toString()
	{
		return root;
	}

}
