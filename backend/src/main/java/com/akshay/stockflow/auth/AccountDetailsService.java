package com.akshay.stockflow.auth;
import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service public class AccountDetailsService implements UserDetailsService {private final UserAccountRepository users; public AccountDetailsService(UserAccountRepository users){this.users=users;} @Override public UserDetails loadUserByUsername(String email){return users.findByEmailIgnoreCase(email).orElseThrow(()->new UsernameNotFoundException("Account not found"));}}
