package com.angular.demo.user.service;

import com.angular.demo.user.model.UserDto;
import com.angular.demo.user.model.mappers.UserMapper;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

  private final Keycloak keycloak;
  private final UserMapper userMapper;

  @Value("${keycloak.admin.realm}")
  String realm;

  public UsersServiceImpl(Keycloak keycloak, UserMapper userMapper) {
    this.keycloak = keycloak;
    this.userMapper = userMapper;
  }

  @Override
  public UserDto getUser(UUID id) {
    final UserRepresentation user = realm().users().get(id.toString()).toRepresentation();
    return userMapper.toDto(user);
  }

  @Override
  public List<UserDto> getUsers(List<UUID> ids) {
    return ids.stream().map(this::getUser).toList();
  }

  @Override
  public Map<UUID, UserDto> getUsersMap(List<UUID> ids) {
    return userMapper.toDtoMap(getUsers(ids));
  }

  @Override
  public void deleteUser(UUID id) {
    Response delete = realm().users().delete(id.toString());
    delete.close();
  }

  private RealmResource realm() {
    return keycloak.realm(realm);
  }
}
