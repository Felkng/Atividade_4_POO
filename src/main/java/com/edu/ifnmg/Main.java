package com.edu.ifnmg;

import java.time.LocalDate;

import com.edu.ifnmg.credential.Credential;
import com.edu.ifnmg.credential.CredentialDao;

import com.edu.ifnmg.librarian.Librarian;
import com.edu.ifnmg.librarian.LibrarianDao;
import com.edu.ifnmg.role.Role;
import com.edu.ifnmg.role.RoleDao;
import com.edu.ifnmg.user.UserDao;


public class Main {
    public static void main(String[] args) {

        try {
            Role rola = new Role("Bibliotecario");
            new RoleDao().saveOrUpdate(rola);
            Long rolaID = new RoleDao().saveOrUpdate(rola);
            rola.setId(rolaID);
            Librarian luigi = null;
            luigi = new Librarian(
                "lUIGI",
                "LUIGI@MAIL.COM",
                LocalDate.now(),
                rola,
                new Credential(null, "LuigiIGI", "Lulu", null, true, luigi)
            );
            Long userID = new UserDao().saveOrUpdate(luigi);
            luigi.setId(userID);
            Long luigiID = new LibrarianDao().saveOrUpdate(luigi);
            // new CredentialDao().saveOrUpdate(luigi.getCredential());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}