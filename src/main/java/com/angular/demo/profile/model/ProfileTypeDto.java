package com.angular.demo.profile.model;

public enum ProfileTypeDto {
  EMPLOYER("employer"),
  EMPLOYEE("employee");

  private final String value;

  ProfileTypeDto(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static ProfileTypeDto fromValue(String value) {
    return switch (value) {
      case "employer", "EMPLOYER" -> EMPLOYER;
      case "employee", "EMPLOYEE" -> EMPLOYEE;
      default -> throw new IllegalArgumentException("Unknown ProfileType value: " + value);
    };
  }
}
