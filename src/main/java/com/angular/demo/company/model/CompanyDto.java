package com.angular.demo.company.model;

import java.util.UUID;

public record CompanyDto(
    UUID id, String name, String address, CompanyContactPersonDto contactPerson) {}
