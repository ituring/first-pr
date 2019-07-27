package xyz.xlong99.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="wiki",schema ="wiki" )
public class WiKiEntity {
    private int wid;
    private String authorId;
    private String topic;
    private int classId;
    private String description;
    private String body;
    private int likeCount;
    private Timestamp createTime;
    private Timestamp updateTime;
    private boolean ifPublic;
    private String tagTemp;
    private String imagesTemp;
    private String likePersonTemp;
    private String[] likePerson;
    private String[] tag;
    private String[] images;
    private boolean ifAddLike;

    @Transient
    public boolean isIfAddLike() {
        return ifAddLike;
    }

    public void setIfAddLike(boolean ifAddLike) {
        this.ifAddLike = ifAddLike;
    }


    @JsonIgnore
    @Basic
    @Column(name = "likePerson")
    public String getLikePersonTemp() {
        return likePersonTemp;
    }

    public void setLikePersonTemp(String likePersonTemp) {
        this.likePersonTemp = likePersonTemp;
    }


    @Transient
    public String[] getLikePerson() {
        return likePerson;
    }

    public void setLikePerson(String[] likePerson) {
        this.likePerson = likePerson;
    }



    @Transient
    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }


    @Transient
    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    @JsonIgnore
    @Basic
    @Column(name = "images")
    public String getImagesTemp() {
        return imagesTemp;
    }

    public void setImagesTemp(String images) {
        this.imagesTemp = images;
    }

    @JsonIgnore
    @Basic
    @Column(name = "tag")
    public String getTagTemp() {
        return tagTemp;
    }

    public void setTagTemp(String tag) {
        this.tagTemp = tag;
    }

    @Id
    @Column(name = "wid")
    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    @Basic
    @Column(name = "authorId")
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "topic")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "classifyId")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "likeCount")
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "createTime",updatable = false,columnDefinition="timestamp default current_timestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime",columnDefinition="timestamp default current_timestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "ifPublic")
    public boolean isIfPublic() {
        return ifPublic;
    }

    public void setIfPublic(boolean ifPublic) {
        this.ifPublic = ifPublic;
    }


}
