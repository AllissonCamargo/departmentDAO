package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;
    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void uptdate(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement
                    (
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?"
                    );

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Department tempDep = new Department();
                tempDep.setId(resultSet.getInt("DepartmentId"));
                tempDep.setName(resultSet.getString("DepName"));
                Seller obj = new Seller();
                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));
                obj.setEmail(resultSet.getString("Email"));
                obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
                obj.setBirthDate(resultSet.getDate("BirthDate"));
                obj.setDepartment(tempDep);
                return obj;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
