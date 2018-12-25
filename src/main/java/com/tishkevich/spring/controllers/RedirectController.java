package com.tishkevich.spring.controllers;

import com.tishkevich.spring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/redirect")
public class RedirectController {
@Autowired
UserDetailsServiceImpl userDetailsService;

    @GetMapping("new")
    public String getRedirect(Principal principal){
        UserDetails user = userDetailsService.loadUserByUsername(principal.getName());
        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }
    }
}
