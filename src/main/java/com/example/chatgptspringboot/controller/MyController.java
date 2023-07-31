package com.example.chatgptspringboot.controller;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {
  @Value("${openai.model}")
    private String model;
  @Value("${openai.api.key}")
    private String apiKey;

  @GetMapping("/chat")
    public String chat(@RequestParam String prompt){
      OpenAiService service = new OpenAiService(apiKey);
      List<ChatMessage> messages = new ArrayList<ChatMessage>();
      ChatMessage message = new ChatMessage("user",prompt);
      messages.add(message);
      ChatCompletionRequest completionRequest = ChatCompletionRequest.
              builder()
              .messages(messages)
              .model(model)
              .temperature(0.6)
              .n(1)
              .build();
      List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
      String returnString = "";
      for (ChatCompletionChoice choice:choices){
          returnString += "response: "+choice.getMessage().getContent()+System.lineSeparator();
      }
      return returnString;

  }
}
