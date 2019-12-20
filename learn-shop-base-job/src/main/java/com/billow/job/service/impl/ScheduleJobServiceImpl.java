package com.billow.job.service.impl;

import com.billow.job.common.CustomPage;
import com.billow.job.dao.ScheduleJobDao;
import com.billow.job.pojo.po.ScheduleJobPo;
import com.billow.job.pojo.vo.ScheduleJobVo;
import com.billow.job.service.ScheduleJobService;
import com.billow.job.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 自动任务
 *
 * @author liuyongtao
 * @create 2018-02-28 9:14
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private ScheduleJobDao scheduleJobDao;

    @Override
    public List<ScheduleJobVo> findByJobStatus(ScheduleJobVo scheduleJobVo) {
        ScheduleJobPo scheduleJobPo = ConvertUtils.convert(scheduleJobVo, ScheduleJobPo.class);
        List<ScheduleJobPo> scheduleJobPos = scheduleJobDao.findAll(scheduleJobPo);
        return ConvertUtils.convert(scheduleJobPos, ScheduleJobVo.class);
    }

    @Override
    public ScheduleJobVo selectByPK(Long id) {
        ScheduleJobPo scheduleJobPo = scheduleJobDao.findById(id);
        return ConvertUtils.convert(scheduleJobPo, ScheduleJobVo.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateByPk(ScheduleJobVo dto) {
        scheduleJobDao.updateById(dto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteByPK(Long id) {
        scheduleJobDao.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(ScheduleJobVo dto) {
        ScheduleJobPo scheduleJobPo = ConvertUtils.convert(dto, ScheduleJobPo.class);
        ScheduleJobPo save = scheduleJobDao.save(scheduleJobPo);
        ConvertUtils.convert(save, dto);
    }

    @Override
    public CustomPage<ScheduleJobPo> selectAll(ScheduleJobVo scheduleJobVo) {
        ScheduleJobPo scheduleJobPo = ConvertUtils.convert(scheduleJobVo, ScheduleJobPo.class);
        return scheduleJobDao.findByPage(scheduleJobPo, scheduleJobVo.getPageNo(), scheduleJobVo.getPageSize());
    }

    @Override
    public ScheduleJobVo findByIdAndValidIndIsTrueAndIsStopIsTrue(Long id) {
        ScheduleJobPo po = scheduleJobDao.findByIdAndValidIndIsTrueAndIsExceptionStopIsTrue(id);
        return ConvertUtils.convert(po, ScheduleJobVo.class);
    }

    @Override
    public int countByJobNameAndJobGroup(String jobName, String jobGroup) {
        return scheduleJobDao.countByJobNameAndJobGroup(jobName, jobGroup);
    }
}
