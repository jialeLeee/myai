package org.springframework.ai.openai.samples.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: movie
 * @date: 2024/6/11 17:48
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public void test() {
        log.info("Rest controller method has been called {}", Thread.currentThread());
    }

}
