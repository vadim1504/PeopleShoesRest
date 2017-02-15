package by.training.rest.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@Configuration
@RestController
@RequestMapping(value = "/role")
class RoleController {

    @GetMapping()
    public String getUser(Principal principal){

        UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
        String role="";
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        */
      //  OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(access_token);
        Collection<GrantedAuthority> collection = (Collection<GrantedAuthority>) currentUser.getAuthorities();
        for (GrantedAuthority i: collection){
          role = i.getAuthority();
        }
        return role;
    }
}
