package com.example.theaterbooking.repository;

import com.example.theaterbooking.model.Theater;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

    List<Theater> findByIsActiveTrue();

    @Query("""
   select distinct t from Theater t
   left join fetch t.sections s
   left join fetch s.rows r
   left join fetch r.seats
   where t.id = :id
   """)
    Theater fetchFullLayout(Long id);

}
