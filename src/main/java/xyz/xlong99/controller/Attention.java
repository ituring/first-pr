package xyz.xlong99.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xlong99.dao.AttentionDao;
import xyz.xlong99.domain.AttentionEntity;

import java.util.List;

@Controller
@RequestMapping(value = "/attention")
public class Attention {

    @Autowired
    private AttentionDao attentionDao;

    @RequestMapping(value = "/send")

    public void send(@RequestBody AttentionEntity attentionEntity){
        attentionDao.send(attentionEntity);
    }

    @RequestMapping(value = "/getAttention")
    @ResponseBody
    public List<AttentionEntity> getAttention(@RequestBody AttentionEntity attentionEntity){
        List<AttentionEntity> result= attentionDao.getAttentionByReceiver(attentionEntity);
        return result;
    }

    @RequestMapping(value = "/delete")
    public void delete(@RequestBody AttentionEntity attention){
        attentionDao.delete(attention);
    }
}
