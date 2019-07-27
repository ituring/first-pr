package xyz.xlong99.domain;

/**
 * @author 胡学良
 * 存取分类信息
 */
public class Classify {
    /**
     * 分类id
     */
    private int classifyId;
    /**
     * 分类名字
     */
    private String classifyName;

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}
