package com.rizvi.spring.filter;

import com.rizvi.spring.constant.SecurityConstant;
import com.rizvi.spring.utility.JWTTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.rizvi.spring.constant.SecurityConstant.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JWTTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      if(request.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)){
          response.setStatus(HttpStatus.OK.value());
      }else{
          String authorizationHeader = request.getHeader(AUTHORIZATION);
          if(authorizationHeader == null || authorizationHeader.startsWith(TOKEN_PREFIX)){
              filterChain.doFilter(request, response);
              return;
          }
      }


    }
}
