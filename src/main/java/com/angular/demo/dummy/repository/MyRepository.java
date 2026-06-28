package com.angular.demo.dummy.repository;

public interface MyRepository {
  Message saveMessage(Message message);

  Message findMessage(Long id);
}
