package com.msg.receiver.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@RestController
public class sampleController {
	
	//@Resource(name="flog")
	//private LogManager flog;
	private String logFileRootPath ="C:"+systemSeparator+"log"+systemSeparator+"MsgGateWay";
	private static final String systemSeparator = File.separator;
	private static final String logLevel = "DEBUG";
	
	@RequestMapping(value="/putHello.do" , method={RequestMethod.GET ,RequestMethod.POST} , produces= {"application/json"})
	public void HelloWolrd(HttpServletRequest req) {
		
		//flog.logFileRootPath(logFileRootPath);
		//flog.appender("com.finnq.receiver.controller.sampleController", "sampleController", logLevel);
		//flog.debug("PUT HELLO TEST");
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
			Connection connection = null;
			Channel channel = null;
			try {
				connection = (Connection) factory.newConnection();
				channel = ((com.rabbitmq.client.Connection) connection).createChannel();
				channel.queueDeclare("hello", false, false, false, null);
				String message = "Hello World!";
				channel.basicPublish("", "hello", null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(channel != null) {
						try {
							channel.close();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(connection != null)
						connection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	@RequestMapping(value="/getHello.do" , method={RequestMethod.GET ,RequestMethod.POST} , produces= {"application/json"})
	public void HelloWolrdGet(HttpServletRequest req) {
		System.out.println("######### Sample HelloWorld : " + req.getParameterMap().toString());
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
			Connection connection = null;
			try {
				connection = (Connection) factory.newConnection();
				Channel channel = ((com.rabbitmq.client.Connection) connection).createChannel();
				channel.queueDeclare("hello", false, false, false, null);
				String message = "Hello World!";
				channel.basicPublish("", "hello", null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(connection != null)
						connection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public static void main(String argp[]) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setVirtualHost("/test");
		factory.setUsername("user1");
		factory.setPassword("1234qwer");
		factory.setPort(5672);
//		ConnectionFactory factory = new ConnectionFactory();

		
			Connection connection = null;
			Channel channel = null;
			try {
				//factory.setUri("amqp://guest:guest@127.0.0.1:5672/");
				System.out.println(" [x] Sent '");
				connection = (Connection) factory.newConnection();
				System.out.println(" [x] Sent '");
				channel = ((com.rabbitmq.client.Connection) connection).createChannel();
				System.out.println(" [x] Sent '");
				channel.queueDeclare("slack1", false, false, false, null);
				System.out.println(" [x] Sent '");
				String message = "Hello World!";
				System.out.println(" [x] Sent '");
				channel.basicPublish("", "hello", null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(channel != null) {
						try {
							channel.close();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(connection != null)
						connection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
}
