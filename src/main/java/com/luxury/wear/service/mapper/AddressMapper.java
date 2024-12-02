package com.luxury.wear.service.mapper;

import com.luxury.wear.service.dto.address.AddressRequestDto;
import com.luxury.wear.service.dto.address.AddressResponseDto;
import com.luxury.wear.service.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponseDto toResponseDto(Address address) {
        if (address == null) {
            return null;
        }

        return new AddressResponseDto(
                address.getId(),
                address.getCountry(),
                address.getProvince(),
                address.getCity(),
                address.getAddress(),
                address.getDetail(),
                address.getPostalCode()
        );
    }

    public Address toEntity(AddressRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        return Address.builder()
                .country(requestDto.getCountry())
                .province(requestDto.getProvince())
                .city(requestDto.getCity())
                .address(requestDto.getAddress())
                .detail(requestDto.getDetail())
                .postalCode(requestDto.getPostalCode())
                .build();
    }
}
