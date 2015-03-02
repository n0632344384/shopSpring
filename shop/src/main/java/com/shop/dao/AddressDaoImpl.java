package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

	private Connection conn;
	private static final String AID = "id";
	private static final String AZIP = "zip";
	private static final String ASTATE = "state";
	private static final String ACITY = "city";
	private static final String ASTREET = "street";
	private static final String APHONE = "phone";
	private static final String INSERT_ADDRESS = "INSERT INTO shop.addresses (zip, state, city, street, phone) VALUES (?,?,?,?,?);";
	private static final String SELECT_ADDRESS = "SELECT * FROM shop.addresses";
	private static final String SELECT_ADDRESS_ID = "SELECT * FROM shop.addresses WHERE id = ?;";
	private static final String UPDATE_ADDRESS = "UPDATE shop.addresses SET zip=?, state=?, city=?, street=?, phone=?  WHERE id=?;";
	private static final String DELETE_ADDRESS = "DELETE FROM shop.addresses WHERE id = ?;";

	public AddressDaoImpl() {
		this.conn = CurConn.getConnection();
	}

	public AddressDaoImpl(boolean autoCommit) {
		this.conn = CurConn.getConnection(autoCommit);
	}

	@Override
	public Address createAddress(Address address) {
		try (PreparedStatement ps = conn.prepareStatement(INSERT_ADDRESS)) {
			ps.setString(1, address.getZip());
			ps.setString(2, address.getState());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getStreet());
			ps.setString(5, address.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return address;
	}

	@Override
	public List<Address> getAllAddresses() {
		Statement statement = null;
		ResultSet rs = null;
		List<Address> addresses = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(SELECT_ADDRESS);
			addresses = new ArrayList<Address>();
			while (rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt(AID));
				address.setZip(rs.getString(AZIP));
				address.setState(rs.getString(ASTATE));
				address.setCity(rs.getString(ACITY));
				address.setStreet(rs.getString(ASTREET));
				address.setPhone(rs.getString(APHONE));
				addresses.add(address);
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return addresses;
	}

	@Override
	public Address getAddressById(int id) {
		Address address = new Address();
		try (PreparedStatement ps = conn.prepareStatement(SELECT_ADDRESS_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			address.setId(rs.getInt(AID));
			address.setZip(rs.getString(AZIP));
			address.setState(rs.getString(ASTATE));
			address.setCity(rs.getString(ACITY));
			address.setStreet(rs.getString(ASTREET));
			address.setPhone(rs.getString(APHONE));
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return address;
	}

	@Override
	public int updateAddress(Address address) {
		int result = 0;
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_ADDRESS)) {
			ps.setString(1, address.getZip());
			ps.setString(2, address.getState());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getStreet());
			ps.setString(5, address.getPhone());
			ps.setInt(6, address.getId());
			ps.executeUpdate();
			result = address.getId();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public int deleteAddress(Address address) {
		int result = 0;
		try (PreparedStatement ps = conn.prepareStatement(DELETE_ADDRESS)) {
			ps.setInt(1, address.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public int createAddressGetIdCreated(Address address) {
		int newAdrId = 0;
		try (PreparedStatement ps = conn.prepareStatement(INSERT_ADDRESS,
				Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, address.getZip());
			ps.setString(2, address.getState());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getStreet());
			ps.setString(5, address.getPhone());
			ps.executeUpdate();

			ResultSet keyResultSet = ps.getGeneratedKeys();
			while (keyResultSet.next()) {
				System.out.println("Generated key: " + keyResultSet.getInt(1));
				newAdrId = (int) keyResultSet.getInt(1);
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return newAdrId;
	}
}