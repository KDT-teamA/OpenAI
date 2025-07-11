package com.example.chatgpt.Service;

import java.util.List;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class OpenAiService {
    
    @Autowired
    OpenAiChatModel openAiChatModel;
    @Autowired
    OpenAiApi openAiApi;

    // 일반 응답
    public ChatResponse answerQuery(String prompt) {
        ChatResponse response = openAiChatModel.call(new Prompt(prompt));
        return response;
    }

    // 이미지 설명
    public ChatResponse explainTheImage (String prompt, Resource img) {
        var userMessage = new UserMessage(prompt, List.of(new Media(
            MimeTypeUtils.IMAGE_PNG, img
        )));

        return openAiChatModel.call(new Prompt(List.of(userMessage)));
    }
}