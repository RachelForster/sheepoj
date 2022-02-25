package com.sheep.sheepoj.controller;

import com.sheep.sheepoj.service.LabelsService;
import com.sheep.sheepoj.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LabelsController {
    @Autowired
    LabelsService labelsService;
    @RequestMapping(value = "/labels")
    JsonResult<List<String>> getById(Long problem_id){
        return new JsonResult<>(labelsService.findByProblemId(problem_id));
    }
}
