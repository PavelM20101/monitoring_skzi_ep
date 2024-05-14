package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.configuration.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService.Impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    @Autowired
    private SuccessUserHandler successUserHandler;

    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
//    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password(bCryptPasswordEncoder.encode("userPass"))
//                .roles("USER")
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password(bCryptPasswordEncoder.encode("adminPass"))
//                .roles("USER", "ADMIN")
//                .build());
//        return manager;
//    }
    @Qualifier("userServiceImpl")
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    //    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers("/login", "/").permitAll();
                    authorizeRequests.anyRequest().permitAll();
                })
                .formLogin(formLogin -> {
                    formLogin.successHandler(successUserHandler);
                    formLogin.permitAll();
                })
                .logout(logout -> logout.permitAll());
        //        http
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers("/login/**").permitAll()
//                                .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
