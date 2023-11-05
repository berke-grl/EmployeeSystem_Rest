package com.example.employeedb.Repository;

import com.example.employeedb.Constant.DbConnection;
import com.example.employeedb.Entity.Boss;
import com.example.employeedb.Entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BossRepository implements BaseRepository<Boss> {

    @Override
    public List<Boss> findAll() throws SQLException {
        List<Boss> list = new ArrayList<>();

        Connection con = DbConnection.getConnection();
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM \"Boss\"");
        while (result.next()) {
            list.add(new Boss(result.getInt("id"),
                    result.getString("firstName"),
                    result.getString("department"),
                    result.getBoolean("isActive")));
        }
        con.close();
        stm.close();
        result.close();
        return list;
    }


    @Override
    public Boss findById(int id) throws SQLException {
        Boss boss = null;

        Connection con = DbConnection.getConnection();
        String sql = "SELECT * FROM \"Boss\" WHERE id = ?";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();

        while (result.next()) {
            boss = new Boss(result.getInt("id"), result.getString("firstName"), result.getString("Department"), result.getBoolean("isActive"));
        }
        con.close();
        stm.close();
        result.close();
        return boss;
    }

    @Override
    public boolean save(Boss boss) throws SQLException {
        boolean result = false;
        Connection con = DbConnection.getConnection();
        String sql = "INSERT INTO \"Employee\"(\"firstName\", \"department\", \"isActive\") VALUES(?, ?, ?))";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setString(1, boss.getFirstName());
        stm.setString(2, boss.getDepartment());
        stm.setBoolean(3, boss.isActive());

        result = stm.executeUpdate() == 1;
        con.close();
        stm.close();
        return result;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        boolean result = false;
        Connection con = DbConnection.getConnection();
        String sql = "DELETE FROM \"Boss\" WHERE \"id\" = ?";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, id);

        result = stm.executeUpdate() == 1;
        con.close();
        stm.close();

        return result;
    }
}
