package com.angular.demo.profile.model;

import java.util.UUID;

public record ProfileUpdateDto(
    UUID id, String firstName, String lastName, String number, ProfileTypeDto type) {}
