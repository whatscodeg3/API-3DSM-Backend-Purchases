package com.br.whatsCodePaymentMicroservice.service;

import com.br.whatsCodePaymentMicroservice.repository.EmployeeRepository;
import com.br.whatsCodePaymentMicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return employeeRepository.findByCpf(username);
    }
}