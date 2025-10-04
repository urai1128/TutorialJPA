package com.techacademy;

import java.util.List;
import java.util.Optional; //これを追加した

import org.springframework.transaction.annotation.Transactional; //ここを変更
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    // 全件を検索して返す
    public List<Country> getCountryList() {
        // リポジトリのfindAllメソッドを呼び出す
        return repository.findAll();
    }
    // ここから追加した
    //一見を検索して返す
    public Country getCountry(String code) {
        //fndByIdで検索
        Optional<Country> option = repository.findById(code);

        Country country = option.orElse(null);
        return country;
    }

    @Transactional
    public void updateCountry(String code, String name, int population) {
        Country country = new Country(code, name, population);
        repository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        repository.deleteById(code);
    }

}