package com.telenova.backend.service.impl;

import com.telenova.backend.converter.UserToSafeUserConverter;
import com.telenova.backend.database.entity.RoleEntity;
import com.telenova.backend.database.entity.ServiceEntity;
import com.telenova.backend.database.entity.UserEntity;
import com.telenova.backend.database.entity.UserStatusEntity;
import com.telenova.backend.database.repository.RoleEntityRepository;
import com.telenova.backend.database.repository.ServiceEntityRepository;
import com.telenova.backend.database.repository.UserEntityRepository;
import com.telenova.backend.database.repository.UserStatusEntityRepository;
import com.telenova.backend.service.ServService;
import com.telenova.backend.service.UserService;
import com.telenova.backend.web.dto.SafeUser;
import com.telenova.backend.web.dto.UserWithSumDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.telenova.backend.constants.InitialAdminConstants.ADMIN_EMAIL;
import static com.telenova.backend.constants.InitialAdminConstants.ADMIN_LOGIN;
import static com.telenova.backend.constants.InitialAdminConstants.ADMIN_NAME;
import static com.telenova.backend.constants.InitialAdminConstants.ADMIN_PASSWORD;
import static com.telenova.backend.constants.InitialAdminConstants.ADMIN_SURNAME;
import static com.telenova.backend.constants.InitialAdminConstants.SUPER_ROLE_ID;
import static com.telenova.backend.constants.UserConstants.ACTIVE_USER_STATUS_ID;
import static com.telenova.backend.constants.UserConstants.CLIENT_USER_ROLE_ID;
import static com.telenova.backend.constants.UserConstants.SUPER_USER_ROLE_ID;

@Service("customUserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserEntityRepository userRepository;
    private UserStatusEntityRepository userStatusRepository;
    private RoleEntityRepository roleRepository;
    private UserToSafeUserConverter userConverter;
    private BCryptPasswordEncoder bCrypt;
    private ServiceEntityRepository serviceEntityRepository;

    @Override
    public SafeUser getSafeUserByLogin(String login) {
        return userConverter.convert(getUserByLogin(login));
    }

    @Override
    public Boolean saveUser(UserEntity userEntity) {
        userEntity.setPassword(bCrypt.encode(userEntity.getPassword()));

        List<UserEntity> existingUsers = userRepository.getAllByLoginOrEmail(userEntity.getLogin(), userEntity.getEmail());
        if (CollectionUtils.isEmpty(existingUsers)) {
            return userRepository.save(userEntity) != null;
        } else {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = getUserByLogin(login);
        return new User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().toUpperCase()));
        return authorities;
    }

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserStatusEntity> getAllStatuses() {
        return (List<UserStatusEntity>) userStatusRepository.findAll();
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return (List<RoleEntity>) roleRepository.findAll();
    }

    @Override
    public List<UserWithSumDto> getAllUserByRoleId(Integer id) {
        List<UserWithSumDto> userWithSumDtos = new ArrayList<>();

        RoleEntity role = roleRepository.findById(id).get();
        List<UserEntity> users = userRepository.getAllByRole(role);

        List<SafeUser> safeUsers = userConverter.convert(users);
        for (SafeUser user : safeUsers) {
            float sum = 0;

            if (user.getRole().getId() == CLIENT_USER_ROLE_ID) {
                List<ServiceEntity> services = serviceEntityRepository.findAllByUserId(user.getId());
                for (ServiceEntity serviceEntity : services) {
                    if (serviceEntity.getBalance().getValue() < 0) {
                        sum += serviceEntity.getBalance().getValue();
                    }
                }
            }
            UserWithSumDto userWithSumDto = new UserWithSumDto();
            userWithSumDto.setUser(user);
            userWithSumDto.setSum(sum);

            userWithSumDtos.add(userWithSumDto);
        }

        return userWithSumDtos;
    }

    @Override
    public Boolean setUserStatus(UserEntity userEntity) {
        UserEntity user = userRepository.findById(userEntity.getId()).get();
        user.setUserStatus(userEntity.getUserStatus());
        user = userRepository.save(user);
        return user.getUserStatus().getId() == userEntity.getUserStatus().getId();
    }

    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id).get();

        if (CLIENT_USER_ROLE_ID == userEntity.getRole().getId() || SUPER_USER_ROLE_ID == userEntity.getRole().getId()) {
            return;
        }

        userRepository.deleteById(id);
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        UserEntity existingUser = userRepository.getByLogin(ADMIN_LOGIN);

        RoleEntity roleEntity = roleRepository.findById(SUPER_ROLE_ID).get();
        UserEntity user = new UserEntity();

        if (existingUser != null) {
            user.setId(existingUser.getId());
            userRepository.delete(existingUser);
        }

        user.setRole(roleEntity);
        user.setLogin(ADMIN_LOGIN);
        user.setPassword(ADMIN_PASSWORD);
        user.setEmail(ADMIN_EMAIL);
        user.setName(ADMIN_NAME);
        user.setSurname(ADMIN_SURNAME);

        UserStatusEntity status = userStatusRepository.findById(ACTIVE_USER_STATUS_ID).get();
        user.setUserStatus(status);
        saveUser(user);
    }

    @Autowired
    public void setUserRepository(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserStatusRepository(UserStatusEntityRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Autowired
    public void setRoleRepository(RoleEntityRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserConverter(UserToSafeUserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Autowired
    public void setbCrypt(BCryptPasswordEncoder bCrypt) {
        this.bCrypt = bCrypt;
    }

    @Autowired
    public void setServiceEntityRepository(ServiceEntityRepository serviceEntityRepository) {
        this.serviceEntityRepository = serviceEntityRepository;
    }
}
