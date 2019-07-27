package xyz.xlong99.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xlong99.dao.CommentDao;
import xyz.xlong99.domain.CommentEntity;

import java.util.List;

@Controller
@RequestMapping(value = "/comment")
public class Comment {
    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "/upload")
    public void upload(@RequestBody CommentEntity commentEntity){
        commentDao.upload(commentEntity);
    }

    @RequestMapping(value = "/getCommentByCid")
    @ResponseBody
    public List<CommentEntity> getCommentByCid(@RequestBody CommentEntity commentEntity){
        List<CommentEntity> result = commentDao.getCommentByCid(commentEntity);
        return result;
    }

    @RequestMapping(value = "/getCommentByWid")
    @ResponseBody
    public List<CommentEntity> getCommentByWid(@RequestBody CommentEntity commentEntity){
        List<CommentEntity> result = commentDao.getCommentByWid(commentEntity);
        return result;
    }

    @RequestMapping(value = "/delete")
    public void delete(@RequestBody CommentEntity commentEntity){

        commentDao.delete(commentEntity);
    }

}
