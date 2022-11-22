package com.pitechplus.selfstudy.model.dao.repository;

import com.pitechplus.selfstudy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}