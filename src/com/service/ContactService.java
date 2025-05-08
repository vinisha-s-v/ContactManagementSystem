package com.service;

import java.util.List;

import com.dao.ContactDao;
import com.dao.ContactDaoImp;
import com.dto.ContactDto;

public class ContactService {
	
	ContactDao dao = new ContactDaoImp();
	
		public List<ContactDto> getAllContacts(){
			return dao.findAll();	
		}
		
		public void addContact(ContactDto contact) {
			dao.save(contact);
		}
}
