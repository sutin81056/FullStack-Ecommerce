//package com.example.mydemo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.List;
//
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        //權限(固定用法)
//        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
//        //return 用戶名稱,密碼,用戶角色（權限），用戶角色不可以為Null!
//        return new User("admin",new BCryptPasswordEncoder().encode("admin"),auths);
//    }
//}
