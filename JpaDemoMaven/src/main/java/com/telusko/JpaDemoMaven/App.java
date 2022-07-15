package com.telusko.JpaDemoMaven;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        EntityManagerFactory ef=Persistence.createEntityManagerFactory("pu");
        
        EntityManager em=ef.createEntityManager();
     
        Alien a=em.find(Alien.class, 2);        
        System.out.println(a);
        
        
        Alien a1=new Alien();
        //a1.setId(4);
        a1.setName("Jrr yadav");
        a1.setPoints(44);
        em.getTransaction().begin();
        em.persist(a1);
        em.getTransaction().commit();
        
    }
}
