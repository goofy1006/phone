package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmOdEvaluation;
import java.util.List;

public interface EmOdEvaluationMapper extends BaseDao {
    int deleteByPrimaryKey(String evaluationId);

    int insert(EmOdEvaluation record);

    int insertSelective(EmOdEvaluation record);

    EmOdEvaluation selectByPrimaryKey(String evaluationId);

    int updateByPrimaryKeySelective(EmOdEvaluation record);

    int updateByPrimaryKey(EmOdEvaluation record);

    List<EmOdEvaluation> selectList(EmOdEvaluation record);
}