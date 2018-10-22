package com.cychat.chat.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.cychat.chat.model.ChatInMessage;
import com.cychat.chat.model.ChatOutMessage;

@Controller
public class ChatController {

	@MessageMapping("/guestchat")  // here
    @SendTo("/topic/guestchats")  // here
    public ChatOutMessage handleMessaging(ChatInMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        
        return new ChatOutMessage(HtmlUtils.htmlEscape(message.getMessage()));
    }
    
    @MessageMapping("/guestupdate")
    @SendTo("/topic/guestupdates")
    public ChatOutMessage handleUserTyping(ChatInMessage message) throws Exception {
        return new ChatOutMessage("Someone is typing...");
    }
    
    @MessageMapping("/guestjoin")
    @SendTo("/topic/guestnames")
    public ChatOutMessage handleMemberJoins(ChatInMessage message) throws Exception {
        return new ChatOutMessage(message.getMessage());
    }
    
	@MessageExceptionHandler
	@SendTo("/topic/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
}
