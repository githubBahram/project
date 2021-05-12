package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ApplicationRepository<DAOUser> {
    DAOUser findByUsername(String username);
}
