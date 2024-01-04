package com.ylsm.service;

import com.ylsm.model.AutoTaskRecordDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: hqy
 * @create: 2023-12-28 17:34
 **/
@Service
@Slf4j
public class AutoTaskRecordService {

    public void updateTime(String model) {
        AutoTaskRecordDo autoTaskRecordDO = new AutoTaskRecordDo();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        autoTaskRecordDO.setTaskName(model);
        autoTaskRecordDO.setLastTime(df.format(new Date()));
//        this.autoTaskRecordMapper.updateByPrimaryKey(autoTaskRecordDO);
        // todo 更新任务上次执行时间

    }

}
