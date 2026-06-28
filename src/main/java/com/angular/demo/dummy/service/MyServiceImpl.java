package com.angular.demo.dummy.service;

import com.angular.demo.dummy.error.ResourceNotFoundException;
import com.angular.demo.dummy.repository.Message;
import com.angular.demo.dummy.repository.MyRepository;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

  private final MyRepository myRepository;

  public MyServiceImpl(MyRepository myRepository) {
    this.myRepository = myRepository;
  }

  @Override
  public MessageDto saveMessage(MessageDto messageDto) {
    Message newMessage =
        myRepository.saveMessage(new Message(messageDto.id(), messageDto.message()));
    return new MessageDto(newMessage.id(), newMessage.message());
  }

  @Override
  public MessageDto findMessage(Long id) {
    Message message = myRepository.findMessage(id);
    if (message == null) {
      throw new ResourceNotFoundException(id);
    }

    return new MessageDto(message.id(), message.message());
  }
}
