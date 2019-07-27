package xyz.xlong99.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xyz.xlong99.domain.User;

/**
 * @author 胡学良
 * @date 2019-05-24 20:30
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * 查询指定用户所有信息
     * @param uid 用户id,查询依据
     * @return User 用户所有信息
     */
    @Select("select * from personaldata where uid = #{uid}")
    User findAll(int uid);

    /**
     * 根据表单信息修改用户信息
     * @param user 修改值
     * @return User 修改之后的用户信息
     */
    @Update("update personaldata set nickName = #{nickName},sex = #{sex},position = #{position},address = #{address},sign = #{sign},photo = #{photo} where uid = #{uid}")
    void modifyUser(User user);

    /**
     *修改用户头像
     * @param photo 用户头像
     * @param uid 用户ID
     */
    @Update("update personaldata set photo = #{photo} where uid = #{uid}")
    void modifyPhoto(@Param("photo") String photo, @Param("uid") int uid);

    /**
     * 修改用户基本信息
     * @param user 用户信息
     */
    @Update("update personaldata set nickName = #{nickName},sex = #{sex},position = #{position},address = #{address},sign = #{sign} where uid = #{uid}")
    void modifyMessage(User user);

}
