package org.springframework.ai.openai.samples.helloworld;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.samples.helloworld.model.OpenaiStreamResult;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
class SimpleAiController {

    private final ChatClient chatClient;

    SimpleAiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai/simple")
    public Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", chatClient.prompt().user(message).call().content());
    }

    @GetMapping(value = "/ai/simple/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<OpenaiStreamResult>> completion1(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content()
                .flatMap(s -> {
                    System.out.println(s); // 输出内容
                    // 在这里可以执行其他的逻辑操作
                    return Mono.just(ServerSentEvent.<OpenaiStreamResult>builder()
                            .data(OpenaiStreamResult.builder()
                                    .result(s)
                                    .build()
                            )
                            .build());
                })
                .doOnComplete(() -> {
                    System.out.println("Completed"); // 输出完成标志
                    // 在这里可以返回结果或执行其他的逻辑操作
                })
                .onErrorResume(e -> Flux.empty());
//        return null;

    }
}
