package com.telerik.ridepalplaylistgenerator.repository;

import com.telerik.ridepalplaylistgenerator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUsername(String username);

    User getUserByUserId(int id);

    @Query("SELECT u from User u where u.username = ?1")
    List<User> getByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    List<User> getByEmail(String email);
}
