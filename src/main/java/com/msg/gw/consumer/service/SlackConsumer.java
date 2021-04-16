package com.msg.gw.consumer.service;

import java.io.UnsupportedEncodingException;

import com.msg.gw.consumer.Interface.IConsumer;
import org.springframework.amqp.core.Message;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.stereotype.Service;

@Service
public class SlackConsumer extends com.msg.gw.consumer.AbConsumer implements IConsumer {

	// slack WebHook URL
	// Properties
	private final String slackUrl = "https://hooks.slack.com/services/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";

	public SlackConsumer() {
		super();
	}
	
	@Override
	public void sendData(Message SlackMessage) throws Exception{
		// TODO Auto-generated method stub
		log(SlackMessage , "SlackConsumer");
		SendToSlack(SlackMessage);
	}
	
	public void SendToSlack(Message SlackMessage) throws Exception {
		String SendMessage = makeSendMsgForSlack(SlackMessage);
		try {
			SendMessage = new String(SlackMessage.getBody(),"UTF-8");
			SlackApi api = new SlackApi(slackUrl);
			api.call(new SlackMessage("#coding", "Coding Alert", SendMessage));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String makeSendMsgForSlack(Message SlackMessage) {
		return "";
	}
}
