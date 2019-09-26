package job.service.impl;

import job.dao.ScheduleJobLogDao;
import job.pojo.po.ScheduleJobLogPo;
import job.pojo.vo.ScheduleJobLogVo;
import job.service.ScheduleJobLogService;
import job.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 自动任务日志
 *
 * @author liuyongtao
 * @create 2018-02-28 9:15
 */
@Service
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

    @Autowired
    private ScheduleJobLogDao scheduleJobLogDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(ScheduleJobLogVo logDto) {
        ScheduleJobLogPo scheduleJobLogPo = ConvertUtils.convert(logDto, ScheduleJobLogPo.class);
        ScheduleJobLogPo save = scheduleJobLogDao.save(scheduleJobLogPo);
        ConvertUtils.convert(save, logDto);
    }

    @Override
    public Page<ScheduleJobLogPo> findAutoTaskLog(ScheduleJobLogVo scheduleJobLogVo) {
        ScheduleJobLogPo scheduleJobLogPo = ConvertUtils.convert(scheduleJobLogVo, ScheduleJobLogPo.class);
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(scheduleJobLogVo.getPageNo(), scheduleJobLogVo.getPageSize(), sort);
        Page<ScheduleJobLogPo> page = scheduleJobLogDao.findAll(pageable);
        return page;
    }
}
