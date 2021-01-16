package com.blz.example.services;

import java.util.List;
import com.blz.example.dto.AddressBookDTO;
import com.blz.example.model.AddressBookData;

public interface IAddressBookService {

	List<AddressBookData> getAddressBookData();

	AddressBookData getAddressBookDataById(int personId);

	AddressBookData createAddressBookData(AddressBookDTO addressBookDTO);

	AddressBookData updateAddressBookData(int personId, AddressBookDTO addressBookDTO);

	void deleteAddressBookData(int personId);
}
