package com.ams.likepost.Service;

import com.ams.likepost.DAO.Country;
import com.ams.likepost.Repository.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    public CountryRepo countryRepo;

    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    public void upsertCountry(Country countryEntity){
        countryRepo.save(countryEntity);
    }

    public Country findById(int id) {

        Optional<Country> optional = countryRepo.findById(id);
        Country country = null;
        if (optional.isPresent()) {
            country = optional.get();
        } else {
            throw new RuntimeException(" Country not found for id :: " + id);
        }
        return country;
    }

    public void deleteCountry(int id) {
        countryRepo.deleteById(id);
    }

}
