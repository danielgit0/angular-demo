package com.angular.demo.dummy.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MyRepositoryImpl implements MyRepository {

  private final Map<Long, Message> messages = new HashMap<>();

  @Override
  public Message saveMessage(Message message) {

    messages.put(message.id(), message);

    return message;
  }

  @Override
  public Message findMessage(Long id) {
    if (messages.containsKey(id)) {
      return messages.get(id);
    }
    return null;
  }
}
