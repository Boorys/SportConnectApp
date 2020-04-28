package com.sportapp.demo.repository;

import com.sportapp.demo.entity.LocationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<LocationEntity, Long> {

    @Query(value = " select street,street_number,city,main_name_of_sport,date,first_name,last_name  from main_typ_sport_entity mtse " +
            " join user_main_typ_sport umts on mtse.id = umts.main_typ_sport_id " +
            " join user_entity ue on ue.id = umts.user_id " +
            " inner join main_typ_sport_location mtsl on mtsl.main_typ_sport_id =mtse.id " +
            " inner join location_entity le on le.id= mtsl.location_id " +
            " inner join time_table_entity tte on tte.location_id=le.id " +
            " where( sqrt(SQUARE(lang- :paramLang)+SQUARE(lat- :paramLat)) < :distance) "
            , nativeQuery = true)
    List<Object[]> findLocationToSelecteddistance(@Param("paramLang") String paramLang, @Param("paramLat") String paramLat, @Param("distance") String distance);

}

