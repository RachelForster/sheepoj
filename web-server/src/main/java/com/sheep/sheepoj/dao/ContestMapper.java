package com.sheep.sheepoj.dao;
import com.sheep.sheepoj.entity.Contest;
import com.sheep.sheepoj.entity.Problem;
import java.util.List;

public interface ContestMapper{
    List<Contest> findAll(String order);
    Contest findById(Long id);
    List<Problem> findProblemList(Long id);
}
