package org.jeecg.common.aspect.annotation;

import org.apache.poi.ss.formula.functions.T;
import org.jeecg.common.exception.JeecgBootException;

import java.util.List;

/**
 * @Description 公共部分
 * @Since 2024-11-08
 * */
public interface BaseImport{

    /**
     * 批量初始化
     * */
    void initList(List<T> list) throws JeecgBootException;

    /**
     * 统一校验数据（定制）
     * */
    void validList(List<T> list) throws JeecgBootException;


    /**
     * 批量保存
     * */
    void saveList(List<T> list) throws JeecgBootException;

}