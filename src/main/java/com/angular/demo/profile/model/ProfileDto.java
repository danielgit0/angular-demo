package com.angular.demo.profile.model;

import java.util.UUID;

public record ProfileDto(
    UUID id, String firstName, String lastName, String number, ProfileTypeDto type, String email) {}
