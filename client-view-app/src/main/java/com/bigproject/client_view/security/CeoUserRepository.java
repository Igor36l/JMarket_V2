package com.bigproject.client_view.security;

import com.bigproject.client_view.entity.CeoUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CeoUserRepository extends CrudRepository<CeoUser, Integer> {

    Optional<CeoUser> findByUsername(String username);
}
