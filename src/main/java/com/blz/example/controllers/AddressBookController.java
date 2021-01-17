package com.blz.example.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blz.example.dto.AddressBookDTO;
import com.blz.example.dto.ResponseDTO;
import com.blz.example.model.AddressBookData;
import com.blz.example.services.IAddressBookService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*",allowedHeaders = "*",)
@RestController
@RequestMapping("/addressBook")
@Slf4j
public class AddressBookController {

	@Autowired
	private IAddressBookService addressBookService;

	@RequestMapping(value = { "", "/", "get" })
	public ResponseEntity<ResponseDTO> getAddressBookData() {
		System.out.println("Get All Data");
		List<AddressBookData> addressBookData = null;
		addressBookData = addressBookService.getAddressBookData();
		ResponseDTO responseDTO = new ResponseDTO("Get Call Success !!!", addressBookData);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@GetMapping("/get/{personId}")
	public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable("personId") int personId) {
		System.out.println("Get Data By Id");
		AddressBookData addressBookData = null;
		addressBookData = addressBookService.getAddressBookDataById(personId);
		ResponseDTO responseDTO = new ResponseDTO("Get Call for id Successful:", addressBookData);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> addAddressBookData(@Valid @RequestBody AddressBookDTO addressBookDTO) {
		System.out.println("Add Data");
		log.debug("AddressBook Dto " + addressBookDTO.toString());
		AddressBookData addressBookData = null;
		addressBookData = addressBookService.createAddressBookData(addressBookDTO);
		ResponseDTO responseDTO = new ResponseDTO("Created Address Book Data Successfully:", addressBookData);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@PutMapping("/update/{personId}")
	public ResponseEntity<ResponseDTO> updateAddressBookData(@PathVariable("personId") int personId,
			@Valid @RequestBody AddressBookDTO addressBookDTO) {
		System.out.println("Update Data");
		AddressBookData addressBookData = null;
		addressBookData = addressBookService.updateAddressBookData(personId, addressBookDTO);
		ResponseDTO responseDTO = new ResponseDTO("Updated  Address Book Data Successfully:", addressBookData);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{personId}")
	public ResponseEntity<ResponseDTO> deleteAddressBookData(@PathVariable("personId") int personId) {
		System.out.println("Delete Data");
		addressBookService.deleteAddressBookData(personId);
		ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + personId);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
}
