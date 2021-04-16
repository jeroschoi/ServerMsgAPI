
package com.msg.gw.consumer;

import com.msg.gw.config.AppConfig;
import com.msg.gw.consumer.Interface.IConsumer;
import com.msg.gw.consumer.service.SlackConsumer;

public class ConsumerFactory {

	public static IConsumer getInstance(String ConsumerType) {
		return (IConsumer)AppConfig.getContext().getBean(ConsumerType);
	}
}
