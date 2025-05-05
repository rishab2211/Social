package com.social.repository;

import com.social.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByEmail(String user);

    @Query("SELECT u FROM UserModel u WHERE u.name LIKE %:query% OR u.email LIKE %:query%")
    List<UserModel> searchUser(@Param("query") String query);
}
