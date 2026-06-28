package com.angular.demo.profile.repository.entities;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProfileType {
  EMPLOYER("employer"),
  EMPLOYEE("employee");

  private final String value;

  private static final Map<String, ProfileType> BY_VALUE =
      Stream.of(values()).collect(Collectors.toMap(v -> v.value.toLowerCase(), v -> v));

  ProfileType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
