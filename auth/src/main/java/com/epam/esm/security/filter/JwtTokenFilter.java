package com.epam.esm.security.filter;

import com.epam.esm.security.exception.ExceptionErrorCode;
import com.epam.esm.security.exception.InvalidTokenException;
import com.epam.esm.security.exception.SpringSecurityExceptionHandler;
import com.epam.esm.security.service.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final SpringSecurityExceptionHandler exceptionHandler;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Optional<String> token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            if (token.isPresent() && jwtTokenProvider.validateToken(token.get())) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token.get());
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            exceptionHandler.commence((HttpServletRequest) request, (HttpServletResponse) response,
                    new InvalidTokenException(ExceptionErrorCode.ACCESS_IS_DENIED.getErrorCode()));
        }
        chain.doFilter(request, response);
    }
}
