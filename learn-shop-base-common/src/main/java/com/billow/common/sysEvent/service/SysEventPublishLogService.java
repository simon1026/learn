package com.billow.common.sysEvent.service;

import com.billow.common.sysEvent.model.expand.SysEventPublishLogDto;

/**
 * 事务执行日志
 *
 * @author liuyongtao
 * @create 2018-03-01 9:25
 */
public interface SysEventPublishLogService {
    void save(SysEventPublishLogDto sysEventPublishLogDto);
}