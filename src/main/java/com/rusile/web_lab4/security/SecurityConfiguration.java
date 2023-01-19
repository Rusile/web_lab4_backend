package com.rusile.web_lab4.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Value("${conf.login_page_path}")
    private String loginPagePath;

    public SecurityConfiguration() {
        super();
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests() // объявляем авторизованные запросчаем статику и страницу авторизации
                .antMatchers("/auth").permitAll() // исключаем POST-запрос на авторизацию
                .antMatchers(HttpMethod.PATCH).permitAll()
                .anyRequest().authenticated() // всё остальное должно быть с авторизацией
                .and() // добавляем обработчик ошибок авторизации
                .exceptionHandling()
                .authenticationEntryPoint(
                        new AuthenticationEntryPoint() {
                            @Override
                            public void commence(
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    AuthenticationException exception
                            ) {
                                // если ошибка при авторизации (т.е. введен неправильный логин или пароль)
                                if (exception instanceof BadCredentialsException) {
                                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                } else { // если просто открыли защищенный раздел, то редирект на страницу авторизации
                                    response.setStatus(HttpStatus.FOUND.value());
                                    response.setHeader(HttpHeaders.LOCATION, request.getContextPath() + loginPagePath);
                                }
                            }
                        }
                )
                .and() // сообщаем, что форма авторизации находится по заданному URL
                .formLogin()
                .loginPage(loginPagePath)
                .and() // отключаем CSRF для всех запросов
                .csrf().disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
