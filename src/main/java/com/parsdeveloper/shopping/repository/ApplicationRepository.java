package com.parsdeveloper.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
interface ApplicationRepository<T> extends CrudRepository<T, Long> {
}