package com.edu.ifnmg.reader;

import java.time.LocalDate;

import com.edu.ifnmg.credential.Credential;
import com.edu.ifnmg.role.Role;
import com.edu.ifnmg.user.User;

public class Reader extends User{
    public Reader(){
        super();
    }

    public Reader(String name, String email, LocalDate birthDate, Role role, Credential credential) throws Exception{
        super(name, email, birthDate, role, credential);
    }
}
