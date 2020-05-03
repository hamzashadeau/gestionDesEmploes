package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.UserDao;
import com.example.stock.bean.User;
import com.example.stock.service.facade.UserService;

@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserDao userDao;


@Override
public int save(User user) {
	if(findByid(user.getId())!= null) {
return -1;
}else {
	userDao.save(user);
		return 1;
}
	}

@Override
public User findByid(Long id) {
	if (userDao.findById(id).isPresent()) {
		return userDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	userDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<User> findAll() {
	return userDao.findAll();
}


@Override
public User findByLogin(String login) {
	return userDao.findByLogin(login);
}

}
