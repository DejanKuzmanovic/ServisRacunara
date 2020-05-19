package com.servisracunara.pmf.security.config;

import com.servisracunara.pmf.security.auth.CustomAuthenticationFailureHandler;
import com.servisracunara.pmf.security.auth.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ServisRacunaraWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(
            UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().and().csrf().disable()
        .antMatcher("/page/**")
        .authorizeRequests()
            .antMatchers("/page/**").permitAll()
            .and()
        .formLogin()
            .loginPage("/page/index")
            .loginProcessingUrl("/page/authenticate")
            .failureHandler(new CustomAuthenticationFailureHandler())
            .and()
        .logout()
            .logoutUrl("/page/logout")
            .logoutRequestMatcher(new AntPathRequestMatcher("/page/logout"))
            .clearAuthentication(true)
            .logoutSuccessUrl("/page/index");
    }
}