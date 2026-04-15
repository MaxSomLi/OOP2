package com.max.avia2spring.repository;

import com.max.avia2spring.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByCrewMembersName(String currentUsername);
}