package com.spring.memory_strategy.controller;

import com.spring.memory_strategy.service.OpenAiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class OpenAiController {

    private final OpenAiService openAiService;

    @GetMapping("/in-memory-chat")
    public Flux<String> getData(@RequestParam String question,@RequestParam String userId) {
        return openAiService.getResponse(question,userId);
    }



}
