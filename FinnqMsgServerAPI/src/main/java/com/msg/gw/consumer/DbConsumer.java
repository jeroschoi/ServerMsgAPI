package com.msg.gw.consumer;

import org.springframework.amqp.core.Message;

public class DbConsumer extends abConsumer implements IConsumer {

	@Override
	public void sendData(Message Message) {
		// TODO Auto-generated method stub
		sendToDB(Message);
	}

	public void sendToDB(Message Msg){
		System.out.println("DB INSERT");
	}
}
