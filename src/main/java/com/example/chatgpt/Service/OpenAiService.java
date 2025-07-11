package com.example.chatgpt.Service;

import java.util.List;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletion;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenAiService {
    
    private final OpenAiChatModel openAiChatModel;
    private final OpenAiApi openAiApi;

    // 챗봇 응답
    public ResponseEntity<ChatCompletion> chatResponse(String query) {

        ChatCompletionMessage systemMsg = new OpenAiApi.ChatCompletionMessage("안녕하세요, 챗봇입니다.", ChatCompletionMessage.Role.SYSTEM);
        ChatCompletionMessage userMsg1 = new OpenAiApi.ChatCompletionMessage("안녕 봇", ChatCompletionMessage.Role.USER);
        ChatCompletionMessage assistantMsg1 = new OpenAiApi.ChatCompletionMessage("환영합니다.", ChatCompletionMessage.Role.SYSTEM);
        ChatCompletionMessage userMsg2 = new OpenAiApi.ChatCompletionMessage(query, ChatCompletionMessage.Role.USER);

        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest(List.of(systemMsg, userMsg1, assistantMsg1, userMsg2),
                                                                                OpenAiApi.ChatModel.GPT_4_O.getValue(),
                                                                                0.8,
                                                                                false);
        return openAiApi.chatCompletionEntity(chatCompletionRequest);
    }

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