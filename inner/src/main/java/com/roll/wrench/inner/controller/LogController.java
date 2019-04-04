package com.roll.wrench.inner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongqiang.hao
 * created on 2019-04-01 08:57.
 */
@RestController
public class LogController {
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping(value = "/log")
    public String log(@RequestParam(name = "info") String info) {
        logger.info("log, " + info);
        return "success";
    }
}
