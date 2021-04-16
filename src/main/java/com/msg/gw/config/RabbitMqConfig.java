package com.msg.gw.config;

import com.msg.gw.consumer.ConsumerFactory;
import com.msg.gw.consumer.Interface.IConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class RabbitMqConfig {

    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
    private IConsumer consumer;

    public void sendData(String ConsumerType , Message sendMsg) throws Exception{
        this.consumer = new ConsumerFactory().getInstance(ConsumerType);
        if(this.consumer != null)
            this.consumer.sendData(sendMsg);
    }

    public ConnectionFactory rqConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean
    public SimpleMessageListenerContainer msgListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rqConnectionFactory());
        container.setQueueNames("TEST");
        container.setMessageListener(executeMessage());
        return container;
    }

    public MessageListener executeMessage(){
        return new MessageListener() {
            @Override
            public void onMessage(Message message) {
                MessageProperties mp = message.getMessageProperties();
                String consumerType = mp.getHeader("consumerType");
                if(consumerType != null){
                    try {
                        log.debug("Check The Message : " + consumerType + " : " + new String(message.getBody(), "UTF-8"));
                        sendData(consumerType , message);
                    }catch (Exception e){

                    }
                }
            }
        };
    }

}
