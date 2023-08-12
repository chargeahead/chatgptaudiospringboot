package com.example.chatgptspringboot.controller;

import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class MyController {

    @Value("${openai.api.key}")
    private String apiKey;

    @GetMapping("/audio")
    public String audio(@RequestParam String filePath){
        OpenAiService service = new OpenAiService(apiKey);

        CreateTranscriptionRequest request = new CreateTranscriptionRequest();
        request.setModel("whisper-1");
        File file = new File(filePath);
        String transcription = service.createTranscription(request,filePath).getText();
        return transcription;
    }
}
