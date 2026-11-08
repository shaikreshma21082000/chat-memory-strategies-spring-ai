package com.spring.memory_strategy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    @Qualifier("chatClient")
    private final ChatClient chatClient;

    @Qualifier("jdbcChatClient")
    private final ChatClient jdbcChatClient;

    public Flux<String> getResponse(String question,String userId) {

        return chatClient.prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                .user(question).stream().content();
    }

    public Flux<String> getJdbcResponse(String question, String userId) {

        return jdbcChatClient.prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                .user(question).stream().content();
    }

}