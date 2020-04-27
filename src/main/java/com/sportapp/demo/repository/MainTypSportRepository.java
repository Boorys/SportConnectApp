package com.sportapp.demo.repository;

import com.sportapp.demo.entity.MainTypSportEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainTypSportRepository extends PagingAndSortingRepository<MainTypSportEntity,Long> {

    MainTypSportEntity findByMainTypSportEntityId(String mainTypSportEntityId);
    List<MainTypSportEntity> findAll();
}
