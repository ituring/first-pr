package xyz.xlong99.domain;
import java.io.Serializable;
/**
 * @author 胡学良
 *
 * 存取用户基本信息
 */
public class User implements Serializable {
    /**
     * 用户id
     */
    private int uid;
    /**
     * 用户名
     */
    private String nickName;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户职业
     */
    private String position;
    /**
     * 用户所在地址
     */
    private String address;
    /**
     * 用户签名
     */
    private String sign;
    /**
     * 用户头像
     */
    private String photo;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", position='" + position + '\'' +
                ", address='" + address + '\'' +
                ", sign='" + sign + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}