package com.angular.demo.user.model.mappers;

import com.angular.demo.user.model.UserDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "attributes", target = "type", qualifiedByName = "type")
  UserDto toDto(UserRepresentation entity);

  List<UserDto> toDto(List<UserRepresentation> entity);

  @Named("type")
  default String type(Map<String, List<String>> attributes) {
    if (attributes == null) {
      return null;
    }
    return attributes.getOrDefault("userProfileUserType", null).getFirst();
  }

  default Map<UUID, UserDto> toDtoMap(List<UserDto> dtos) {
    return dtos.stream()
        .collect(Collectors.toMap(UserDto::id, user -> user, (a, b) -> a, HashMap::new));
  }
}
