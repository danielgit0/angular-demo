package com.angular.demo.company.model;

import java.util.UUID;

public record CompanyUpdateDto(UUID profileId, UUID id, String name, String address) {}
