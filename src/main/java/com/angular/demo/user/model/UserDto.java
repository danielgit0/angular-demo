package com.angular.demo.user.model;

import java.util.UUID;

public record UserDto(UUID id, String email, String type) {}
