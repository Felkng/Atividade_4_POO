package com.edu.ifnmg.credential;
import com.edu.ifnmg.entity.Entity;

import java.time.LocalDate;

public class Credential extends Entity{
    private String username;
    private String password;
    private LocalDate lastAccess;
    private Boolean enabled;

    public Credential() {
    }

    public Credential(Long id, String username, String password, LocalDate lastAccess, Boolean enabled) throws Exception{
        setId(id);

        setUsername(username);
        setPassword(password);
        setLastAccess(lastAccess);
        setEnabled(enabled);
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) throws IllegalAccessException{
        if(username.length() > 15) throw new IllegalAccessException("Tamanho invalido!"); 
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalAccessException{
        if(password.length() > 32) throw new IllegalAccessException("Tamanho invalido!"); 
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
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ENCONTRADO {" + "username=" + username + ", password=" + password + ", lastAccess=" + lastAccess + ", enabled=" + enabled + '}';
    }
}
