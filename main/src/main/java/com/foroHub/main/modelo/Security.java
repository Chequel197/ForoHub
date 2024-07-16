package com.foroHub.main.modelo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Security {

    @Bean
    // la siguente funcion se encarga de redirigir las las credenciales a los diferentes filtros de seguridad
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.build();
    }


    //como su nombre lo dice se encarga de manejar la autenticacion
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }

    //Aqui es el ultimo filtro donde se verifican las credenciales y se consultan a la base de datos
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    //aqui se encarga de traer los usuarios de la base de datos para poder verificarlos
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withUsername("juan")
                .password("root")
                .roles("ADMIN")
                .authorities("Read", "Create")
                //por el momento se estan creando manualmente
                //sin necesitadad de conectarse a una base de datos
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    //Aqui es donde se encripta las credenciales en formato hash
    @Bean
    public PasswordEncoder passwordEncoder(){
return NoOpPasswordEncoder.getInstance(); //de esta forma no se encriptan las credenciales se usa mas para testeos
    }

}
