package com.edu.ifnmg.credential;
import com.edu.ifnmg.entity.Entity;
import com.edu.ifnmg.user.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Credential extends Entity{
    private String username;
    private String password;
    private LocalDate lastAccess;
    private Boolean enabled;
    private User user;

    public Credential() {
        setEnabled(null);
    }

    public Credential(Long id, String username, String password, LocalDate lastAccess, Boolean enabled, User user) throws Exception{
        setId(id);
        setUsername(username);
        setPassword(password);
        setLastAccess(lastAccess);
        setEnabled(enabled);
        setUser(user);
    }


    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) throws Exception{
        if(username.length() > 15) throw new Exception("Tamanho invalido!"); 
        if(username.equals("")) throw new Exception("Role undefined!");
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception{
        this.password = password;
    }

    public LocalDate getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDate lastAccess) { 
        this.lastAccess = lastAccess;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        if(enabled == null) {
            this.enabled = true;
            return;
        }
        this.enabled = enabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ENCONTRADO {" + "username=" + username + ", password=" + password + ", lastAccess=" + lastAccess + ", enabled=" + enabled + '}';
    }
}
