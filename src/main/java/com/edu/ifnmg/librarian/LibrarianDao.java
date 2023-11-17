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
    public Long saveOrUpdate(Librarian e) {
        Long idLibrarian = new UserDao().saveOrUpdate(e);
       
        if ( e.getId() == null || e.getId() == 0) {
            e.setId(-idLibrarian);
        } else {
            e.setId(idLibrarian);
        }

        super.saveOrUpdate(e);

        return idLibrarian;
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Librarian e){
        try {
            if(e.getId() != null && e.getId() < 0) {
                pstmt.setString(1, e.getName());
                pstmt.setString(2, e.getEmail());
                pstmt.setObject(3, e.getBirthDate(), Types.VARCHAR);
                pstmt.setLong(4, -e.getId());
            } else {
                pstmt.setString(1, e.getName());
                pstmt.setString(2, e.getEmail());
                pstmt.setObject(3, e.getBirthDate(), Types.VARCHAR);
                pstmt.setLong(4, e.getId());
            }
        } catch ( SQLException ex ) {
            System.out.println("Exception in composeSave or Update: " + ex);
        }
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