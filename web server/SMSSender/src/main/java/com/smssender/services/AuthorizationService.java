package com.smssender.services;

import com.smssender.model.User;
import com.smssender.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService extends BasicService {
    private UserRepository userRepository;
    private MessageService messageService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public boolean checkUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            messageService.setUser(userRepository.getByEmail(user.getEmail()));
            return true;
        } else {
            return false;
        }
    }
}
