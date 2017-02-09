package by.training.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Configuration
@RestController
@RequestMapping(value = "/role")
class RoleController {

    @Autowired
    public TokenStore tokenStore;

    @GetMapping(params = {"access_token"})
    public String getUser(@RequestParam(value = "access_token") String  access_token){
        String role="";
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();*/
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(access_token);
        Collection<GrantedAuthority> collection = oAuth2Authentication.getAuthorities();
        for (GrantedAuthority i: collection){
          role = i.getAuthority();
        }
        return role;
    }
}
