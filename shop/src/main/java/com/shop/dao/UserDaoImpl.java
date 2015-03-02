package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

	private Connection conn;
	private static final String UID = "id";
	private static final String ULOGIN = "login";
	private static final String UPASS = "password";
	private static final String UFIRSTNAME = "firstName";
	private static final String ULASTNAME = "lastName";
	private static final String UEMAIL = "email";
	private static final String UACCOUNT = "account";
	private static final String UAGE = "age";
	private static final String UGENDER = "gender";
	private static final String UROLE_ID = "role_id";
	private static final String UADDRESS_ID = "address_id";
	private static final String INSERT_USER = "INSERT INTO shop.users (login, password, firstName, lastName, email, account, age, gender, role_id, address_id) VALUES (?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_USER = "SELECT * FROM shop.users";
	private static final String SELECT_USER_ID = "SELECT * FROM shop.users WHERE id = ?;";
	private static final String UPDATE_USER = "UPDATE shop.users SET login=?, password=?, firstName=?, lastName=?, email=?, account=?, age=?, gender=?, role_id=?, address_id=?  WHERE id=?;";
	private static final String DELETE_USER = "DELETE FROM shop.users WHERE id = ?;";
	private static final String CHECK_LOGIN = "SELECT login FROM shop.users where login=?;";
	private static final String CHECK_LOGIN_PASS = "SELECT login,password FROM shop.users where login=?;";
	private static final String GET_FIRSTNAME = "SELECT firstname FROM shop.users where login=?;";
	private static final String GET_ROLE = "SELECT role_id FROM shop.users where login=?;";

	public UserDaoImpl() {
		this.conn = CurConn.getConnection();
	}

	@Override
	public User createUser(User user) {
		try (PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getAccount());
			ps.setString(7, user.getAge());
			ps.setBoolean(8, user.isGender());
			ps.setInt(9, user.getRole_id());
			ps.setInt(10, user.getAddress_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		Statement statement = null;
		ResultSet rs = null;
		List<User> users = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(SELECT_USER);
			users = new ArrayList<User>();
			System.out.println("users from getAllUsers");
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(UID));
				user.setLogin(rs.getString(ULOGIN));
				user.setPassword(rs.getString(UPASS));
				user.setFirstName(rs.getString(UFIRSTNAME));
				user.setLastName(rs.getString(ULASTNAME));
				user.setEmail(rs.getString(UEMAIL));
				user.setAccount(rs.getInt(UACCOUNT));
				user.setAge(rs.getString(UAGE));
				user.setGender(rs.getBoolean(UGENDER));
				user.setRole_id(rs.getInt(UROLE_ID));
				user.setAddress_id(rs.getInt(UADDRESS_ID));
				users.add(user);
				System.out.println(user);
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return users;
	}

	@Override
	public User getUserById(int id) {
		User user = new User();
		try (PreparedStatement ps = conn.prepareStatement(SELECT_USER_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setId(rs.getInt(UID));
			user.setLogin(rs.getString(ULOGIN));
			user.setPassword(rs.getString(UPASS));
			user.setFirstName(rs.getString(UFIRSTNAME));
			user.setLastName(rs.getString(ULASTNAME));
			user.setEmail(rs.getString(UEMAIL));
			user.setAccount(rs.getInt(UACCOUNT));
			user.setAge(rs.getString(UAGE));
			user.setGender(rs.getBoolean(UGENDER));
			user.setRole_id(rs.getInt(UROLE_ID));
			user.setAddress_id(rs.getInt(UADDRESS_ID));
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return user;
	}

	@Override
	public int updateUser(User user) {
		int result = 0;
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_USER)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getAccount());
			ps.setString(7, user.getAge());
			ps.setBoolean(8, user.isGender());
			ps.setInt(5, user.getRole_id());
			ps.setInt(6, user.getAddress_id());
			ps.setInt(7, user.getId());
			ps.executeUpdate();
			result = user.getId();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public int deleteUser(User user) {
		int result = 0;
		try (PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {
			ps.setInt(1, user.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean result = false;
		try (PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {
			ps.setInt(1, id);
			if (ps.executeUpdate() != 0) {
				result = true;
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public boolean checkLogin(String login) {
		boolean result = false;
		try (PreparedStatement ps = conn.prepareStatement(CHECK_LOGIN)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(ULOGIN) != null) {
					result = true;
				}
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public boolean checkLoginPass(String login, String pass) {
		boolean result = false;
		try (PreparedStatement ps = conn.prepareStatement(CHECK_LOGIN_PASS)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(ULOGIN) != null) {
					if (rs.getString(UPASS).equals(pass)) {
						result = true;
					}
				}
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public String getFirstNameByLogin(String firstname) {
		String fname = "";
		try (PreparedStatement ps = conn.prepareStatement(GET_FIRSTNAME)) {
			ps.setString(1, firstname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(UFIRSTNAME) != null) {
					fname = rs.getString(UFIRSTNAME);
				}
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return fname;
	}

	@Override
	public int getRoleByLogin(String login) {
		int role = 0;
		try (PreparedStatement ps = conn.prepareStatement(GET_ROLE)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(UROLE_ID) > 0) {
					role = rs.getInt(UROLE_ID);
				}
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return role;
	}
}