package xyz.xlong99.domain;

/**
 * @author 胡学良
 * 存取标签信息
 */
public class Tag {
    /**
     * 标签id
     */
    private String tagid;
    /**
     * 标签名字
     */
    private String tagname;

    public String  getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
