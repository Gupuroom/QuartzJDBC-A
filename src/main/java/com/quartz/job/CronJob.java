package com.quartz.job;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CronJob extends QuartzJobBean {
	private int MAX_SLEEP_IN_SECONDS = 5;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String jobId = jobDataMap.getString("jobId");
		JobKey jobKey = context.getJobDetail().getKey();

		log.info("============================================================================");
		log.info("CronJob started :: sleep : {} jobId : {} jobKey : {}", MAX_SLEEP_IN_SECONDS, jobId, jobKey);

		IntStream.range(0, 4).forEach(i -> {
			log.info("CronJob Counting - {}", i);
			try {
				TimeUnit.SECONDS.sleep(MAX_SLEEP_IN_SECONDS);
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
			}
		});
		log.info("CronJob ended :: jobKey : {}", jobKey);
		log.info("============================================================================");
	}
}