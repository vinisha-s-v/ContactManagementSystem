package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

		String sql = "SELECT * "
				+ "FROM contactsschema.contacts";
		try (
			Connection conn = DBConnectionUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);)
			{
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

	@Override
	public void save(ContactDto contact) {
		
		String sql = "INSERT INTO contactsschema.contacts(firstname, lastname, phonenumber) VALUES (?,?,?)";
		try (
			Connection conn = DBConnectionUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);)
			{
				stmt.setString(1, contact.getFirstName());
	            stmt.setString(2, contact.getLastName());
	            stmt.setString(3, contact.getPhoneNumber());
	            stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public ContactDto findById(int id) {
		
		String sql = "SELECT * FROM contactsschema.contacts WHERE id=?";
		try (
				Connection conn = DBConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
			)
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new ContactDto(
						rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("phonenumber")
						);
						
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
