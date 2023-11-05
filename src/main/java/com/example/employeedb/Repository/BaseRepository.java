package com.example.employeedb.Repository;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    boolean save(T t) throws SQLException;
    boolean deleteById(int id) throws SQLException;
}
