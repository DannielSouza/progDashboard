package com.dannielSouza.progDashboard.models.DTO;

import java.util.List;

public record CompanyDTO(Long id, String name, String email,List<UserDTO>usersList) {
}
