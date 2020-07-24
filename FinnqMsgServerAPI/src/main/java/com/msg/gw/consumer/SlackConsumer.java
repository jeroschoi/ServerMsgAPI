package com.msg.gw.consumer;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;

import com.msg.gw.service.log.logInsert;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

public class SlackConsumer extends abConsumer implements IConsumer {

	// slack WebHook URL
	// Properties
	private final String slackUrl = "https://hooks.slack.com/services/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";

	public SlackConsumer() {
		super();
	}
	
	@Override
	public void sendData(Message SlackMessage) throws Exception{
		// TODO Auto-generated method stub
		logData(SlackMessage);
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
