package com.edu.ifnmg.librarian;

import java.time.LocalDate;

import com.edu.ifnmg.credential.Credential;
import com.edu.ifnmg.role.Role;
import com.edu.ifnmg.user.User;

public class Librarian extends User{

    public Librarian(){
        super();
    }

    public Librarian(String name, String email, LocalDate birthDate, Role role, Credential credential) throws Exception{
        super(name, email, birthDate, role, credential);
    }

}
