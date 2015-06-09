package com.zl.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.client.chat.ChatService;

@Controller
@RequestMapping("/chat")
public class ChattControl {

    private static final Logger log = LoggerFactory.getLogger(ChattControl.class);

    @Autowired
    private ChatService         chatService;

    @RequestMapping("/send")
    public void sendMessage(HttpServletRequest request, HttpServletResponse response) {

    }

    @RequestMapping("/read")
    public void readMessage(HttpServletRequest request, HttpServletResponse response) {

    }
}
