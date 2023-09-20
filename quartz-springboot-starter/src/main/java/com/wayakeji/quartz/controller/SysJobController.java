package com.wayakeji.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.wayakeji.common.core.constant.CommonConstants;
import com.wayakeji.common.core.exception.TaskException;
import com.wayakeji.common.core.util.R;
import com.wayakeji.common.core.util.StringUtils;
import com.wayakeji.common.log.annotation.SysLog;
import com.wayakeji.quartz.domain.SysJob;
import com.wayakeji.quartz.service.ISysJobService;
import com.wayakeji.quartz.util.CronUtils;
import com.wayakeji.quartz.util.ScheduleUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 调度任务信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/job")
public class SysJobController {
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
    @GetMapping("/list")
    public R<IPage<SysJob>> list(Page page, SysJob sysJob) {
        return R.ok(jobService.selectJobPage(page,sysJob));
    }

    /**
     * 导出定时任务列表
     */
    @ResponseExcel
    @SysLog(value = "定时任务")
    @PostMapping("/export")
    public List<SysJob> export(HttpServletResponse response, SysJob sysJob) {
        return jobService.selectJobList(sysJob);
    }

    /**
     * 获取定时任务详细信息
     */
    @GetMapping(value = "/{jobId}")
    public R<SysJob> getInfo(@PathVariable("jobId") Long jobId)
    {
        return R.ok(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @SysLog(value = "定时任务")
    @PostMapping
    public R add(@RequestBody SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return R.failed("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        }
        else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), CommonConstants.LOOKUP_RMI))
        {
            return R.failed("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { CommonConstants.LOOKUP_LDAP, CommonConstants.LOOKUP_LDAPS }))
        {
            return R.failed("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { CommonConstants.HTTP, CommonConstants.HTTPS }))
        {
            return R.failed("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), CommonConstants.JOB_ERROR_STR))
        {
            return R.failed("新增任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        }
        else if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
        {
            return R.failed("新增任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
//        job.setCreateBy(getUsername());
        return R.ok(jobService.insertJob(job));
    }

    /**
     * 修改定时任务
     */
    @SysLog(value = "定时任务")
    @PutMapping
    public R edit(@RequestBody SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return R.failed("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        }
        else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), CommonConstants.LOOKUP_RMI))
        {
            return R.failed("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { CommonConstants.LOOKUP_LDAP, CommonConstants.LOOKUP_LDAPS }))
        {
            return R.failed("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { CommonConstants.HTTP, CommonConstants.HTTPS }))
        {
            return R.failed("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), CommonConstants.JOB_ERROR_STR))
        {
            return R.failed("修改任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        }
        else if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
        {
            return R.failed("修改任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
//        job.setUpdateBy(getUsername());
        return R.ok(jobService.updateJob(job));
    }

    /**
     * 定时任务状态修改
     */
    @SysLog(value = "定时任务")
    @PutMapping("/changeStatus")
    public R changeStatus(@RequestBody SysJob job) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return R.ok(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @SysLog(value = "定时任务")
    @PutMapping("/run")
    public R run(@RequestBody SysJob job) throws SchedulerException {
        boolean result = jobService.run(job);
        return result ? R.ok() : R.failed("任务不存在或已过期！");
    }

    /**
     * 删除定时任务
     */
    @SysLog(value = "定时任务")
    @DeleteMapping("/{jobIds}")
    public R remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException {
        jobService.deleteJobByIds(jobIds);
        return R.ok();
    }
}
