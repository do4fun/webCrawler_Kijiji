package com.publiciteweb.webcrawler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class crawlerLog {

	private static final Logger logger = LogManager.getLogger("crawlerLogger");
	private static final Logger consoleLogger = LogManager.getLogger("consoleLogger");
	private static final Logger exceptionLogger = LogManager.getLogger("exceptionLogger");

	public static void exception(Exception pException) {
		if (consoleLogger.isErrorEnabled()) {
			consoleLogger.error(pException.getClass());
		}
		if (logger.isErrorEnabled()) {
			logger.error(pException);
		}
		if (exceptionLogger.isErrorEnabled()) {
			exceptionLogger.error(pException);
		}
	}

	public static void exception(Exception pException, String pMessage) {
		if (consoleLogger.isErrorEnabled()) {
			consoleLogger.error(pException.getClass() + " - ************ " + pMessage);
		}
		if (logger.isErrorEnabled()) {
			logger.error(pException.getClass() + " - " + pMessage);
		}
		if (exceptionLogger.isErrorEnabled()) {
			exceptionLogger.error(pMessage, pException);
		}
	}

	public static void debug() {
		if (consoleLogger.isDebugEnabled()) {
			consoleLogger.debug(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "() - Start");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "() - Start");
		}
	}

	public static void debug(String pMessage) {
		if (consoleLogger.isDebugEnabled()) {
			consoleLogger.debug(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
					+ " - " + pMessage);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - "
					+ pMessage);
		}
	}

	public static void trace(String pMessage) {
		if (consoleLogger.isTraceEnabled()) {
			consoleLogger.trace(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
					+ " - " + pMessage);
		}

		if (logger.isTraceEnabled()) {
			logger.trace(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - "
					+ pMessage);
		}
	}

	public static void info(String pMessage) {
		info(pMessage, false);
	}

	public static void info(String pMessage, boolean pDisplayCrawlerPagesGUI) {
		if (!pDisplayCrawlerPagesGUI) {
			if (consoleLogger.isInfoEnabled()) {
				consoleLogger.info(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
						+ " - " + pMessage);
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - "
					+ pMessage);
		}
	}

	public static void warn(String pMessage) {
		if (consoleLogger.isWarnEnabled()) {
			consoleLogger.warn(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
					+ " - " + pMessage);
		}
		if (logger.isWarnEnabled()) {
			logger.warn(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - "
					+ pMessage);
		}
	}

	public static void error(String pMessage) {
		if (consoleLogger.isErrorEnabled()) {
			consoleLogger.error(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
					+ " - " + pMessage);
		}
		if (logger.isErrorEnabled()) {
			logger.error(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - "
					+ pMessage);
		}
	}

	public static void fatal(String pMessage) {
		if (consoleLogger.isFatalEnabled()) {
			consoleLogger.fatal(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName()
					+ " - " + pMessage);
		}
		if (logger.isFatalEnabled()) {
			logger.fatal(Thread.currentThread().getStackTrace()[2].getClassName() + " - " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - "
					+ pMessage);
		}
	}
}
