package com.example.webdev.service;

import com.example.webdev.repository.PersonalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonalService implements UserDetailsService {

    @Autowired
    private PersonalRepo personalRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personalRepo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
