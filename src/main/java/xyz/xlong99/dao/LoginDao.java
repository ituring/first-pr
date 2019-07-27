package xyz.xlong99.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author xlong
 */
@Mapper
@Repository
public interface LoginDao {
    /**
     * 用户名校验
     * @param loginname 要校验的用户名
     * @return 用户存在返回true
     */
    @Select("select count(*) from user where loginname=#{loginname}")
    boolean checkLoginname(@Param("loginname") String loginname);
    /**
     *密码校验
     * @param loginname 要校验的用户名
     * @param password 要校验的密码
     * @return 密码正确返回true
     */
    @Select("select count(*) from user where loginname=#{loginname} and password=#{password}")
    boolean checkpassword(@Param("loginname") String loginname,@Param("password")String password);
    /**
     * 修改密码
     * @param loginname 要修改密码的用户名
     * @param newpassword 新密码
     */
    @Update("update user set password = #{newpassword} where loginname = #{loginname}")
    void changePassword(@Param("loginname") String loginname,@Param("newpassword")String newpassword);
    /**
     * 注册和保存用户
     * @param loginname 用户名
     * @param password 密码
     */
    @Insert("insert into user (loginname,password) values (#{loginname},#{password})")
    void savaUser(@Param("loginname") String loginname,@Param("password")String password);
}
