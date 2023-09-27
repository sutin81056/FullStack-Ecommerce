package com.example.mydemo.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

// 預設logout邏輯:
//        1.註銷該 HTTP Session (非常重要)
//        2.清除已設定的記得我驗證
//        3.清空 SecurityContextHolder
//        4.重新導向到 /login?logout
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {



//    @Autowired
//    private DataSource dataSource;
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        String sql="SELECT username,password FROM frontend_user WHERE username=?";
//        String authorsql="SELECT username,role FROM frontend_user WHERE username=?";
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(sql)
//                .authoritiesByUsernameQuery(authorsql)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(request -> {
                    request
                            .antMatchers("/", "/login").permitAll()
                            .antMatchers("/api/product/**").permitAll()
                            .antMatchers("/css/**", "/js/**", "/images/**", "/api/**", "/font-awesome/**").permitAll()
                            .antMatchers("/admin/**").permitAll()
                            .requestMatchers(
                                    new AntPathRequestMatcher("/user/login", HttpMethod.POST.toString()),
                                    new AntPathRequestMatcher("/user/register", HttpMethod.POST.toString())
                            ).permitAll()

                            .anyRequest().authenticated();
                });
        return http.build();
    }


//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/admin/**")
//                    .hasAuthority("ROLE_ADMIN")
//                    .antMatchers("/user/**")
//                    .hasAuthority("ROLE_USER")
//
//                    .antMatchers("/","/login")
//                    .permitAll()
//
//                    .anyRequest()
//                    .authenticated()
//
//                    .and()
//                    .csrf().disable()
//
//                .formLogin() //自定義登入頁面
//                    .loginPage("/login")
//                    .failureHandler(authenticationFailureHandler())
//                    .permitAll()
//                    .loginProcessingUrl("/login")
//                    .defaultSuccessUrl("/admin/lunchAllProductsPage")
//
//                        .and()
//
//                .logout()
//                    .logoutSuccessHandler(logoutSuccessHandler())
//                    .permitAll();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").authenticated()
//                .antMatchers(HttpMethod.GET).permitAll()
//                .antMatchers(HttpMethod.POST, "/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .formLogin();
//    }

    // 後端登入登出用
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomAuthenticationFailureHandler();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new CustomLogoutSuccessHandler();
//    }

}



//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//        //測試帳密
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        //設定自定義密碼
//        String rawpassword = "user";
//        //設定變數，這個變數存放加密完成後的密碼
//        String encodepassword = encoder.encode(rawpassword);
//        String encodesql="UPDATE users SET password=:userpassword WHERE username='user'";
//        Map<String, Object> map = new HashMap<>();
//        map.put("userpassword",encodepassword);
//
//        namedParameterJdbcTemplate.update(encodesql,map);


//    @Bean
//    public LogoutSuccessHandler customLogoutSuccessHandler() {
//        return new CustomLogoutSuccessHandler();
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // TODO
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").authenticated()
//                .antMatchers(HttpMethod.GET).permitAll()
//                .antMatchers(HttpMethod.POST, "/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .formLogin();
//    }


//    @Autowired
//    @Qualifier("myUsersDetailService")
//    private UserDetailsService userDetailsService;


//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers(HttpMethod.GET, "/**").permitAll()  // Allow public access
//                        .anyExchange().authenticated()  // Require authentication for other endpoints
//                )
//                .httpBasic();  // Use HTTP Basic authentication
//
//        return http.build();
//    }
//}


//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange().anyExchange().permitAll();
//        return http.build();
//    }
//}

//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/users/**").authenticated()
//                .antMatchers(HttpMethod.GET).permitAll()
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .formLogin();
//    }
//}

//package com.example.mydemo.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
//    }
//}

//讓h2的console可以過security
//dangerzone
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/h2-ui/**");
//    }
