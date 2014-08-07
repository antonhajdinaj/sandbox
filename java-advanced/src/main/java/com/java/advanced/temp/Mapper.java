package com.java.advanced.temp;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
	public T result(ResultSet rs) throws SQLException;
}