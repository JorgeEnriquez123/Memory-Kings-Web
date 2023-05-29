package pe.com.apiciberelectrik.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import pe.com.apiciberelectrik.service.impl.gestion.UsuarioServiceImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SeguridadConfig{

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    /*protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/principal")
                    .permitAll()
                    .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ? VENTAS ---
        RequestMatcher ventas1 = new AntPathRequestMatcher("/venta/**");
        RequestMatcher ventas2 = new AntPathRequestMatcher("/cliente/**");

        // * VENDEDOR
        RequestMatcher vendedor1 = new AntPathRequestMatcher("/reclamo/**");

        // * CAJERO ESPECIAL
        RequestMatcher cajero1 = new AntPathRequestMatcher("/venta/control/**");
        // ------------

        // ? ALMACEN ----
        RequestMatcher jefeAlmacenyVendedor = new AntPathRequestMatcher("/producto/**");

        // * JEFE DE ALMACEN ESPECIAL
        RequestMatcher jefealmacen1 = new AntPathRequestMatcher("/producto/control/**");

        // * JEFE DE ALMACEN
        RequestMatcher jefealmacen2 = new AntPathRequestMatcher("/categoria/**");
        RequestMatcher jefealmacen3 = new AntPathRequestMatcher("/proveedor/**");
        RequestMatcher jefealmacen4 = new AntPathRequestMatcher("/productoproveedor/**");
        RequestMatcher jefealmacen5 = new AntPathRequestMatcher("/ordencompra/**");
        RequestMatcher jefealmacen6 = new AntPathRequestMatcher("/guiaentrada/**");
        // ----------

        // ? ADMIN -----
        RequestMatcher admin1 = new AntPathRequestMatcher("/usuario/**");
        RequestMatcher admin2 = new AntPathRequestMatcher("/rol/**");
        // -----------

        return http
                .authorizeRequests()
                .requestMatchers(cajero1).hasAnyAuthority("Cajero", "Administrador")

                .requestMatchers(ventas1).hasAnyAuthority("Cajero", "Vendedor", "Administrador")
                .requestMatchers(ventas2).hasAnyAuthority("Cajero", "Vendedor", "Administrador")
                .requestMatchers(vendedor1).hasAnyAuthority("Vendedor", "Administrador")

                .requestMatchers(jefealmacen1).hasAnyAuthority("Jefe de almacén", "Administrador")
                .requestMatchers(jefeAlmacenyVendedor).hasAnyAuthority("Jefe de almacén", "Vendedor", "Administrador")
                .requestMatchers(jefealmacen2).hasAnyAuthority("Jefe de almacén", "Administrador")
                .requestMatchers(jefealmacen3).hasAnyAuthority("Jefe de almacén", "Administrador")
                .requestMatchers(jefealmacen4).hasAnyAuthority("Jefe de almacén", "Administrador")
                .requestMatchers(jefealmacen5).hasAnyAuthority("Jefe de almacén", "Administrador")
                .requestMatchers(jefealmacen6).hasAnyAuthority("Jefe de almacén", "Administrador")

                .requestMatchers(admin1).hasAuthority("Administrador")
                .requestMatchers(admin2).hasAuthority("Administrador")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/noaccess")
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler())
                .permitAll()
                .and()
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/principal?logeoexitoso");
        return successHandler;
    }

}