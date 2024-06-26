package org.springframework.ai.openai.samples.helloworld;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
	public Flux<String> completion1(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
		return chatClient.prompt()
				.user(message)
				.stream()
				.content();
	}
}
