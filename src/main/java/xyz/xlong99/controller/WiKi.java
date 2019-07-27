package xyz.xlong99.controller;


import xyz.xlong99.dao.WiKiDao;
import xyz.xlong99.domain.QueryWiki;
import xyz.xlong99.domain.WiKiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/wiki",produces = "application/json;charset=UTF-8")
public class WiKi {


    @Autowired
    private WiKiDao wiKiDao;

    @RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8")
    public void upload(@RequestBody WiKiEntity wiKiEntity) {
        wiKiDao.upload(wiKiEntity);
    }

    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    public void delete(@RequestBody QueryWiki queryWiki) {
        wiKiDao.delete(queryWiki);
    }

    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public void update(@RequestBody WiKiEntity wiKiEntity) {
        wiKiDao.update(wiKiEntity);
    }

    @RequestMapping(value = "/getWikiById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getWikiById(@RequestBody QueryWiki queryWiki) {

        Object result = wiKiDao.getWiki(queryWiki);
        return result;

    }

    @RequestMapping(value = "/getWikisByAuthorId", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getWikisByAuthorId(@RequestBody QueryWiki queryWiki) {
        List<WiKiEntity> result = wiKiDao.getWikisByAuthorId(queryWiki);
        return result;
    }

    @RequestMapping(value = "/getAllPublicWikis",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<WiKiEntity> getAllPublicWikis(@RequestBody QueryWiki queryWiki){
        List<WiKiEntity> result = wiKiDao.getAllPublicWikis(queryWiki);
        return result;
    }

    @RequestMapping(value = "/addLike")
    public void addLike(@RequestBody QueryWiki queryWiki){
        wiKiDao.addLike(queryWiki);
    }


}
