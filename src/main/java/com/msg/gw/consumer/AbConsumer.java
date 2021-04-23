package com.msg.gw.consumer;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.msg.gw.consumer.Interface.IConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import com.msg.gw.config.AppConfig;
import com.msg.gw.common.logInsert;


public abstract class AbConsumer implements IConsumer {

	private logInsert logInsert;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String logLevel = "DEBUG";
	
	public AbConsumer() {
	    // logging ...
		logInsert = (logInsert) AppConfig.getContext().getBean("logInsert");
	}
	
	public void log(String logData , String ServiceName) {
        MDC.put("serviceId" , ServiceName);
        if(log.isDebugEnabled()){
            log.debug(logData);
        }
	}
	
	public void logbackUp(Message Message) {
		// DATA DB SAVE
	}

}
