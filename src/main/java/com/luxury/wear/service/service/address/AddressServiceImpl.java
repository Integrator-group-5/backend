package com.luxury.wear.service.service.address;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.entity.Address;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address createAddress(ReservationRequestDto requestDto, User user) {
        Address address = Address.builder()
                .country(requestDto.getCountry())
                .province(requestDto.getProvince())
                .city(requestDto.getCity())
                .address(requestDto.getAddress())
                .detail(requestDto.getDetail())
                .postalCode(requestDto.getPostalCode())
                .user(user)
                .build();

        return addressRepository.save(address);
    }

    @Override
    public void updateAddress(Long addressId, ReservationRequestDto requestDto) {

        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));

        existingAddress.setCountry(requestDto.getCountry());
        existingAddress.setProvince(requestDto.getProvince());
        existingAddress.setCity(requestDto.getCity());
        existingAddress.setAddress(requestDto.getAddress());
        existingAddress.setDetail(requestDto.getDetail());
        existingAddress.setPostalCode(requestDto.getPostalCode());

        addressRepository.save(existingAddress);
    }
}
