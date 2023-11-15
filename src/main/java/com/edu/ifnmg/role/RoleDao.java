package com.edu.ifnmg.role;

import com.edu.ifnmg.entity.Entity;
import com.edu.ifnmg.repository.Dao;
import com.edu.ifnmg.repository.DbConnection;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class RoleDao extends Dao<Role> {
    public static final String TABLE = "role";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (name) values (?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set name = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select name, id from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select name, id from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Role e) {
        try {
            pstmt.setObject(1, e.getName(), Types.VARCHAR);

            if (e.getId() != null) {
                pstmt.setObject(2, e.getId(), Types.BIGINT);
            }
        } catch (SQLException ex) {
            System.out.println("Exception in composeSave or Update: " + ex);
        }
    }

    @Override
    public Long saveOrUpdate(Role role) {
        Long id = 0L;

        ArrayList<Role> alreadyHave = new RoleDao().findAll();

        for (var x : alreadyHave) {
            if (x.getName().equals(role.getName()))
                role.setId(x.getId());
        }

        if (role.getId() != null) {
            return role.getId();
        }

        if (role.getId() == null
                || role.getId() == 0) {

            // Insert a new register
            // try-with-resources
            try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(
                    getSaveStatement(),
                    Statement.RETURN_GENERATED_KEYS)) {

                // Assemble the SQL statement with the data (->?)
                composeSaveOrUpdateStatement(preparedStatement, role);

                // Show the full sentence
                System.out.println(">> SQL: " + preparedStatement);

                // Performs insertion into the database
                preparedStatement.executeUpdate();

                // Retrieve the generated primary key
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                // Moves to first retrieved data
                if (resultSet.next()) {

                    // Retrieve the returned primary key
                    id = resultSet.getLong(1);
                }

            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
            }

        } else {
            // Update existing record
            try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(
                    getUpdateStatement())) {

                // Assemble the SQL statement with the data (->?)
                composeSaveOrUpdateStatement(preparedStatement, role);

                // Show the full sentence
                System.out.println(">> SQL: " + preparedStatement);

                // Performs the update on the database
                preparedStatement.executeUpdate();

                // Keep the primary key
                id = (role.getId());

            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
            }
        }

        return id;
    }

    @Override
    public Role extractObject(ResultSet rs) {

        Role queryRole = null;

        try {
            queryRole = new Role();

            queryRole.setId(rs.getLong("id"));
            queryRole.setName(rs.getString("name"));
        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }

        return queryRole;
    }

}