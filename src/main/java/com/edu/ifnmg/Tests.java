package com.edu.ifnmg;

import java.time.LocalDate;

import com.edu.ifnmg.credential.Credential;
import com.edu.ifnmg.credential.CredentialDao;
import com.edu.ifnmg.entity.Entity;
import com.edu.ifnmg.librarian.Librarian;
import com.edu.ifnmg.librarian.LibrarianDao;
import com.edu.ifnmg.reader.Reader;
import com.edu.ifnmg.reader.ReaderDao;
import com.edu.ifnmg.repository.Dao;
import com.edu.ifnmg.role.Role;
import com.edu.ifnmg.role.RoleDao;
import com.edu.ifnmg.user.UserDao;
import java.util.ArrayList;

public class Tests {
    public static void TestA() {
        System.out.println("--------------------------------------TEST A--------------------------------------");
        try {
            Role role = new Role("Bibliotecario");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);

            Librarian luigi = null;
            luigi = new Librarian(
            "luigi um",
            "luigi@mail.COM",
            LocalDate.now(),
            role,
            new Credential(null, "LuigiIGI", "Lulu", null, true, luigi)
            );

            Long userId = new UserDao().saveOrUpdate(luigi);
            new LibrarianDao().saveOrUpdate(luigi,userId);

            luigi = new LibrarianDao().findById((long) luigi.getId());
            luigi.setEmail("luigi@maiaiial.com");
            luigi.setBirthDate(LocalDate.of(2003, 05, 22));
            new LibrarianDao().saveOrUpdate(luigi);

            System.out.println("Bilbiotecario: " + luigi.getName() + " Email: " + luigi.getEmail());

            // new CredentialDao().saveOrUpdate(luigi.getCredential());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void TestB() {
        System.out.println("--------------------------------------TEST B--------------------------------------");
        try{
            Role role = new Role("Leitor");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);

            Reader felipe = null;
            felipe = new Reader(
            "felipe um",
            "felipe@mail.COM",
            LocalDate.now(),
            role,
            new Credential(null, "felipeJJ", "Lulu", null, true, felipe)
            );

            Long userId = new UserDao().saveOrUpdate(felipe);
            new ReaderDao().saveOrUpdate(felipe,userId);
            
            felipe = new ReaderDao().findById((long)felipe.getId());
            felipe.setEmail("felipinho@mail.com");
            felipe.setBirthDate(LocalDate.of(2077, 8, 10));
            new ReaderDao().saveOrUpdate(felipe);

            System.out.println("Leitor: " + felipe.getName() + " Email: " + felipe.getEmail());

            // new CredentialDao().saveOrUpdate(luigi.getCredential());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void TestC() {
        System.out.println("--------------------------------------TEST C--------------------------------------");
        try {
            Role role = new Role("Leitor");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);

            Reader marcos = null;
            marcos = new Reader(
                    "marcos um",
                    "marcos@mail.COM",
                    LocalDate.now(),
                    role,
                    new Credential(null, "marcosmarquin", "Lulu", null, true, marcos));

            Long userId = new UserDao().saveOrUpdate(marcos);
            new ReaderDao().saveOrUpdate(marcos, userId);

            marcos = new ReaderDao().findById((long) marcos.getId());
            marcos.setEmail("felipinho@mail.com");
            marcos.setBirthDate(LocalDate.of(1977, 12, 26));
            new ReaderDao().saveOrUpdate(marcos);

            System.out.println(marcos.getName());

            // new CredentialDao().saveOrUpdate(luigi.getCredential());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void TestD(){
        System.out.println("--------------------------------------TEST D--------------------------------------");
        ArrayList<Librarian> allObjects = null;
        
        try{
            allObjects = new LibrarianDao().findAll();
            for(var x : allObjects){
                System.out.println(x.getName());
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void TestE(){
        System.out.println("--------------------------------------TEST E--------------------------------------");
        ArrayList<Reader> allObjects = null;
        
        try{
            allObjects = new ReaderDao().findAll();
            for(var x: allObjects){
                System.out.println(x.getName());
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static void TestF(){
        System.out.println("--------------------------------------TEST E--------------------------------------");
        
        try{
            
        }catch(Exception ex){
        
        }
    }
}