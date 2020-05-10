package com.sportapp.demo.repository;

import com.sportapp.demo.entity.OpinionEntity;
import com.sportapp.demo.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OpinionRepository extends PagingAndSortingRepository<OpinionEntity,Long> {

    UserEntity findByUserId(String userId);

}
