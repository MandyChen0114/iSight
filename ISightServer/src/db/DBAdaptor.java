package db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;

import model.Record;
import model.User;

/**
 * @author Zuowei
 *
 */
public class DBAdaptor {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/iSightServer";

	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	static Connection connection;

	static {
		try {
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User getUserInfo(int id) {
		User user = new User();

		String sql = "SELECT * FROM UserTable WHERE Id = " + id + ";";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				user.setId(id);
				user.setUserName(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setAge(rs.getInt("Age"));
				user.setPhoneNum(rs.getString("PhoneNum"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			user.setId(0);
		}
		return user;
	}

	public static boolean createNewUser(User user) {
		String sql = "insert into UserTable (Username, Email, Password, Age, PhoneNum)" + " values (?, ?, ?, ?, ?);";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.getAge());
			preparedStatement.setString(5, user.getPhoneNum());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		sql = "SELECT * FROM UserTable ORDER BY Id DESC limit 1";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				user.setId(rs.getInt("Id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean updateUserInfo(User user) {
		String sql = "update UserTable SET Username = ?, Email = ?, Password = ?, Age = ?, PhoneNum = ?" + " WHERE Id = ?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.getAge());
			preparedStatement.setString(5, user.getPhoneNum());
			preparedStatement.setInt(4, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static ArrayList<Record> getAllRecordsByUserId(int userId) {
		ArrayList<Record> records = new ArrayList<>();

		String sql = "SELECT * FROM RecordTable WHERE UserId = " + userId + ";";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Record record = new Record();
				record.setId(rs.getInt("Id"));
				record.setUserId(rs.getInt("UserId"));
				record.setTestId(rs.getInt("TestId"));
				record.setTimestamp(rs.getString("Timestamp"));
				record.setResult(rs.getString("Result"));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return records;
	}

	public static boolean createNewRecord(Record record) {
		String sql = "insert into RecordTable (UserId, TestId, Timestamp, Result)" + "values (?, ?, ?, ?);";
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, record.getUserId());
			preparedStatement.setInt(2, record.getTestId());
			preparedStatement.setString(3, record.getTimestamp());
			preparedStatement.setString(4, record.getResult());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteRecord(int userId) {
		String sql = "DELETE FROM RecordTable WHERE UserId = ? ;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
