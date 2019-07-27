package xyz.xlong99.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xlong99.dao.WikiDaoHxl;
import xyz.xlong99.domain.Wiki;
import xyz.xlong99.service.UserService;
import xyz.xlong99.service.WikiService;

import java.util.List;

@Service("wikiService")
public class WikiServiceImpl implements WikiService {

    @Autowired
    private WikiDaoHxl wikiDaoHxl;

    @Autowired
    private UserService userService;


    @Override
    public List<Wiki> findWikiByClassifyId(int classifyId) {
        return wikiDaoHxl.findWikiByClassifyId(classifyId);
    }

    @Override
    public List<Wiki> findWikiByTagId(String tag) {
        return wikiDaoHxl.findWikiByTagId(tag);
    }

    @Override
    public String getTagId(String name) {
        return wikiDaoHxl.getTagId(name);
    }

    @Override
    public int getClassifyId(String name) {
        return wikiDaoHxl.getClassifyId(name);
    }

    @Override
    public String getTagName(String id) {
        return wikiDaoHxl.getTagName(id).getTagname();
    }

    @Override
    public List<Wiki> tagNameOfList(List<Wiki> list) {
        for(Wiki wiki : list){
            String tagId = wiki.getTag();
            String[] ids = tagId.split("#");
            StringBuilder names = new StringBuilder();
            for(int i = 0; i < ids.length; i++){
                names.append(getTagName(ids[i])).append(",");
            }
            wiki.setTag(names.deleteCharAt(names.length()-1).toString());
        }
        return list;
    }

    @Override
    public List<Wiki> assignUser(List<Wiki> list) {
        for(Wiki wiki : list){
            wiki.setUser(userService.findUser(wiki.getAuthorId()));
        }
        return list;
    }

}
