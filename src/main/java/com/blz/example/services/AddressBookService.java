package com.blz.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blz.example.dto.AddressBookDTO;
import com.blz.example.exceptions.AddressBookException;
import com.blz.example.model.AddressBookData;
import com.blz.example.repository.AddressBookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressBookService implements IAddressBookService {

	@Autowired
	private AddressBookRepository addressBookRepository;

	@Override
	public List<AddressBookData> getAddressBookData() {
		return addressBookRepository.findAll();
	}

	@Override
	public AddressBookData getAddressBookDataById(int personId) {
		return addressBookRepository.findById(personId).orElseThrow(
				() -> new AddressBookException("AddressBook with address id : " + personId + " does not exists "));
	}

	@Override
	public AddressBookData createAddressBookData(AddressBookDTO addressBookDTO) {
		AddressBookData addressBookData = null;
		addressBookData = new AddressBookData(addressBookDTO);
		log.debug("AddressBook data : " + addressBookData.toString());
		return addressBookRepository.save(addressBookData);
	}

	@Override
	public AddressBookData updateAddressBookData(int personId, AddressBookDTO addressBookDTO) {
		AddressBookData addressBookData = this.getAddressBookDataById(personId);
		addressBookData.updateAddressBookData(addressBookDTO);
		return addressBookRepository.save(addressBookData);
	}

	@Override
	public void deleteAddressBookData(int personId) {
		AddressBookData addressBookDataById = this.getAddressBookDataById(personId);
		addressBookRepository.delete(addressBookDataById);
	}
}
