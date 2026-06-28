package com.angular.demo.user.service;

import com.angular.demo.user.model.UserDto;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UsersService {

  UserDto getUser(UUID id);

  List<UserDto> getUsers(List<UUID> ids);

  Map<UUID, UserDto> getUsersMap(List<UUID> ids);

  void deleteUser(UUID id);
}
