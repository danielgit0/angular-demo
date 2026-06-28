package com.angular.demo.dummy.controller;

import com.angular.demo.dummy.service.MessageDto;
import com.angular.demo.dummy.service.MyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("angular")
public class MyControllerImpl implements MyController {

  private final MyService myService;

  public MyControllerImpl(MyService myService) {
    this.myService = myService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Override
  public MessageResponse postMessage(@RequestBody CreateMessageRequest message) {
    MessageDto messageDto = myService.saveMessage(new MessageDto(message.id(), message.message()));
    return new MessageResponse(messageDto.id(), messageDto.message());
  }

  @GetMapping("/{id}")
  @Override
  public MessageResponse getMessage(@PathVariable Long id) {
    MessageDto messageDto = myService.findMessage(id);
    return new MessageResponse(messageDto.id(), messageDto.message());
  }
}
