package com.imooc.controller;

import com.imooc.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HiController {
    @Autowired
    private HiService hiService;

    public void handleRequest() {
        hiService.sayHi();
        hiService.justWantToSayHi();
    }
}
