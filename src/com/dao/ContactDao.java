package com.dao;

import java.util.List;

import com.dto.ContactDto;

public interface ContactDao {
	
	List<ContactDto> findAll();
	
	void save(ContactDto contact);
	
	ContactDto findById(int id);
}
