package com.dannielSouza.progDashboard.models.DTO;

import com.dannielSouza.progDashboard.models.Enums.Role;
import com.dannielSouza.progDashboard.models.Task;

import java.util.List;

public record UserDTO (Long id, String name, String username, Long idCompany, Role role, List<Task> tasksList) {
}
