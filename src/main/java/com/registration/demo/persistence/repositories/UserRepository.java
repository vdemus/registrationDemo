package com.registration.demo.persistence.repositories;

import com.registration.demo.datamodel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
