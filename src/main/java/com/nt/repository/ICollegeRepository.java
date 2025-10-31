package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.College;

public interface ICollegeRepository extends JpaRepository<College, Integer> {

}
