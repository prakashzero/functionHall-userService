package com.pellipandiri.userservice.repository;

import com.pellipandiri.userservice.entities.Booking;
import com.pellipandiri.userservice.entities.FunctionHalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByFunctionHall(FunctionHalls functionHall);
    
    @Query("SELECT b FROM Booking b WHERE b.functionHall = :functionHall AND b.status = 'CONFIRMED' AND " +
           "((b.startTime <= :startTime AND b.endTime >= :startTime) OR " +
           "(b.startTime <= :endTime AND b.endTime >= :endTime) OR " +
           "(b.startTime >= :startTime AND b.endTime <= :endTime))")
    List<Booking> findConflictingBookings(@Param("functionHall") FunctionHalls functionHall,
                                         @Param("startTime") LocalDateTime startTime,
                                         @Param("endTime") LocalDateTime endTime);
    
    @Query("SELECT b FROM Booking b WHERE b.functionHall = :functionHall AND b.status = 'CONFIRMED' AND b.startTime >= :date")
    List<Booking> findUpcomingBookings(@Param("functionHall") FunctionHalls functionHall,
                                      @Param("date") LocalDateTime date);
} 