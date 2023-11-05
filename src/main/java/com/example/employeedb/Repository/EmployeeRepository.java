package com.example.employeedb.Repository;

import com.example.employeedb.Constant.DbConnection;
import com.example.employeedb.Entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements BaseRepository<Employee> {

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> list = new ArrayList<>();

        Connection con = DbConnection.getConnection();
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM \"Employee\"");
        while (result.next()) {
            list.add(new Employee(result.getInt("id"),
                    result.getString("firstName"),
                    result.getInt("identityNumber")));
        }
        con.close();
        stm.close();
        result.close();
        return list;
    }

    @Override
    public Employee findById(int id) throws SQLException {
        Employee employee = null;

        Connection con = DbConnection.getConnection();
        String sql = "SELECT * FROM \"Employee\" WHERE id = ?";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();

        while (result.next()) {
            employee = new Employee(result.getInt("id"), result.getString("firstName"), result.getInt("identityNumber"));
        }
        con.close();
        stm.close();
        result.close();
        return employee;
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
        boolean result = false;
        Connection con = DbConnection.getConnection();
        String sql = "INSERT INTO \"Employee\"(\"firstName\", \"identityNumber\") VALUES(?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setString(1, employee.getFirstName());
        stm.setInt(2, employee.getIdentityNumber());

        result = stm.executeUpdate() == 1;
        con.close();
        stm.close();
        return result;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        boolean result = false;
        Connection con = DbConnection.getConnection();
        String sql = "DELETE FROM \"Employee\" WHERE \"id\" = ?";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, id);

        result = stm.executeUpdate() == 1;
        con.close();
        stm.close();

        return result;
    }
}
