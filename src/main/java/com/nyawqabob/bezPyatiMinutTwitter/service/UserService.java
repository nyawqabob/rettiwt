/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyawqabob.bezPyatiMinutTwitter.service;

import com.nyawqabob.bezPyatiMinutTwitter.entity.Role;
import com.nyawqabob.bezPyatiMinutTwitter.entity.User;
import com.nyawqabob.bezPyatiMinutTwitter.exception.ServiceException;
import com.nyawqabob.bezPyatiMinutTwitter.repos.UserRepo;
import java.util.Collections;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements UserDetailsService {

    @Value("${localhost.path}")
    private String localhost;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        return userRepo.findByUsername(string);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public void addUser(User user) throws ServiceException {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        User userFromDbSecond = userRepo.findByEmail(user.getEmail());
        if (userFromDb != null) {
            throw new ServiceException("User already exist");
        }
        if (userFromDbSecond != null) {
            throw new ServiceException("Email exists");
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello %s! \n"
                    + "Welcome to Dizer. To activate ur account visit next link: %sactivate/%s", user.getUsername(),
                    localhost, user.getActivationCode());
            mailSender.sendMail(user.getEmail(), "Activation code from DizeR", message);
        }
        {
            userRepo.save(user);
        }
    }

    public void activate(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            throw new ServiceException("Activation code is not found");
        }
        user.setActive(true);
        user.setActivationCode(null);
        userRepo.save(user);
    }

}
