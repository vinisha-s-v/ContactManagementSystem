package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.ContactDto;
import com.util.DBConnectionUtil;

public class ContactDaoImp implements ContactDao{

	@Override
	public List<ContactDto> findAll() {
		
		List<ContactDto> listContact = new ArrayList<>();
		
		String sql = "SELECT * FROM contactsschema.contacts";
		try {
			Connection conn = DBConnectionUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				listContact.add(new ContactDto(
						rs.getInt("id"),
						rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("phonenumber")
				));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listContact;
	}

}
