package com.naukri.updateProfile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4jExample {
	public static final Logger log = LogManager.getLogger(log4jExample.class.getName());
	public static void main(String[] args) {
		log.debug("Debug message logged");
		log.error("Error message logged");
		log.fatal("fatal message logged");
		
	}

}
