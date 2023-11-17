package com.edu.ifnmg.librarian;

import com.edu.ifnmg.credential.Credential;
import com.edu.ifnmg.credential.CredentialDao;
import com.edu.ifnmg.repository.Dao;
import com.edu.ifnmg.role.Role;
import com.edu.ifnmg.role.RoleDao;
import com.edu.ifnmg.user.User;
import com.edu.ifnmg.user.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class LibrarianDao extends Dao<Librarian> {
    public static final String TABLE = "librarian";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(name,email,birthdate,id) values (?,?,?,?)";
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
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Librarian librarian) {
        try {
            if(librarian.getName() != null)
                pstmt.setObject(1, librarian.getName(), Types.VARCHAR);
            if(librarian.getEmail() != null)
                pstmt.setObject(2, librarian.getEmail(), Types.VARCHAR);
            if(librarian.getBirthDate() != null)
                pstmt.setObject(3, librarian.getBirthDate(), Types.VARCHAR);
            // if(librarian.getRole().getId() != null)
                // pstmt.setObject(4, librarian.getRole().getId(), Types.BIGINT);
            if (librarian.getId() != null) {
                pstmt.setObject(4, librarian.getId(), Types.BIGINT);
            }
        } catch (SQLException ex) {
            System.out.println("Exception in composeSave or Update: " + ex);
        }
    }

    @Override
    public Librarian extractObject(ResultSet rs) {

        Librarian queryLibrarian = null;

        try {

            queryLibrarian = new Librarian();
            
            queryLibrarian.setId(rs.getLong("id"));
            User user = new UserDao().findById(queryLibrarian.getId());
            Credential credential = new CredentialDao().findById(queryLibrarian.getId());
            queryLibrarian.setName(user.getName());
            queryLibrarian.setEmail(user.getEmail());
            queryLibrarian.setRole(user.getRole());
            queryLibrarian.setBirthDate(user.getBirthDate());
            queryLibrarian.setCredential(credential);
        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }

        return queryLibrarian;
    }

}