package com.quartz.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig
{
	@Autowired
	DataSource dataSource;
	
	@Bean
	public SchedulerFactoryBean SchedulerA()
	{
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		
		Properties properties = new Properties();
		properties.setProperty("org.quartz.scheduler.instanceName", "quartzA");
		properties.setProperty("org.quartz.scheduler.instanceId", "AUTO");
		properties.setProperty("org.quartz.scheduler.interruptJobsOnShutdown", "true");
		properties.setProperty("org.quartz.scheduler.makeSchedulerThreadDaemon", "true");
		properties.setProperty("org.quartz.scheduler.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		
		properties.setProperty("org.quartz.scheduler.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
		properties.setProperty("org.quartz.scheduler.jobStore.useProperties", "true");
		properties.setProperty("org.quartz.scheduler.jobStore.tablePrefix", "QRTZ_");
		properties.setProperty("org.quartz.scheduler.jobStore.misfireThreshold", "60000");
		properties.setProperty("org.quartz.scheduler.jobStore.clusterCheckinInterval", "10000");
		properties.setProperty("org.quartz.scheduler.jobStore.isClustered", "false");
		
		properties.setProperty("org.quartz.scheduler.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		properties.setProperty("org.quartz.scheduler.threadPool.threadCount", "10");
		properties.setProperty("org.quartz.scheduler.threadPool.threadPriority", "5");
		properties.setProperty("org.quartz.scheduler.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
		
		schedulerFactory.setOverwriteExistingJobs(true);
		schedulerFactory.setQuartzProperties(properties);
		schedulerFactory.setDataSource(dataSource);
		
		return schedulerFactory;
	}
	
	@Bean
	public SchedulerFactoryBean SchedulerCommon()
	{
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		
		Properties properties = new Properties();
		properties.setProperty("org.quartz.scheduler.instanceName", "quartzCommon");
		properties.setProperty("org.quartz.scheduler.instanceId", "AUTO");
		properties.setProperty("org.quartz.scheduler.interruptJobsOnShutdown", "true");
		properties.setProperty("org.quartz.scheduler.makeSchedulerThreadDaemon", "true");
		properties.setProperty("org.quartz.scheduler.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		
		properties.setProperty("org.quartz.scheduler.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
		properties.setProperty("org.quartz.scheduler.jobStore.useProperties", "true");
		properties.setProperty("org.quartz.scheduler.jobStore.tablePrefix", "QRTZ_");
		properties.setProperty("org.quartz.scheduler.jobStore.misfireThreshold", "60000");
		properties.setProperty("org.quartz.scheduler.jobStore.clusterCheckinInterval", "10000");
		properties.setProperty("org.quartz.scheduler.jobStore.isClustered", "true");
		
		properties.setProperty("org.quartz.scheduler.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		properties.setProperty("org.quartz.scheduler.threadPool.threadCount", "10");
		properties.setProperty("org.quartz.scheduler.threadPool.threadPriority", "5");
		properties.setProperty("org.quartz.scheduler.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
		
		schedulerFactory.setOverwriteExistingJobs(true);
		schedulerFactory.setQuartzProperties(properties);
		schedulerFactory.setDataSource(dataSource);
		
		return schedulerFactory;
	}
}
