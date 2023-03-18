package com.dannielSouza.progDashboard.repositories;

import com.dannielSouza.progDashboard.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findAllByIdUser(Long id);

    Optional<List<Task>> findAllByIdCompany(Long id);
}
