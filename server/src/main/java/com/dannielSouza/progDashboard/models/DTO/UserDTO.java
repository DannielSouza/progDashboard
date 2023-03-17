package com.dannielSouza.progDashboard.models.DTO;

import com.dannielSouza.progDashboard.models.Enums.Role;
import com.dannielSouza.progDashboard.models.Task;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO (Long id,
                       String image,
                       String name,
                       String username,
                       String office,
                       Long idCompany,
                       Role role,
                       LocalDateTime lastLogin,
                       List<Task> tasksList)
{}
