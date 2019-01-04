package com.bing.lan.msj;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lan_bing
 * @date 2019-01-04 15:46
 */
@RestController
public class DemoController {

    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot!";
    }
}