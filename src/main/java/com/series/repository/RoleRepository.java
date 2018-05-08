package com.series.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.series.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
