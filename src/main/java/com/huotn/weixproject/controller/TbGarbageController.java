package com.huotn.weixproject.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huotn.weixproject.service.ITbGarbageService;
import com.huotn.weixproject.bean.TbGarbage;
import com.huotn.weixproject.query.TbGarbageQuery;
import com.huotn.weixproject.util.AjaxResult;
import com.huotn.weixproject.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tbGarbage")
public class TbGarbageController {
    @Autowired
    public ITbGarbageService tbGarbageService;

    /**
     * 保存、修改 【区分id即可】
     *
     * @param tbGarbage 传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody TbGarbage tbGarbage) {
        try {
            if (tbGarbage.getId() != null) {
                tbGarbageService.updateById(tbGarbage);
            } else {
                tbGarbageService.insert(tbGarbage);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage());
        }
    }

    //删除对象信息
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            tbGarbageService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TbGarbage get(@PathVariable("id") Long id) {
        return tbGarbageService.selectById(id);
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TbGarbage> list() {
        return tbGarbageService.selectList(null);
    }


    /**
     * 分页查询数据：
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<TbGarbage> json(@RequestBody TbGarbageQuery query) {
        Page<TbGarbage> page = new Page<TbGarbage>(query.getPage(), query.getRows());
        page = tbGarbageService.selectPage(page);
        return new PageList<TbGarbage>(page.getTotal(), page.getRecords());
    }

    @RequestMapping("/getGCategory")
    @ResponseBody
    public void getGCategory(String gcName, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        gcName = URLDecoder.decode(gcName);
        Writer out = response.getWriter();
        EntityWrapper<TbGarbage> ew = new EntityWrapper<TbGarbage>();
        ew.where("name like {0}", "%"+gcName+"%");

        TbGarbage tbGarbages = tbGarbageService.selectOne(ew);
        String name = tbGarbages.getTypeName();
        //返回值给微信小程序
        out.write(name);
        out.flush();


    }

}

