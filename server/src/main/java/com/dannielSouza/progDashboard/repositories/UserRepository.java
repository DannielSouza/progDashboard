package com.dannielSouza.progDashboard.repositories;

import com.dannielSouza.progDashboard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String email);

    Optional<List<User>> findAllByIdCompany(Long idCompany);

}
