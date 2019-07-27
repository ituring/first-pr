package xyz.xlong99.service;

import xyz.xlong99.domain.User;

/**
 * @author 胡学良
 * @date 2019-05-24 20:33
 */
public interface UserService {
    /**
     * 通过用户ID查询信息
     * @param uid 用户id
     * @return User
     */
    User findUser(int uid);

    /**
     * 修改用户信息（包括头像）
     * @param user 修改后的信息
     */
    void modifyUser( User user);

    /**
     *修改用户头像
     * @param photo 头像名字
     * @param uid 用户id
     */
    void modifyPhoto(String photo,int uid);

    /**
     * 修改用户信息
     * @param user 用户信息
     */
    void modifyMessage(User user);
}
