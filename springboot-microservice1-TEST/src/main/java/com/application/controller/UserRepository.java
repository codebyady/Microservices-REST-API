package com.application.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<com.application.controller.User, Long> {
    // You can define custom query methods here if needed
}
