package com.edu.ifnmg.role;

import com.edu.ifnmg.repository.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class RoleDao  extends Dao<Role> {
    public static final String TABLE = "role";

    @Override
    public String getSaveStatement(){
        return "insert into " + TABLE + "(name) values (?)";
    }

    @Override
    public String getUpdateStatement(){
        return "update " + TABLE + " set name = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement(){
        return "select name from " + TABLE + "where id ?";
    }

    @Override
    public String getFindAllStatement(){
        return "select name from " + TABLE;
    }

    @Override
    public String getDeleteStatement(){
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
    public Role extractObject(ResultSet rs){

        Role queryRole = null;

        try{
            queryRole =  new Role();

            queryRole.setId(rs.getLong("id"));
            queryRole.setName(rs.getString("name"));
        }catch (Exception ex){
            System.out.println("Exception in extractObject: " + ex);
        }

        return queryRole;
    }

}