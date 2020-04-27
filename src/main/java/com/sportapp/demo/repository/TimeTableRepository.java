package com.sportapp.demo.repository;

import com.sportapp.demo.entity.MainTypSportEntity;
import com.sportapp.demo.entity.TimeTableEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository  extends PagingAndSortingRepository<TimeTableEntity,Long> {
}
