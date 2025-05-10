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
		
		public ContactDto getContactByid(int id) {
			return dao.findById(id);
		}

		public void updateContact(ContactDto updationContact) {
			dao.update(updationContact);
		}

		public void deleteContact(int id) {
			dao.delete(id);
		}
}
