package com.max.avia2spring.repository;

import com.max.avia2spring.model.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {


    Optional<CrewMember> findByName(String currentUserName);

    CrewMember save(CrewMember crewMember);

}