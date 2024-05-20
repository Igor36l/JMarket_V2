package com.bigproject.manager.security;

import com.bigproject.manager.entity.CeoUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CeoUserRepository extends CrudRepository<CeoUser, Integer> {

    Optional<CeoUser> findByUsername(String username);
}
