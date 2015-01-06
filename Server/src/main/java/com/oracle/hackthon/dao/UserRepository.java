package com.oracle.hackthon.dao;

import com.oracle.hackthon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
