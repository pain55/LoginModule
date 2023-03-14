package com.servicemodules.LoginModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicemodules.LoginModule.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
