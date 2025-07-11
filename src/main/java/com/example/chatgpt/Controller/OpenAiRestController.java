package com.example.chatgpt.Controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatgpt.Service.OpenAiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class OpenAiRestController {
    private final OpenAiService openAiService;

    @PostMapping("/query")
    public @ResponseBody ChatResponse query(@RequestParam(defaultValue = "인기있는 여행지는", name = "query") String prompt) {
        return openAiService.answerQuery(prompt);
    }
}
