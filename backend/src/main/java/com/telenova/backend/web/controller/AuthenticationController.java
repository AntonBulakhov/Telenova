package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.UserEntity;
import com.telenova.backend.security.TokenProvider;
import com.telenova.backend.security.dto.AuthToken;
import com.telenova.backend.service.UserService;
import com.telenova.backend.web.dto.SafeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.telenova.backend.constants.UserConstants.BLOCKED_USER_STATUS_ID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;
    private UserService userService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> register(@RequestBody UserEntity loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getLogin(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> regNewUser(@RequestBody UserEntity user) {
        UserEntity userAuth = copyUser(user);
        if (!userService.saveUser(user)) return ResponseEntity.badRequest().build();

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuth.getLogin(),
                        userAuth.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    private UserEntity copyUser(UserEntity user) {
        UserEntity copy = new UserEntity();
        copy.setLogin(user.getLogin());
        copy.setPassword(user.getPassword());
        return copy;
    }

    @GetMapping("/user")
    public ResponseEntity<SafeUser> authUser(Principal userInfo) {
        SafeUser safeUser = userService.getSafeUserByLogin(userInfo.getName());
        if(safeUser.getUserStatus().getId() == BLOCKED_USER_STATUS_ID){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(safeUser);
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setTokenProvider(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
