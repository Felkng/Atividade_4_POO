package com.edu.ifnmg;

import java.time.LocalDate;

import com.edu.ifnmg.credential.Credential;
import com.edu.ifnmg.credential.CredentialDao;
import com.edu.ifnmg.librarian.Librarian;
import com.edu.ifnmg.librarian.LibrarianDao;
import com.edu.ifnmg.reader.Reader;
import com.edu.ifnmg.reader.ReaderDao;
import com.edu.ifnmg.role.Role;
import com.edu.ifnmg.role.RoleDao;
import com.edu.ifnmg.user.User;
import java.util.ArrayList;

public class Tests {
    public static void TestA() {
        System.out.println("--------------------------------------TEST A--------------------------------------");
        try {
            Role role = new Role("Bibliotecario");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);
            
            Credential luigiC = new Credential(null, "Luigi", "lulu", LocalDate.now(), null, null);
            
            Librarian luigi = null;
            luigi = new Librarian(
                    "Luigi Eliabe",
                    "luigi@mail.COM",
                    LocalDate.now(),
                    role,
                    luigiC);
    
            luigiC.setUser(luigi);
            Long userId = new LibrarianDao().saveOrUpdate(luigi);
            
            Librarian luigiTeste = null;
            luigiTeste = new LibrarianDao().findById(userId);
            luigiTeste.setEmail("luigi@maiaiidsoifdhsifal.com");
            luigiTeste.setBirthDate(LocalDate.of(2093, 05, 22));
            new LibrarianDao().saveOrUpdate(luigiTeste);

            System.out.println("Bibliotecário criado com sucesso");
            System.out.println(luigiTeste.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void TestB() {
        System.out.println("--------------------------------------TEST B--------------------------------------");
        try {
            Role role = new Role("Leitor");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);

            Reader felipe = null;
            felipe = new Reader(
                    "Felipe Rodrigues",
                    "felipe@mail.COM",
                    LocalDate.now(),
                    role,
                    new Credential(null, "felipe", "fefe", LocalDate.now(), true, felipe));
            felipe.getCredential().setUser(felipe);
            Long userId = new ReaderDao().saveOrUpdate(felipe);

            felipe = new ReaderDao().findById(userId);
            felipe.setEmail("felipinho@mail.com");
            felipe.setBirthDate(LocalDate.of(2077, 8, 10));
            new ReaderDao().saveOrUpdate(felipe);

            System.out.println("Leitor 1 criado com sucesso");
            System.out.println(felipe.toString());

        } catch (Exception ex) {
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
                    "Marcos Danel",
                    "marcos@mail.COM",
                    LocalDate.now(),
                    role,
                    new Credential(null, "marcos", "tresesposas", LocalDate.now(), true, marcos));
            marcos.getCredential().setUser(marcos);
            Long userId = new ReaderDao().saveOrUpdate(marcos);

            marcos = new ReaderDao().findById(userId);
            marcos.setEmail("felipinho@mail.com");
            marcos.setBirthDate(LocalDate.of(1977, 12, 26));
            new ReaderDao().saveOrUpdate(marcos);

            System.out.println("Leitor 2 criado com sucesso");
            System.out.println(marcos.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void TestD() {
        System.out.println("--------------------------------------TEST D--------------------------------------");
        ArrayList<Librarian> allObjects = null;

        try {
            allObjects = new LibrarianDao().findAll();
            System.out.println("Todos os bibliotecários: ");
            for (var x : allObjects) {
                System.out.println(x.getName());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void TestE() {
        System.out.println("--------------------------------------TEST E--------------------------------------");
        ArrayList<Reader> allObjects = null;

        try {
            allObjects = new ReaderDao().findAll();
            System.out.println("Todos os leitores: ");
            for (var x : allObjects) {
                System.out.println(x.getName());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void TestF() {
        System.out.println("--------------------------------------TEST F--------------------------------------");

        try {
            Credential luigi = new Credential();
            luigi.setUsername("Luigi");
            luigi.setPassword("lulu");

            User user = new CredentialDao().authenticate(luigi);
            user.setCredential(new CredentialDao().findById(user.getId()));
            System.out.println(user.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}