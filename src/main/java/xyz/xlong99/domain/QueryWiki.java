package xyz.xlong99.domain;

import javax.persistence.Entity;

@Entity
public class QueryWiki {
    private int wid;
    private String uid;
    private String authorId;






    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }


}
