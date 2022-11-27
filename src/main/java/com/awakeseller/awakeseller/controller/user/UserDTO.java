package com.awakeseller.awakeseller.controller.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private UUID id;

    private String name;
}
