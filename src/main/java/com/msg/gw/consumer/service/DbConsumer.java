package com.msg.gw.consumer.service;

import com.msg.gw.consumer.Interface.IConsumer;
import org.springframework.amqp.core.Message;

public class DbConsumer extends com.msg.gw.consumer.AbConsumer implements IConsumer {

	@Override
	public void sendData(Message dbMessage) {
		// TODO Auto-generated method stub
        log(dbMessage , "DbConsumer");
		sendToDB(dbMessage);
	}

	public void sendToDB(Message Msg){
		System.out.println("DB INSERT");
	}
}
