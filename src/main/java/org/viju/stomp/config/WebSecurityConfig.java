package org.viju.stomp.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //TODO Refactor login form
                .authorizeRequests()
                .antMatchers("/lib/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .defaultSuccessUrl("/index.html")
                .loginPage("/login.html")

                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("fabrice").password("fab123").roles("USER").and()
                .withUser("paulson").password("bond").roles("ADMIN", "USER");
    }


}