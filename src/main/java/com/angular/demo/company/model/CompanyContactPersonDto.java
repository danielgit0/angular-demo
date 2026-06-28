package com.angular.demo.company.model;

import java.util.UUID;

public record CompanyContactPersonDto(UUID id, String name, String number, String email) {}
