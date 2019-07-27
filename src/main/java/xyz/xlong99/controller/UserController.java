package xyz.xlong99.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.xlong99.domain.User;
import xyz.xlong99.service.UserService;
import xyz.xlong99.utils.UploadFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 胡学良
 * @date 2019-05-24 20:32
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 通过id查询所有用户信息
     * @param uid 用户ID
     * @return 用户信息
     */
    @RequestMapping("/findAll/{uid}")
    public @ResponseBody User findAll(@PathVariable("uid") int uid){
        User user = userService.findUser(uid);
        return user;
    }

    /**
     * 表单提交信息兵提交给数据库
     * @param user 表单提交信息
     * @param response response对象
     * @param request request对象
     * @param file 获取文件 信息
     * @throws IOException 上传文件时的异常
     */
    @RequestMapping("/save")
    public void modifyUser(User user, HttpServletResponse response, HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        String url = UploadFile.uploadFiles(file);
        user.setPhoto(url);
        userService.modifyUser(user);
        response.sendRedirect(request.getContextPath()+"/user/findAll/"+user.getUid());
    }

    /**
     * 修改用户头像
     * @param user 存取用户id
     * @param file 文件上传
     * @param request request对象
     * @param response response对象
     * @throws Exception 文件上传时的异常
     */
    @RequestMapping("/modifyPhoto")
    public void modifyPhoto(User user ,@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {
        String url = UploadFile.uploadFiles(file);
        userService.modifyPhoto(url,user.getUid());
        response.sendRedirect(request.getContextPath()+"/user/findAll/"+user.getUid());
    }

    /**
     * 修改用户基本信息
     * @param user 用户信息
     * @param response response对象
     * @param request request对象
     * @throws IOException 文件上传的异常
     */
    @RequestMapping("/modifyMessage")
    public void modifyMessage(User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        userService.modifyMessage(user);
        response.sendRedirect(request.getContextPath()+"/user/findAll/"+user.getUid());
    }
}

