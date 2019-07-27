package xyz.xlong99.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 胡学良
 * 存取文章信息
 */
public class Wiki implements Serializable {
    /**
     * 文章id
     */
    private int wid;
    /**
     * 作者id
     */
    private int authorId;
    /**
     * 文章标题
     */
    private String topic;
    /**
     * 文章标签id
     */
    private String tag;
    /**
     * 文章分类id
     */
    private int classifyId;
    /**
     * 文章描述
     */
    private String description;
    /**
     * 文章的markdown内容
     */
    private String body;
    /**
     * 文章点赞数
     */
    private int likeCount;
    /**
     * 文章发布时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    /**
     * 文章最后更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp updateTime;
    /**
     * 文章是否公开
     */
    private boolean ifPublic;
    /**
     * 用户基本信息
     */
    private User user;

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isIfPublic() {
        return ifPublic;
    }

    public void setIfPublic(boolean ifPublic) {
        this.ifPublic = ifPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wiki{" +
                "wid=" + wid +
                ", authorId=" + authorId +
                ", topic='" + topic + '\'' +
                ", tag='" + tag + '\'' +
                ", classifyId=" + classifyId +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", likeCount=" + likeCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ifPublic=" + ifPublic +
                ", user=" + user +
                '}';
    }
}
