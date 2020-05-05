package com.example.stock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.UserDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.Utilis.HashUtil;
import com.example.stock.bean.User;
import com.example.stock.service.facade.UserService;

import antlr.Utils;

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
public int seConnecter(User user) throws Exception {
    User loadedUser = findByLogin(user.getLogin());
    if(loadedUser.isBloqued() == true) {
    if(DateUlils.debloquer(loadedUser.getDateBloquage())) {
    	loadedUser.setBloqued(false);
    	loadedUser.setNbrTentatifRestant(3);
    	userDao.save(loadedUser);
    }else {
       System.out.println("please wait 15 min");
    	return -4;
    }
    }
    if (loadedUser == null) {
        return -1;
    } else if (!loadedUser.getPwd().equalsIgnoreCase(HashUtil.hash(user.getPwd()))) {
        loadedUser.setNbrTentatifRestant(loadedUser.getNbrTentatifRestant() - 1);
        if (loadedUser.getNbrTentatifRestant() == 0) {
            loadedUser.setBloqued(true);
            loadedUser.setDateBloquage(new Date());
            userDao.save(loadedUser);
            return -2;
        } else {
        	userDao.save(loadedUser);
            return -3;
        }
    } else {
        return 1;
    }
}
@Override
public User findByLogin(String login) {
	return userDao.findByLogin(login);
}

}
