package org.springframework.ai.openai.samples.helloworld.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.samples.helloworld.service.extern.RAGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: movie
 * @date: 2024/6/13 14:32
 */
@RestController
public class OpenAIRestController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private RAGService ragService;

    @GetMapping("/chat-without-rag")
    public String chat(String question) {
        return chatClient.prompt().user(question).call().content();
    }

    @GetMapping("/chat-with-rag")
    public String chat2(String context, String question) {
        return chatClient.prompt().system(context)
                .user(question)
                .call()
                .content();
    }

    @GetMapping("/your-rag")
    public String chatRAG(String question) {
        return ragService.askLLM(question);
    }

}
