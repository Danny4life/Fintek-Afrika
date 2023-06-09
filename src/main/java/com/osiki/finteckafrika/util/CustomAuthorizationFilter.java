package com.osiki.finteckafrika.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osiki.finteckafrika.model.ErrorResponse;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomAuthorizationFilter.class);
    
    private UserDetailsService userDetailsService;
    
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        try{
            String token = extractBearerToken(request);
            
            if(StringUtils.isNotEmpty(token) && SecurityContextHolder.getContext().getAuthentication() == null){
                String username = jwtUtil.extractUsername(token);
                
                if(username != null){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    
                    if(jwtUtil.validateToken(token, userDetails)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.
                                setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
            
            filterChain.doFilter(request, response);
        }catch (JwtException e){
            this.writeErrorResponse("Invalid token", response, HttpStatus.UNAUTHORIZED);
        }

    }

    private void writeErrorResponse(String errMsg, HttpServletResponse response, HttpStatus httpStatus) {

        try{
            ErrorResponse er = new ErrorResponse(httpStatus);
            er.setMessage(errMsg);
            response.setStatus(httpStatus.value());
            response.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(er));
        }catch (Exception e){
            LOGGER.error("Unknown error", e);
        }
    }

    private String extractBearerToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            token = authorizationHeader.substring(7);
        }

        return token;
    }
}
