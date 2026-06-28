package com.angular.demo.company.model;

import java.util.UUID;

public record CompanyCreateDto(UUID profileId, String name, String address) {}
