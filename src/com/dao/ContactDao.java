package com.dao;

import java.util.List;

import com.dto.ContactDto;

public interface ContactDao {
	List<ContactDto> findAll();
}
