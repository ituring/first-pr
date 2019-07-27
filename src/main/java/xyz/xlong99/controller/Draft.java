package xyz.xlong99.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xlong99.dao.DraftDao;
import xyz.xlong99.domain.DraftEntity;

import java.util.List;

@Controller
@RequestMapping(value = "/draft")
public class Draft {

    @Autowired
    private DraftDao draftDao;

    @RequestMapping(value = "/upload")
    public void upload(@RequestBody DraftEntity draftEntity){
        draftDao.upload(draftEntity);
    }

    @RequestMapping(value = "/update")
    public void update(@RequestBody DraftEntity draftEntity ){
        draftDao.update(draftEntity);
    }

    @RequestMapping(value = "/delete")
    public void delete(@RequestBody DraftEntity draftEntity){
        draftDao.delete(draftEntity);
    }

    @RequestMapping(value = "/getDraftsByAuthorId")
    @ResponseBody
    public List<DraftEntity>  getDraftsByAuthorId(@RequestBody DraftEntity draftEntity){
        List<DraftEntity> result = draftDao.getDraftsByAuthorId(draftEntity);
        return result;
    }

    @RequestMapping(value = "/getDraftById")
    @ResponseBody
    public Object getDraftById(@RequestBody DraftEntity draftEntity){
        Object result = draftDao.getDraftById(draftEntity);
        return result;
    }
}
