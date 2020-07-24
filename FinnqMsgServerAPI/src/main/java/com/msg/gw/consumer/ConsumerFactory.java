package com.msg.gw.consumer;

public class ConsumerFactory {

	public IConsumer getInstance(String ConsumerType) {
		if("slack".equals(ConsumerType)) {
			return new SlackConsumer();
		}
		return null;
	}
}
