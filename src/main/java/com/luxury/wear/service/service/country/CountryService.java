package com.luxury.wear.service.service.country;

import java.util.List;

public interface CountryService {

    List<String> getAllCountries();

    List<String> getStatesByCountryName(String countryCode);
}
