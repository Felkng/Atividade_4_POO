package com.edu.ifnmg.user;

import com.edu.ifnmg.entity.Entity;

import java.time.LocalDate;

public class User extends Entity {
    private String name;
    private String email;
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception{
        if(name.length() > 150)
            throw new Exception("Name has more than 150 caracteres!");
        else if(name.equals(""))
            throw new Exception("Name is null!");
        else
            this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if(email.length() > 255)
            throw new Exception("Email has more than 255 caracters!");
        else if(email.equals(""))
            throw new Exception("Email is null!");
        else
            this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws Exception{
        if(birthDate == null)
            throw new Exception("Wrong birthDate!");
        else
            this.birthDate = birthDate;
    }
}
