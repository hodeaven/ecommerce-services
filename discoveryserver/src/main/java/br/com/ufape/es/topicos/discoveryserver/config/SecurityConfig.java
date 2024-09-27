package br.com.ufape.es.topicos.discoveryserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SecurityConfig {

    @Value("${eureka.username}")
    private String username;

    @Value("${eureka.password}")
    private String password;

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        @SuppressWarnings("deprecation")
        UserDetails user = User.withDefaultPasswordEncoder()
            .username(username)
            .password(password)
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desabilitar CSRF para simplificar chamadas externas
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/eureka/**").permitAll()  // Permitir acessos ao Eureka
                .anyRequest().authenticated()               // Exigir autenticação para outros endpoints
            )
            .httpBasic(withDefaults());
        return http.build();
    }
}