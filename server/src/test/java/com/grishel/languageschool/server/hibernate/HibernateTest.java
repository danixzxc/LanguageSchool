package com.grishel.languageschool.server.hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.grishel.languageschool.shared.pojo.Role;
import com.grishel.languageschool.shared.pojo.User;


/**
 * Unit test for simple App.
 */
public class HibernateTest 
{
	@Disabled
    @Test
    public void test() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	
    	User user = new User();
    	
    	user.setLogin("daniil");
    	user.setPassword("1234");
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	Transaction transaction = session.beginTransaction();
    	
    	session.persist(user);
    	Integer id = user.getId();
    	System.out.println(id);
    	
    	user = session.get(User.class, id);
    	
    	
    	List<Role> roles = new ArrayList<Role>();
    	Role role = new Role();
    	role.setRole("Admin");
    	role.setUser(user);
    	roles.add(role);
    	user.setRoles(roles);
    	
    	session.persist(user);
    	
    	transaction.commit();
    	// Пауза
    	try {
			reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	HibernateUtil.shutdown();
    }
}
