package com.xu.community.controller;

import com.xu.community.entity.DiscussPost;
import com.xu.community.entity.Page;
import com.xu.community.entity.User;
import com.xu.community.service.DiscussPostService;
import com.xu.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

  


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用前，springmvc会自动实例化Model和Page，并将Page注入Model，所以在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> selectDiscussPosts = discussPostService.selectDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if(selectDiscussPosts!=null){
            for (DiscussPost post : selectDiscussPosts) {
                Map<String,Object> map=new HashMap();
                map.put("post",post);
                User user=userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";

    }
    @GetMapping("/error")
    public String getErrorPage(){
        return "/error/500";
    }

}
