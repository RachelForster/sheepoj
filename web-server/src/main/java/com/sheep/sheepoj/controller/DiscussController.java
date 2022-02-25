package com.sheep.sheepoj.controller;

import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.entity.Comment;
import com.sheep.sheepoj.entity.Discuss;
import com.sheep.sheepoj.service.DiscussService;
import com.sheep.sheepoj.utils.JsonResponseStatus;
import com.sheep.sheepoj.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletionException;

@RestController
public class DiscussController {

    @Autowired
    DiscussService discussService;
    @RequestMapping("/discuss")
    JsonResult<PageInfo<Discuss>> findByPage(Integer page, Integer limit, String order){
        return new JsonResult<>(discussService.findByPage(page,limit,order));
    }

    @RequestMapping("/discuss/{id}")
    JsonResult<Discuss> findById(@PathVariable("id") Long id)
    {
        Discuss  discuss=discussService.findById(id);
        System.out.println(discuss);
        return new JsonResult<>(discuss);
    }
    @RequestMapping("discuss/{id}/comments")
    JsonResult<List<Comment>> findComments(@PathVariable("id") Long id)
    {
        return new JsonResult<>(discussService.findCommentsByDisscussId(id));
    }
    @RequestMapping("/discuss/publish")
    JsonResult<String> publishDiscuss(String title,String content)
    {
        try {
            discussService.putOne(title,content);
        }
        catch (Exception e){
            return new JsonResult<>(JsonResponseStatus.UNKNOWN_ERROR.getCode(),"发表失败");
        }
        return new JsonResult<>("发表成功");
    }

    @RequestMapping("/discuss/{id}/likes")
    JsonResult<String> putLike(@PathVariable("id") Long id)
    {
        try {
            discussService.putLike(id);
            return new JsonResult<String>(JsonResponseStatus.SUCCESS.getCode(), "点赞成功");
        }
        catch (Exception e)
        {
            return new JsonResult<>(JsonResponseStatus.UNKNOWN_ERROR.getCode(), e.getMessage());
        }
    }
}
