package com.blz.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blz.example.model.AddressBookData;

public interface AddressBookRepository extends JpaRepository<AddressBookData,Integer> {

}
