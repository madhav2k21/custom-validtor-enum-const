package com.techleads.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techleads.app.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
