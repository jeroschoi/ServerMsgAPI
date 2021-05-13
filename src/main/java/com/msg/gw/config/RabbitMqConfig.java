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
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.ErrorHandler;


@Configuration
@DependsOn(value = {"appConfig"})
public class RabbitMqConfig {

    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    public void sendData(String ConsumerType , Message sendMsg) throws Exception{
        IConsumer consumer = new ConsumerFactory().getInstance(ConsumerType);
        if(consumer != null)
            consumer.sendData(sendMsg);
    }

    public ConnectionFactory rqConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory((String)AppConfig.getConfig().get("MQ.URL"));
        connectionFactory.setUsername((String)AppConfig.getConfig().get("MQ.USER"));
        connectionFactory.setPassword((String)AppConfig.getConfig().get("MQ.PASS"));
        connectionFactory.setVirtualHost((String)AppConfig.getConfig().get("MQ.VIR.HOST"));
        return connectionFactory;
    }

    @Bean
    public SimpleMessageListenerContainer msgListenerContainer(){
        log.debug(" CHECK THE Spring!!! ");
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rqConnectionFactory());
        container.setQueueNames((String)AppConfig.getConfig().get("MQ.QUEUE"));
        container.setMessageListener(executeMessage());
        // Listener Thread 처리
        //container.setConcurrentConsumers(10);
        container.setErrorHandler(errorProcess());
        return container;
    }

    @Bean
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

    // Error Handler 추가
    public ErrorHandler errorProcess(){
        return new ConditionalRejectingErrorHandler(new FatalExceptionStrategy() {
            @Override
            public boolean isFatal(Throwable throwE) {
                // error 처리 추가
                if(throwE instanceof ListenerExecutionFailedException){
                    ListenerExecutionFailedException lefE = (ListenerExecutionFailedException) throwE;
                    Message msg = lefE.getFailedMessage();
                    // msg 처리로직
                    try {
                        log.debug("Filed msg : - " + new String(msg.getBody(), "UTF-8"));
                        // 로그 DB 저장 시 DB Consumer 사용 or Slack 전송
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                // false 일 경우 모든 Thread에 전달
                return true;
            }
        });
    }

}
