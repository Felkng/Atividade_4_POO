package com.edu.ifnmg.reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


import com.edu.ifnmg.repository.Dao;

public class ReaderDao extends Dao<Reader>{
    public static final String TABLE = "reader";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (name,email,birthdate, id) values (?,?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set name = ?, email = ?, birthdate = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, name, email, birthdate from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id, name, email, birthdate from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Reader e) {
        try {
            pstmt.setObject(1, e.getName(), Types.VARCHAR);
            pstmt.setObject(2, e.getEmail(), Types.VARCHAR);
            pstmt.setObject(3, e.getBirthDate(), Types.VARCHAR);

            if (e.getId() != null) {
                pstmt.setObject(4, e.getId(), Types.BIGINT);
            }
        } catch (SQLException ex) {
            System.out.println("Exception in composeSave or Update: " + ex);
        }
    }

    @Override
    public Reader extractObject(ResultSet rs) {

        Reader queryReader = null;

        try {
            queryReader = new Reader();

            queryReader.setId(rs.getLong("id"));
            queryReader.setName(rs.getString("name"));
        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }

        return queryReader;
    }
    
}
