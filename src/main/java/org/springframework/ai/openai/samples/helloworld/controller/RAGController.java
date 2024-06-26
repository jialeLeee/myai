package org.springframework.ai.openai.samples.helloworld.controller;

import org.springframework.ai.openai.samples.helloworld.service.extern.RAGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: movie
 * @date: 2024/6/13 14:32
 */
@Controller
public class RAGController {
    @Autowired
    private RAGService ragService;

    @GetMapping("/rag")
    public String index(@RequestParam(name = "question", defaultValue = "") String question, Model model) {
        if (question.isEmpty()) {
            return "rag";
        }
        model.addAttribute("question", question);
        model.addAttribute("response", ragService.askLLM(question));

        return "rag";
    }

    @GetMapping("/index")
    public String uploadForm() {
        return "index";
    }

    @GetMapping("sse/index")
    public String sseIndex() {
        return "sse/index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            ragService.textEmbedding(file);
        }
        return "redirect:/rag";
    }
}
