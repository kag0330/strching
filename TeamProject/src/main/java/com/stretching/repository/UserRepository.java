package com.stretching.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stretching.dto.UserDto;
import com.stretching.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
