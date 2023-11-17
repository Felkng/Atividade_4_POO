package com.edu.ifnmg.credential;
import com.edu.ifnmg.repository.Dao;
import com.edu.ifnmg.repository.DbConnection;
import com.edu.ifnmg.user.User;
import com.edu.ifnmg.user.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CredentialDao extends Dao<Credential> {
    public static final String TABLE = "credential";
    private static final String SALT = "!asdf";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (username, password, last_access, enabled, id) values(?, md5(?), ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update "+ TABLE + " set username = ?, password = ?, last_access = ?, enabled = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, username, password, last_access, enabled from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id, username, password, last_access, enabled from credential";
    }

    @Override
    public String getDeleteStatement() {
        return "Delete from " + TABLE + " where id = ?";
    }

    private String findUser(){
        return "select id, username, password, last_access, enabled from " + TABLE + " where username = ? and password = md5(?)";
    }

    private Credential findByCredential(Credential credential) {
        try ( PreparedStatement preparedStatement
                      = DbConnection.getConnection().prepareStatement(
                findUser())) {

            // Assemble the SQL statement with the id
            preparedStatement.setString(1, credential.getUsername());
            preparedStatement.setString(2, credential.getPassword() + SALT);
            // Show the full sentence
            System.out.println(">> SQL: " + preparedStatement);

            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object if exists
            if (resultSet.next()) {
                return extractObject(resultSet);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }


    public User authenticate(Credential credential){
        User user = null;
        try{
        Credential credentialInDataBase = findByCredential(credential);
        if(credentialInDataBase != null)
            user = new UserDao().findById(credentialInDataBase.getId());
        
        }catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }
        return user;
    }
    
    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Credential e) {
        try {
            pstmt.setString(1, e.getUsername());
            pstmt.setString(2, e.getPassword() + SALT);
            pstmt.setObject(3, e.getLastAccess());
            pstmt.setBoolean(4, e.getEnabled());

            if (e.getId() != null) {
                pstmt.setLong(5, e.getId());
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            System.out.println("Exception in extractObject: " + ex);
        }
    }

    @Override
    public Credential extractObject(ResultSet resultSet) {

        Credential credential = null;

        try {
            credential = new Credential();
            credential.setId(resultSet.getLong("id"));
            User user = new UserDao().findById(credential.getId());
            credential.setUser(user);
            credential.setUsername(resultSet.getString("username"));
            credential.setPassword(resultSet.getString("password"));
            credential.setLastAccess( resultSet.getObject("last_access", LocalDate.class));
            credential.setEnabled(resultSet.getBoolean("enabled"));
         } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return credential;
    }

}

