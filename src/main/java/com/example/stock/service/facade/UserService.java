package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.User;

public interface UserService {
	User findByid(Long id);
	User findByLogin(String login);
	List<User> findAll();
	int save(User prix);
	int deleteById(Long id);
    int seConnecter(User user) throws Exception;
}
