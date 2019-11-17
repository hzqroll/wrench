package com.roll.wrench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author roll
 * created on 2019-11-12 20:01
 */
@Controller
@RequestMapping(value = "/chat")
public class ChatController {

    @RequestMapping(value = "/websocketclient")
    public String homepage() {
        return "websocket/websocketclient";
    }

    @RequestMapping(value = "/room")
    public String chat() {
        return "chatroom/chat";
    }
}
