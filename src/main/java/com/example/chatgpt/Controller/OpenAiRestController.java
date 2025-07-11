package com.example.chatgpt.Controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    @PostMapping(path = "/image", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ChatResponse explainImage(@RequestParam(defaultValue = "이 이미지는 무엇인가요?", name = "prompt") String prompt,
                                                   @RequestPart(required = true, name = "image" ) MultipartFile image) {
        return openAiService.explainTheImage(prompt, image.getResource());
    }
}