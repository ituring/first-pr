package xyz.xlong99.service;

import xyz.xlong99.domain.Wiki;

import java.util.List;

/**
 * @author 胡学良
 * @date 2019-05-26 11:05
 */
public interface WikiService {
    /**
     * 通过分类id查询文章
     * @param classifyId 文章分类id
     * @return 文章集合
     */
    List<Wiki> findWikiByClassifyId(int classifyId);

    /**
     * 通过标签id查询文章
     * @param tag 文章标签id
     * @return 文章集合
     */
    List<Wiki> findWikiByTagId(String tag);

    /**
     * 通过标签名字查询id
     * @param name 标签名字
     * @return 标签id
     */
    String getTagId(String name);

    /**
     * 通过分类名字查询id
     * @param name 分类名字
     * @return 分类id
     */
    int getClassifyId(String name);

    /**
     * 通过标签id查询名字
     * @param id 标签id
     * @return 标签名字
     */
    String getTagName(String id);

    /**
     * 通过集合中的标签id查询名字
     * @param list wiki集合
     * @return wiki集合
     */
    List<Wiki> tagNameOfList(List<Wiki> list);

    /**
     * 为集合中的wiki的属性user赋值
     * @param list wiki集合
     * @return wiki集合
     */
    List<Wiki> assignUser(List<Wiki> list);
}
