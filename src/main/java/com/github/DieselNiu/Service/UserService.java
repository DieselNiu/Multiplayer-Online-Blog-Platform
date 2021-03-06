package com.github.DieselNiu.Service;

import com.github.DieselNiu.dao.UserDao;
import com.github.DieselNiu.entity.AvatarGenerator;
import com.github.DieselNiu.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AvatarGenerator avatarGenerator;

    @Inject
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    public void setAvatarGenerator(AvatarGenerator avatarGenerator) {
        this.avatarGenerator = avatarGenerator;
    }

    public String insertNewUser(String username, String password) {
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        param.put("encryptedPassword", encryptedPassword);
        param.put("avatar", avatarGenerator.getRandomAvatar());
        this.userDao.insertNewUser(param);
        return username;
    }

    public User getUserByUsername(String username) {
        return this.userDao.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if ((user = getUserByUsername(username)) == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        String encodedPassword = user.getEncryptedPassword();
        return new org.springframework.security.core.userdetails.User(username, encodedPassword, Collections.emptyList());
    }


}
