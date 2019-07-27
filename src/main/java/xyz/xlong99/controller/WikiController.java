package xyz.xlong99.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xlong99.domain.Classify;
import xyz.xlong99.domain.Tag;
import xyz.xlong99.domain.Wiki;
import xyz.xlong99.service.WikiService;

import java.util.List;


/**
 * @author 胡学良
 */
@Controller
@RequestMapping("/wiki")
public class WikiController {
    @Autowired
    private WikiService wikiService;

    /**
     * 直接通过分类id查询文章
     * @param wiki 接受请求id
     * @return
     */
    @RequestMapping("/classifyId")
    @ResponseBody
    public List<Wiki> findWikiByClassifyId(Wiki wiki){
        System.out.println(wiki);
        List<Wiki> list = wikiService.findWikiByClassifyId(wiki.getClassifyId());
        list = wikiService.assignUser(list);
        list = wikiService.tagNameOfList(list);
        return list;
    }

    /**
     * 直接通过标签id查询文章
     * @param wiki 接受请求id
     * @return 文章list集合
     */
    @RequestMapping("/tagId")
    @ResponseBody
    public List<Wiki> fingWikiByTagId(Wiki wiki){
        String string = wiki.getTag().replace("[","").replace("]","").replace(",","#");
        wiki.setTag(string);
        List<Wiki> list = wikiService.findWikiByTagId(wiki.getTag());
        list = wikiService.assignUser(list);
        list = wikiService.tagNameOfList(list);
        return list;
    }

    /**
     * 通过标签名字查询文章
     * @param tag 接受请求名字
     * @return 文章list集合
     */
    @RequestMapping("/tagName")
    @ResponseBody
    public List<Wiki> findWikiByTagName(Tag tag){
        String string1 = tag.getTagname().replace("[","").replace("]","");
        String[] names = string1.split(",");
        StringBuilder ids = new StringBuilder();
        for(int i = 0; i < names.length;i++){
            String num = wikiService.getTagId(names[i]);
            ids.append(num+"#");
        }
        String string2 = ids.deleteCharAt(ids.length()-1).toString();
        List<Wiki> list = wikiService.findWikiByTagId(string2);
        list = wikiService.assignUser(list);
        list = wikiService.tagNameOfList(list);
        return list;
    }

    /**
     * 通过分类名字查询文章
     * @param classify 接受请求名字
     * @return 文章list集合
     */
    @RequestMapping("/classifyName")
    @ResponseBody
    public List<Wiki> findWikiByClassifyName(Classify classify){
        List<Wiki> list =  wikiService.findWikiByClassifyId(wikiService.getClassifyId(classify.getClassifyName()));
        list = wikiService.assignUser(list);
        list = wikiService.tagNameOfList(list);
        return list;
    }
}
