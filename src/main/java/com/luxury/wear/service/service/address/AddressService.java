package com.luxury.wear.service.service.address;

import com.luxury.wear.service.dto.address.AddressResponseDto;
import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.entity.Address;
import com.luxury.wear.service.entity.User;

import java.util.List;

public interface AddressService {

    Address createAddress(ReservationRequestDto reservationRequestDto, User user);

    void updateAddress(Long addressId, ReservationRequestDto reservationRequestDto);

    List<AddressResponseDto> getUserAddressesByEmail(String email);
}
