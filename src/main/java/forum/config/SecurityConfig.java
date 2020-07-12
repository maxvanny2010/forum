package forum.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * SecurityConfig.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/18/2020
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * field a data source.
     */
    private final DataSource ds;

    public SecurityConfig(@Qualifier("dataSource") final DataSource aDs) {
        this.ds = aDs;
    }

    @Override
    protected final void configure(final AuthenticationManagerBuilder auth)
            throws Exception {
        final PasswordEncoder pass = this.passwordEncoder();
        final String users = "select username, password, enable from users where username=?";
        final String authority = "select u.username, a.authority from users u, authorities a"
                + " where u.authority_id = a.id_authority and username=?";
        auth.jdbcAuthentication()
                .dataSource(this.ds)
                .passwordEncoder(pass)
                .usersByUsernameQuery(users)
                .authoritiesByUsernameQuery(authority);
    }

    /**
     * Method to get.
     *
     * @return encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected final void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/forum", "/login", "/registration", "/post")
                .permitAll()
                .antMatchers("/**").authenticated()
                .antMatchers("/cabinet/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/cabinet/admin/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/forum")
                .failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable();
    }

    @Override
    public final void configure(final WebSecurity web) {
        web.ignoring()
                .antMatchers("/resources/", "/js/message.js");
    }
}
