package com.telenova.backend.converter;

import com.telenova.backend.database.entity.UserEntity;
import com.telenova.backend.web.dto.SafeUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToSafeUserConverter implements Converter<UserEntity, SafeUser> {

    public List<SafeUser> convert(List<UserEntity> userEntities) {
        List<SafeUser> safeUsers = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            safeUsers.add(convert(userEntity));
        }
        return safeUsers;
    }

    @Override
    public SafeUser convert(UserEntity userEntity) {
        SafeUser safeUser = new SafeUser();
        safeUser.setId(userEntity.getId());
        safeUser.setUserStatus(userEntity.getUserStatus());
        safeUser.setEmail(userEntity.getEmail());
        safeUser.setName(userEntity.getName());
        safeUser.setSurname(userEntity.getSurname());
        safeUser.setRole(userEntity.getRole());
        return safeUser;
    }
}
