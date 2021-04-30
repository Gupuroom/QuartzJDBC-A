package com.quartz.job;

import java.time.LocalDateTime;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuartzJob extends QuartzJobBean {
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	log.info("Scheduler A 실행 중 : {}", LocalDateTime.now().toString());
    }
}