package com.msg.gw.consumer;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.msg.gw.consumer.Interface.IConsumer;
import org.springframework.amqp.core.Message;
import com.msg.gw.config.AppConfig;
import com.msg.gw.common.logInsert;


public abstract class AbConsumer implements IConsumer {

	private logInsert logInsert;
	private String logFileRootPath ="C:"+systemSeparator+"log"+systemSeparator+"FinnqMsgGateWay";
	private static final String systemSeparator = File.separator;
	private static final String logLevel = "DEBUG";
	
	public AbConsumer() {
		logInsert = (logInsert) AppConfig.getContext().getBean("logInsert");
		// Properties 형태 변경처리 필요
	}
	
	public void log(Message Message , String ServiceName) {
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
