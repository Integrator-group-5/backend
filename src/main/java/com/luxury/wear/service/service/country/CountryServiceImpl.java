package com.luxury.wear.service.service.country;

import com.luxury.wear.service.entity.Country;
import com.luxury.wear.service.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public List<String> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(Country::getName)
                .toList();
    }

    @Override
    public List<String> getStatesByCountryName(String countryName) {
        return countryRepository.findByName(countryName)
                .map(Country::getDepartments)
                .orElseThrow(() -> new IllegalArgumentException("Country not found with name: " + countryName));
    }
}
