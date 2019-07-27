package xyz.xlong99.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xlong99.dao.UserDao;
import xyz.xlong99.domain.User;
import xyz.xlong99.service.UserService;

/**
 * @author 胡学良
 * @date 2019-05-24 20:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUser(int uid) {
        return userDao.findAll(uid);
    }

    @Override
    public void modifyUser(User user) {
        userDao.modifyUser(user);
    }

    @Override
    public void modifyPhoto(String photo, int uid) {
        userDao.modifyPhoto(photo,uid);
    }

    @Override
    public void modifyMessage(User user) {
        userDao.modifyMessage(user);
    }
}
