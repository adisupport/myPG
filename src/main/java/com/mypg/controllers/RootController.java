package com.mypg.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Collection;

@Controller
public class RootController {
    @GetMapping
    @ResponseBody
    public String getPage(Authentication authentication, HttpServletResponse response) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Determine the role and redirect accordingly
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_GUEST"))) {
            response.sendRedirect("/guest");
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_OWNER"))) {
            response.sendRedirect("/owner");
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            response.sendRedirect("/admin");
        } else {
            // Optional: if no role matches, you can redirect to a default page
            response.sendRedirect("/default");
        }
        return "root";
    }
}
