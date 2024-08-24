package com.scm.ContactManager.config;

import com.scm.ContactManager.services.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAurhenticationSucsessHandler handler;

    @Autowired
    private AuthFailtureHandler authFailtureHandler; // Corrected the typo in the class name

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/user/**").authenticated();
                    authorize.anyRequest().permitAll();
                })
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/login")
                            .loginProcessingUrl("/authenticate")
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/user/dashboard");
                            })
                            .failureHandler(authFailtureHandler) // Use AuthFailtureHandler here
                            .usernameParameter("email")
                            .passwordParameter("password");
                })
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .logout(logout -> {
                    logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout=true") // Append ?logout=true to URL on success
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true);
                });

        // OAuth security config
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}















// package com.scm.ContactManager.config;


// import com.scm.ContactManager.services.impl.SecurityCustomUserDetailService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {
//     @Autowired
//     private SecurityCustomUserDetailService userDetailService;
//     @Autowired
//     private OAuthAurhenticationSucsessHandler handler;
//     @Autowired
//     private AuthFailtureHandler authFailtureHandler;
//     // For example, configuring user details service, authentication manager, and authorization rules, spring security
//     @Bean
//     public DaoAuthenticationProvider authenticationProvider(){
//         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//         daoAuthenticationProvider.setUserDetailsService(userDetailService);
//         daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//         return daoAuthenticationProvider;

//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
//                 .authorizeHttpRequests(authorize -> {
//                     authorize.requestMatchers("/user/**").authenticated();
//                     authorize.anyRequest().permitAll();
//                 })
//                 .formLogin(formLogin -> {
//                     formLogin
//                             .loginPage("/login")
//                             .loginProcessingUrl("/authenticate")
//                             .successHandler((request, response, authentication) -> {
//                                 response.sendRedirect("/user/dashboard");
//                             })
//                             .failureUrl("/login?error=true")
//                             .usernameParameter("email")
//                             .passwordParameter("password")
//                             .failureHandler(authFailtureHandler);

                    
//                 })
//                 .csrf(csrf -> csrf.disable()) // Disable CSRF protection
//                 .logout(logout -> {
//                     logout
//                             .logoutUrl("/logout")
//                             .logoutSuccessUrl("/login?logout=true") // Append ?logout=true to URL on success
//                             .deleteCookies("JSESSIONID")
//                             .invalidateHttpSession(true);
//                 });

//         //oauth security config
//         httpSecurity.oauth2Login(oauth ->{
//             oauth.loginPage("/login");
//             oauth.successHandler(handler);
//         });


//         return httpSecurity.build();
//     }





//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
