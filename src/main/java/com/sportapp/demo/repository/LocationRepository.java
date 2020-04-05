package com.sportapp.demo.repository;

import com.sportapp.demo.entity.LocationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<LocationEntity,Long> {
}
