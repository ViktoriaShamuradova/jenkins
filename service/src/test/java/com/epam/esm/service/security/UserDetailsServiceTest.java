package com.epam.esm.service.security;

import com.epam.esm.persistence.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserDao userDao;

    @Test
    public void shouldThrownException() {

        when(userDao.findByEmail(anyString())).thenThrow(UsernameNotFoundException.class);
        assertThatThrownBy(() -> userDetailsService.loadUserByUsername(anyString()))
                .isInstanceOf(UsernameNotFoundException.class);

    }
}
