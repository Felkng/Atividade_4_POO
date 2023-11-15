package com.edu.ifnmg.credential;
import com.edu.ifnmg.repository.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CredentialDao extends Dao<Credential> {
    public static final String TABLE = "credential";
    // private static final int SALT = (int) "!asdf";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (username, password, last_access, enabled) values(?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update "+ TABLE + " set username = ?, password = ?, last_access = ?, enabled = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, username, password, last_access, enabled from credential where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id, username, password, last_access, enabled from credential";
    }

    @Override
    public String getDeleteStatement() {
        return "Delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Credential e) {
        try {
            pstmt.setString(1, e.getUsername());
            pstmt.setString(2, e.getPassword());
            pstmt.setObject(3, e.getLastAccess());
            pstmt.setBoolean(4, e.getEnabled());

            if (e.getId() != null) {
                pstmt.setLong(8, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CredentialDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Credential extractObject(ResultSet resultSet) {

        Credential credential = null;

        try {
            credential = new Credential();
            credential.setId(resultSet.getLong("id"));
            credential.setUsername(resultSet.getString("username"));
            credential.setPassword(resultSet.getString("password"));
            credential.setLastAccess( resultSet.getObject("last_access", LocalDate.class));
            credential.setEnabled(resultSet.getBoolean("enabled"));
        }catch (Exception ex) {
            Logger.getLogger(CredentialDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return credential;
    }
}

