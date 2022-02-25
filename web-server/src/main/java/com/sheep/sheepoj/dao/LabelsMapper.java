package com.sheep.sheepoj.dao;

import com.sheep.sheepoj.entity.Labels;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LabelsMapper {
    List<String> findById(Long problem_id);
}
