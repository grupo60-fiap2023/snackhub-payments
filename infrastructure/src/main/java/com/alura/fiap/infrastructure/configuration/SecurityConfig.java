package com.alura.fiap.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configuração da política de segurança de conteúdo (CSP)
        http.headers()
                .contentSecurityPolicy("default-src 'self'; script-src 'self' https://trusted.cdn.com; " +
                        "frame-ancestors 'self'; form-action 'self'; img-src 'self' https://goqr.me https://*.goqr.me data: blob:;");

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/merchant/orders/receiveMerchantOrder/**").permitAll()  // Endpoint específico
                .antMatchers("/merchant/orders/receiveQrDataPayment/**").permitAll()  // Endpoint específico
                .antMatchers("/merchant/**").hasRole("MERCHANT")  // Padrão mais genérico
                .antMatchers("/notification").permitAll()
                .antMatchers("/public/**").permitAll()
                .and().logout().permitAll();
        http.csrf().disable();
        http.cors().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.sessionManagement().disable();
        http.headers().frameOptions().disable();
        http.headers().frameOptions().deny();
        http.headers().frameOptions().sameOrigin();
        http.headers().cacheControl();
        http.headers().contentTypeOptions();
        http.headers().xssProtection();
        http.headers().referrerPolicy();
        http.headers().httpStrictTransportSecurity();
        http.headers().permissionsPolicy();

    }

}


