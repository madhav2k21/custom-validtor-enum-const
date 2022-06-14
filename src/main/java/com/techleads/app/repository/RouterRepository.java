package com.techleads.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techleads.app.model.Route;

public interface RouterRepository extends JpaRepository<Route, Integer> {

}
