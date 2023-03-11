package com.dannielSouza.progDashboard.models.DTO;

import com.dannielSouza.progDashboard.models.Enums.Role;

import java.util.List;

public record CompanyDTO(Long id, String name, String email,Role role, List<UserDTO>usersList) {
}
