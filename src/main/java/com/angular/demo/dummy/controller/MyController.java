package com.angular.demo.dummy.controller;

public interface MyController {
  MessageResponse postMessage(CreateMessageRequest message);

  MessageResponse getMessage(Long id);
}
