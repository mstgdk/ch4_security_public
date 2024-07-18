package com.patika.security.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired private JwtUtils jwtUtils;
    @Autowired private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

          String jwtToken = parseJwt(request);

        try {
            if (jwtToken!=null && jwtUtils.validateJwtToken(jwtToken)){
                String email = jwtUtils.getEmailFromToken(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                //Security context
                UsernamePasswordAuthenticationToken aut = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(aut);
            }
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request,response);


    }

    private String parseJwt(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
}
