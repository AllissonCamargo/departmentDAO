package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private final Connection connection;
    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO department "
            + "(Name) "
            + "VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, obj.getName());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void uptdate(Department obj) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(
                    "UPDATE department "
            + "SET Name = ? "
            + "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
            ps.setInt(1, id);
            int rowsAffect = ps.executeUpdate();
            if (rowsAffect == 0) {
                throw new DbException("Department no found");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM department "
            + "WHERE department.Id = ?");

            ps.setInt(1, id);
            rs =  ps.executeQuery();

            if (rs.next()) {
                Department obj = new Department();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                return obj;
            }
            System.out.println("Not found!");
            return null;

        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM department");
            rs = ps.executeQuery();

            while (rs.next()) {
                Department tempDep = new Department(rs.getInt("Id"), rs.getString("Name"));
                list.add(tempDep);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }



}
