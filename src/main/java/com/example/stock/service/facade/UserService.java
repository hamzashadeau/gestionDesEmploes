package com.example.stock.service.facade;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.xml.transform.TransformerException;

import com.example.stock.bean.User;

public interface UserService {
	User findByid(Long id);
	User findByLogin(String login);
	List<User> findAll();
	int save(User prix);
	int deleteById(Long id);
    int seConnecter(User user) throws Exception;
    public int sendCode(String email) throws AddressException, MessagingException, IOException, TransformerException;
    public int resetPasswordCodeVerification(String email, String nvpassword,Long code ) throws Exception;
    public int resetPassword(String email, String oldPassword, String nvPassword) throws Exception;

}
