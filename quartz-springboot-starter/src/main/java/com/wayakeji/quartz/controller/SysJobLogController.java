package com.wayakeji.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.quartz.domain.SysJobLog;
import com.wayakeji.quartz.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController {
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @GetMapping("/list")
    public R<IPage<SysJobLog>> list(Page page,SysJobLog sysJobLog) {
        return R.ok(jobLogService.selectJobLogPage(page,sysJobLog));
    }

    /**
     * 导出定时任务调度日志列表
     */
    @ResponseExcel
    @SysLog(value = "任务调度日志")
    @PostMapping("/export")
    public List<SysJobLog> export(HttpServletResponse response, SysJobLog sysJobLog) {
        return jobLogService.selectJobLogList(sysJobLog);
    }
    
    /**
     * 根据调度编号获取详细信息
     */
    @GetMapping(value = "/{jobLogId}")
    public R<SysJobLog> getInfo(@PathVariable Long jobLogId) {
        return R.ok(jobLogService.selectJobLogById(jobLogId));
    }


    /**
     * 删除定时任务调度日志
     */
    @SysLog(value = "定时任务调度日志")
    @DeleteMapping("/{jobLogIds}")
    public R remove(@PathVariable Long[] jobLogIds) {
        return R.ok(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @SysLog(value = "调度日志")
    @DeleteMapping("/clean")
    public R clean() {
        jobLogService.cleanJobLog();
        return R.ok();
    }
}
