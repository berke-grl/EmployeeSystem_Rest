package com.example.employeedb.Repository;

import com.example.employeedb.Constant.DbConnection;
import com.example.employeedb.Entity.Boss;
import com.example.employeedb.Entity.Department;
import com.example.employeedb.Entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository implements BaseRepository<Department> {
    @Override
    public List<Department> findAll() throws SQLException {
        List<Department> list = new ArrayList<>();
        Connection con = DbConnection.getConnection();
        String sql = "SELECT \"Department\".\"id\", \"Department\".\"location\", \"Boss\".\"firstName\" AS \"bossName\", \"Employee\".\"firstName\" AS \"employeeName\" FROM \"Department\" " +
                "inner join \"Boss\" ON \"Boss\".id = \"Department\".\"boosId\" " +
                "inner join \"Employee\" ON \"Employee\".id = \"Department\".\"employeeId\"";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet result = stm.executeQuery();

        while (result.next()) {
            Employee emp = new Employee(result.getString("employeeName"));
            Boss boss = new Boss(result.getString("bossName"));

            list.add(new Department(result.getInt("id"), result.getString("location"), emp, boss));
        }

        con.close();
        stm.close();
        result.close();

        return list;
    }

    @Override
    public Department findById(int id) throws SQLException {
        Department department = null;

        Connection con = DbConnection.getConnection();
        String sql = "SELECT \"Department\".\"id\", \"Department\".\"location\", \"Boss\".\"firstName\" AS \"bossName\", \"Employee\".\"firstName\" AS \"employeeName\" FROM \"Department\" " +
                "inner join \"Boss\" ON \"Boss\".id = \"Department\".\"boosId\"\n" +
                "inner join \"Employee\" ON \"Employee\".id = \"Department\".\"employeeId\" " +
                "where \"Department\".\"id\" = ?";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        while (result.next()) {
            Employee emp = new Employee(result.getString("employeeName"));
            Boss boss = new Boss(result.getString("bossName"));

            department = new Department(result.getInt("id"), result.getString("location"), emp, boss);
        }

        con.close();
        stm.close();
        result.close();
        return department;
    }

    @Override
    public boolean save(Department department) throws SQLException {
        boolean result = false;

        Connection con = DbConnection.getConnection();
        String sql = "INSERT INTO public.\"Department\"(location, \"employeeId\", \"boosId\") VALUES (?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setString(1, department.getLocation());
        stm.setInt(2, department.getEmployee().getId());
        stm.setInt(3, department.getBoss().getId());

        result = stm.executeUpdate() == 1;

        con.close();
        stm.close();

        return result;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        boolean result = false;

        Connection con = DbConnection.getConnection();
        String sql = "DELETE FROM public.\"Department\" WHERE  \"id\" = ?";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, id);

        result = stm.executeUpdate() == 1;

        con.close();
        stm.close();

        return result;
    }
}
