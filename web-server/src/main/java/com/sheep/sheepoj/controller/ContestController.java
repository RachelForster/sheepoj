package com.sheep.sheepoj.controller;

import com.github.pagehelper.PageInfo;
import com.sheep.sheepoj.entity.Contest;
import com.sheep.sheepoj.entity.Problem;
import com.sheep.sheepoj.service.ContestService;
import com.sheep.sheepoj.utils.JsonResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContestController {
    @Autowired
    ContestService contestService;
    @RequestMapping("/contests")
    JsonResult<PageInfo<Contest>> findByPage(Integer page, Integer limit)
    {
        return new JsonResult(contestService.findByPage(page,limit));
    }
    @RequestMapping("/contest/{id}")
    JsonResult<Contest> findById(@PathVariable(value = "id") Long id){
        return new JsonResult<>(contestService.findById(id));
    }
    @RequestMapping("/contest/{id}/problemlist")
    JsonResult<List<Problem>> findProblemList(@PathVariable("id") Long id)
    {
        return new JsonResult<>(contestService.findProblemList(id));
    }

}
