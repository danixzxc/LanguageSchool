package com.grishel.languageschool.server.service;

import com.grishel.languageschool.server.dao.HibernateDAO;
import com.grishel.languageschool.server.dao.JpaDAO;
import com.grishel.languageschool.shared.pojo.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserService {
	JpaDAO<User> dao = new HibernateDAO<User>(User.class);
	
	public User authorize(User userLogin) {
		User user = findByLogin(userLogin.getLogin());
		
		if (user == null) { return null; }
		if (user.getPassword().equals(userLogin.getPassword())) {
			return user;
		}
		return null;
	}
	
	public User findByLogin(String login) {
		CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.equal(root.get("login"), login));
		return dao.getCriteriaQuerySigleResult(cq);
	}
	
	public void registerUser(User userLogin) {
		userLogin.setId(null);
		userLogin.setRoles(null);
		dao.save(userLogin);
		return;
	}
	
	public void deleteUser(User user) {
		dao.delete(user);
	}
	
	public User getUser(Integer id) {
		User user = dao.get(id);
		return user;
	}
	
	
}