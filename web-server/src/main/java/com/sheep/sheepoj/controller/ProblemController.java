package com.sheep.sheepoj.controller;

import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.entity.Problem;
import com.sheep.sheepoj.service.ProblemService;
import com.sheep.sheepoj.utils.JsonResult;
import io.netty.util.internal.SuppressJava6Requirement;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping("/problem/{id}")
    JsonResult<Problem> findById(@PathVariable("id")Long id)
    {
        System.out.println(problemService.findById(id));
        return new JsonResult<>(problemService.findById(id));
    }
    @RequestMapping("/problemset")
    JsonResult<PageInfo<Problem>> findByPage(Integer page, Integer limit, @RequestParam(required = false) String word, @RequestParam(required = false) String tag)
    {
        return new JsonResult<>(problemService.findByPage(page,limit,word,tag));
    }
}
