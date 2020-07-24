package com.msg.gw.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.ErrorHandler;

@Configuration
public class AppConfiguration implements ApplicationContextAware {
	
		/**
		 * AMQP Config var
		 */
		private static String hostUrl 	= "";
		private static String userName 	= "";
		private static String passWord 	= "";
		private static int reConnectionCount = 5;
		private static int makingCount = 0;
		
		/**
		 * Spring App Context var
		 */
	    private static ApplicationContext context;
	    
	    
	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	        context = applicationContext;
	        String[] beanName = context.getBeanDefinitionNames();
	        for(String bean:beanName) {
	        	System.out.println(bean);
	        }
	    }
	    public static ApplicationContext getContext() {
	        return context;
	    }
		
		@Bean
		public ConnectionFactory connectionFactoryToSlack() {
			// property �ʿ�
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1");
		    connectionFactory.setUsername("guest");
		    connectionFactory.setPassword("guest");
		    connectionFactory.setVirtualHost("/slack");
			return connectionFactory;
		}
		
		@Bean
		public ErrorHandler testErrorHandler() {
			System.out.println("Errror Handlere Test!!!!!!!!!!");
			test();
			return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
		}
		
		public void test() {
			System.out.println("Errror Handlere Test!!!!!!!!!!");
		}
		
		public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy{
			@Override
			public boolean isFatal(Throwable t) {
				if (t instanceof ListenerExecutionFailedException) {
					ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
				}
				return super.isFatal(t);
			}

		}
}
