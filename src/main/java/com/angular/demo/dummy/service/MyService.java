package com.angular.demo.dummy.service;

public interface MyService {
  MessageDto saveMessage(MessageDto messageDto);

  MessageDto findMessage(Long id);
}
