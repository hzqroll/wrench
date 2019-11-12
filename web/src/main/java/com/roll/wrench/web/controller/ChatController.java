package com.roll.wrench.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author roll
 * created on 2019-11-12 20:01
 */
@RestController
@RequestMapping(value = "/chat")
public class ChatController {
    @RequestMapping(value = "/homepage")
    public String homepage() {
        return "websocket/websocketclient";
    }
}
