package com.msg.gw.consumer;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import com.msg.gw.config.AppConfiguration;
import com.msg.gw.service.log.logInsert;


public abstract class abConsumer implements IConsumer {

	private logInsert logInsert;
	private String logFileRootPath ="C:"+systemSeparator+"log"+systemSeparator+"FinnqMsgGateWay";
	private static final String systemSeparator = File.separator;
	private static final String logLevel = "DEBUG";
	
	public abConsumer() {
		logInsert = (logInsert)AppConfiguration.getContext().getBean("logInsert");
		// Properties 형태 변경처리 필요
	}
	
	public void logData(Message Message) {
		String ERR_MSG = "";
		try {
			ERR_MSG = new String(Message.getBody(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// logInsert.insertdata(test);
	}
	
	public void logbackUp(Message Message) {
		// DATA DB SAVE
	}

}
