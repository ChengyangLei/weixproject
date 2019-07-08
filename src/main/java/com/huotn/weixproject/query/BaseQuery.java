package com.huotn.weixproject.query;

import lombok.Data;

/**
 * 基础查询对象
 */
@Data
public class BaseQuery {
    private String keyword;//关键字
    //有公共属性-分页
    private Integer page = 1; //当前页
    private Integer rows = 10; //每页显示多少条  TODO 注：layui是limit


}

