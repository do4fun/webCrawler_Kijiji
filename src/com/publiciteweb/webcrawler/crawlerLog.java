package com.publiciteweb.webcrawler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrawlerLog
{

	private static final Logger logger = LogManager.getLogger( "crawlerLogger" );
	private static final Logger consoleLogger = LogManager.getLogger( "consoleLogger" );
	private static final Logger exceptionLogger = LogManager.getLogger( "exceptionLogger" );

	public static void exception( Exception exception )
	{
		if ( consoleLogger.isErrorEnabled() )
		{
			consoleLogger.error( exception.getClass() );
		}
		if ( logger.isErrorEnabled() )
		{
			logger.error( exception );
		}
		if ( exceptionLogger.isErrorEnabled() )
		{
			exceptionLogger.error( exception );
		}
	}

	public static void exception( Exception exception, String message )
	{
		if ( consoleLogger.isErrorEnabled() )
		{
			consoleLogger.error( exception.getClass() + " - ************ " + message );
		}
		if ( logger.isErrorEnabled() )
		{
			logger.error( exception.getClass() + " - " + message );
		}
		if ( exceptionLogger.isErrorEnabled() )
		{
			exceptionLogger.error( message, exception );
		}
	}

	public static void debug()
	{
		if ( consoleLogger.isDebugEnabled() )
		{
			consoleLogger.debug( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + "() - Start" );
		}
		if ( logger.isDebugEnabled() )
		{
			logger.debug( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + "() - Start" );
		}
	}

	public static void debug( String message )
	{
		if ( consoleLogger.isDebugEnabled() )
		{
			consoleLogger.debug( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
		if ( logger.isDebugEnabled() )
		{
			logger.debug( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
	}

	public static void trace( String message )
	{
		if ( consoleLogger.isTraceEnabled() )
		{
			consoleLogger.trace( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}

		if ( logger.isTraceEnabled() )
		{
			logger.trace( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
	}

	public static void info( String message )
	{
		info( message, false );
	}

	public static void info( String message, boolean displayCrawlerPagesGUI )
	{
		if ( !displayCrawlerPagesGUI )
		{
			if ( consoleLogger.isInfoEnabled() )
			{
				consoleLogger.info( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
			}
		}
		if ( logger.isInfoEnabled() )
		{
			logger.info( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
	}

	public static void warn( String message )
	{
		if ( consoleLogger.isWarnEnabled() )
		{
			consoleLogger.warn( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
		if ( logger.isWarnEnabled() )
		{
			logger.warn( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
	}

	public static void error( String message )
	{
		if ( consoleLogger.isErrorEnabled() )
		{
			consoleLogger.error( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
		if ( logger.isErrorEnabled() )
		{
			logger.error( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
	}

	public static void fatal( String message )
	{
		if ( consoleLogger.isFatalEnabled() )
		{
			consoleLogger.fatal( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
		if ( logger.isFatalEnabled() )
		{
			logger.fatal( Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + message );
		}
	}
}
