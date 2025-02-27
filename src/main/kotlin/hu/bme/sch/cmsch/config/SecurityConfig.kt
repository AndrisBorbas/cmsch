package hu.bme.sch.cmsch.config

import hu.bme.sch.cmsch.model.RoleType
import hu.gerviba.authsch.AuthSchAPI
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@EnableWebSecurity
@Configuration
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/", "/control/loggedin", "/control/login", "/control/logged-out", "/api/events",
                        "/api/events/**", "/api/extra-page/**", "/api/version", "/style.css", "/control/test-user",
                        "/images/**", "/js/**", "/files/**", "/admin/logout", "/cdn/events/**",
                        "/api/warning", "/countdown", "/control/logout", "/control/test", "/api/achievement", "/control/open-site",
                        "/api/qr/**")
                    .permitAll()

                .antMatchers(
                        "/api/profile",
                        "/api/riddle/**",
                        "/control/entrypoint",
                        "/cdn/achievement/**",
                        "/api/token-after-login",
                        "/api/token/**",
                        "/api/riddle/**")
                    .hasAnyRole(RoleType.BASIC.name, RoleType.STAFF.name, RoleType.ADMIN.name, RoleType.SUPERUSER.name)

                .antMatchers("/admin/**", "/cdn/**")
                    .hasAnyRole(RoleType.STAFF.name, RoleType.ADMIN.name, RoleType.SUPERUSER.name)

                .and().formLogin().disable()
                .exceptionHandling().accessDeniedPage("/403")
        http.csrf().ignoringAntMatchers("/api/**", "/admin/sell/**")
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.eraseCredentials(true)
    }

    @Bean
    @ConfigurationProperties(prefix = "authsch")
    fun authSchApi(): AuthSchAPI {
        return AuthSchAPI()
    }
}
