package com.msg.gw.consumer.Interface;

import org.springframework.amqp.core.Message;

public interface IConsumer {

	public void sendData(Message Message) throws Exception;
}
