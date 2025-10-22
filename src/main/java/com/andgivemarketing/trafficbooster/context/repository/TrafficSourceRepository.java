package com.andgivemarketing.trafficbooster.context.repository;

import com.andgivemarketing.trafficbooster.context.entity.TrafficSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficSourceRepository extends JpaRepository<TrafficSourceEntity, Long>  {



}
