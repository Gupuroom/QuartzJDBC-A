package com.quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quartz.job.CronJob;
import com.quartz.job.QuartzJob;;

@Configuration
public class Scheduleconfig
{
	@Qualifier("SchedulerA")
	@Autowired
	private Scheduler schedulerA;
	
	@Qualifier("SchedulerCommon")
	@Autowired
	private Scheduler schedulerCommon;
	
	@Bean
	public JobDetail scheduleAJobDetail()
	{
		return JobBuilder.newJob(QuartzJob.class).withIdentity("scheduleJobA").storeDurably().build();
	}
	
	@Bean
	public JobDetail scheduleCommonJobDetail()
	{
		return JobBuilder.newJob(CronJob.class).withIdentity("scheduleJob").storeDurably().build();
	}
	
	@Bean
	public Trigger scheduleAJobTrigger()
	{
		// cron
		CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
		
		return TriggerBuilder.newTrigger().withSchedule(cronBuilder).build();
	}
	
	@Bean
	public Trigger scheduleCommonJobTrigger()
	{
		// cron
		CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
		
		return TriggerBuilder.newTrigger().withSchedule(cronBuilder).build();
	}
	
	@Bean
	public void addscheduleA() throws SchedulerException
	{
		JobDetail jobDetail = scheduleAJobDetail();
		
		if (schedulerA.checkExists(jobDetail.getKey()))
		{
			schedulerA.deleteJob(jobDetail.getKey());
		}
		schedulerA.scheduleJob(jobDetail, scheduleAJobTrigger());
	}
	
	@Bean
	public void addscheduleCommon() throws SchedulerException
	{
		JobDetail jobDetail = scheduleCommonJobDetail();
		
		if (schedulerCommon.checkExists(jobDetail.getKey()))
		{
			schedulerCommon.deleteJob(jobDetail.getKey());
		}
		schedulerCommon.scheduleJob(jobDetail, scheduleCommonJobTrigger());
	}
}
