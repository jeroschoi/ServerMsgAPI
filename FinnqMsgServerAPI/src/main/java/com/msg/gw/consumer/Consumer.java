package com.msg.gw.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements MessageListener{

	private IConsumer consumer;
	
	public void sendData(String ConsumerType , Message sendMsg) throws Exception{
		this.consumer = new ConsumerFactory().getInstance(ConsumerType);
		if(this.consumer != null)
			this.consumer.sendData(sendMsg);
	}
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
		MessageProperties mp = message.getMessageProperties();
		String consumerType = mp.getHeader("consumerType");
		if(consumerType != null)
		{
			try {
				sendData(consumerType , message);
				throw new Exception();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
