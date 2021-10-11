package com.mba.commons.controlers;

import org.apache.log4j.Logger;

/**
@author Shenilton
@version 2.1
*/

public class Log4jModel {
	 
    private static Logger logger = Logger.getLogger(Log4jModel.class);
 
    public static void main(String[] args) {
        logger.debug("Log4jExample: A Sample Debug Message");
        logger.info("Log4jExample: A Sample Info  Message");
        logger.warn("Log4jExample: A Sample Warn  Message");
        logger.error("Log4jExample: A Sample Error Message");
        logger.fatal("Log4jExample: A Sample Fatal Message");       
    }
}
