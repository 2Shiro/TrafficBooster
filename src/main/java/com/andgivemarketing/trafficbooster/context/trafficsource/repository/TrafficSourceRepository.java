package com.andgivemarketing.trafficbooster.context.trafficsource.repository;

import com.andgivemarketing.trafficbooster.context.trafficsource.entity.TrafficSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficSourceRepository extends JpaRepository<TrafficSourceEntity, Long>  {



}
