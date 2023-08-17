package com.techlead.javaspring.dto;

import com.techlead.javaspring.entity.Role;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private List<Role> roles;
}
