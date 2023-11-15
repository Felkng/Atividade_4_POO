package com.edu.ifnmg.role;

import com.edu.ifnmg.entity.Entity;

public class Role extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception{
        if(name.length() > 20)
            throw new Exception("Role name has more than 20 caracters!");
        if(name.equals(""))
            throw new Exception("Role undefined!");
        
        this.name = name;
    }

    public Role(String name) throws Exception{
        setName(name);
    }

    public Role (){}

}
