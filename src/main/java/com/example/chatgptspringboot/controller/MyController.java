package com.example.chatgptspringboot.controller;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Value("${openai.api.key}")
    private String apiKey;

    @GetMapping("/image")
    public String image(@RequestParam String prompt){
        OpenAiService service = new OpenAiService(apiKey);
        CreateImageRequest req = new CreateImageRequest();
        req.setPrompt(prompt);
        req.setSize("1024x1024");
        Image image = service.createImage(req).getData().get(0);
        return image.getUrl();
    }
}
