package com.mulaobao.administration.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mulaobao.administration.entity.LsEmpsale;
import com.mulaobao.administration.service.LsEmpsaleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (LsEmpsale)表控制层
 *
 * @author makejava
 * @since 2021-10-19 15:20:02
 */
@RestController
@RequestMapping("lsEmpsale")
public class LsEmpsaleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LsEmpsaleService lsEmpsaleService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param lsEmpsale 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<LsEmpsale> page, LsEmpsale lsEmpsale) {
        return success(this.lsEmpsaleService.page(page, new QueryWrapper<>(lsEmpsale)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.lsEmpsaleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param lsEmpsale 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody LsEmpsale lsEmpsale) {
        return success(this.lsEmpsaleService.save(lsEmpsale));
    }

    /**
     * 修改数据
     *
     * @param lsEmpsale 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody LsEmpsale lsEmpsale) {
        return success(this.lsEmpsaleService.updateById(lsEmpsale));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.lsEmpsaleService.removeByIds(idList));
    }
}

