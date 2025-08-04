package com.pellipandiri.userservice.mapper;

import com.pellipandiri.userservice.dto.BookingDTO;
import com.pellipandiri.userservice.dto.OwnerDTO;
import com.pellipandiri.userservice.entities.Booking;
import com.pellipandiri.userservice.entities.Owner;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCustomerEmail(booking.getCustomerEmail());
        dto.setCustomerName(booking.getCustomerName());
        dto.setCustomerPhone(booking.getCustomerPhone());
        dto.setEndTime(booking.getEndTime());
        dto.setEventType(booking.getEventType());
        dto.setStartTime(booking.getStartTime());
        dto.setGuestCount(booking.getGuestCount());
        dto.setFunctionHallId(booking.getFunctionHall().getId());
        dto.setStatus(booking.getStatus().toString());
        dto.setTotalAmount(booking.getTotalAmount());

        return dto;
    }
}
