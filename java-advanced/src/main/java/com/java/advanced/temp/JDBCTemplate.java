package com.java.advanced.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.advanced.exception.ErrorLogger;

public class JDBCTemplate {

	public static final String URL = "jdbc:mysql://localhost:3306/test";

	public JDBCTemplate() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, "root", "");
	}

	public <T> List<T> executeQ(String sql, Object[] params, Mapper<T> mapper) {

		List<T> results = new ArrayList<T>();

		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i], getType(params[i]));
				}
			}
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				results.add(mapper.result(resultSet));
			}
		} catch (SQLException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return results;
	}

	public <T> Integer executeUpdateWithKeys(String sql, Object[] params,
			Mapper<T> mapper) {
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i], getType(params[i]));
			}
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return null;
	}

	private int getType(Object o) {
		String type = o.getClass().getName();
		switch (type) {
		case "java.lang.String":
			return java.sql.Types.VARCHAR;
		case "java.lang.Integer":
			return java.sql.Types.INTEGER;
		}
		return -1;
	}

}
