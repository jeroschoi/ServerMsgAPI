package com.finnq.msg;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

public class finnqAmpqMessage extends Message{
	
	private MessageProperties msgprop;
	private byte[] msgBody;
	private String pushSlackFilter;
	
	public finnqAmpqMessage(byte[] body, MessageProperties messageProperties) {
		super(body, messageProperties);
		// TODO Auto-generated constructor stub
		this.msgprop = messageProperties;
		this.msgBody = body; 
	}

	public void setTrxCd(String trxCd) {
		msgprop.getHeaders().put("TRX_CD", trxCd);
	}
	
	public void setErrCd(String errCd) {
		msgprop.getHeaders().put("ERR_CD", errCd);
	}
}
