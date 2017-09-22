package com.yc.mq.listener;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.yc.test.entity.Test;
import com.yc.test.mapper.TestMapper;

/**
 * 通过 activeMq 消息队列进行监控 
 * 监听 其他 工程项目传来的 消息 进行 下一步操作
 * 轮训监听 ·
 * @author yuanchen
 *
 */
public class MyMqListener implements MessageListener  {

	@Autowired
	private TestMapper testMapper;
	
	public void onMessage(Message message) {
		
		
		TextMessage textMessage = (TextMessage)message;
		String text;
		try {
			text = textMessage.getText();
			Test t1 = new Test(text,"z",new Double(6.66),new Date());
			testMapper.addTestTbl(t1);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
