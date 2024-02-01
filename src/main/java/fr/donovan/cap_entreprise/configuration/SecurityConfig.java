package fr.donovan.cap_entreprise.configuration;

import fr.donovan.cap_entreprise.mapping.UrlRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(UrlRoute.URL_LOGIN).permitAll()
                                .requestMatchers(UrlRoute.URL_REGISTER).permitAll()
                                .requestMatchers(UrlRoute.URL_LOGOUT).permitAll()
                                .requestMatchers(UrlRoute.URL_REVIEW_MODERATE + "/**").hasAuthority("ROLE_MODERATOR")
                                .requestMatchers(UrlRoute.URL_REVIEW + "/**").authenticated()
                                .requestMatchers(HttpMethod.POST, UrlRoute.URL_REVIEW + "/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/**").hasAuthority("ROLE_MODERATOR")
                                .requestMatchers(HttpMethod.PUT, "/**").hasAuthority("ROLE_MODERATOR")
                                .requestMatchers(UrlRoute.URL_ADMIN+"/**").hasAuthority("ROLE_MODERATOR")
                                .requestMatchers(UrlRoute.URL_GAME + "/**").authenticated()
                                .requestMatchers(UrlRoute.URL_GENRE + "/**").authenticated()
                                .requestMatchers(UrlRoute.URL_BUSINESSMODEL + "/**").authenticated()
                                .requestMatchers(UrlRoute.URL_CLASSIFICATION + "/**").authenticated()
                                .requestMatchers(UrlRoute.URL_PLATFORM + "/**").authenticated()
                                .requestMatchers(UrlRoute.URL_PUBLISHER + "/**").authenticated()
                                .requestMatchers("/**").permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage(UrlRoute.URL_LOGIN)
                                .defaultSuccessUrl(UrlRoute.URL_REVIEW)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl(UrlRoute.URL_LOGIN)
                                .permitAll()
                );

        return http.build();
    }

}
