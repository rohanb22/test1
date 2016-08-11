package net.example.springbatch;

import net.example.springbatch.excel.in.ExcelFileToDatabaseJobLauncher;
import net.example.springbatch.excel.in.PersonJobLauncher;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExcelToDBBatch {

	public static void main(String[] args){
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringBatchExampleContext.class);
		ExcelFileToDatabaseJobLauncher jobLauncher = context.getBean(ExcelFileToDatabaseJobLauncher.class);
		PersonJobLauncher personJobLauncher = context.getBean(PersonJobLauncher.class);
		
		try {
			
			jobLauncher.launchXmlFileToDatabaseJob();
			personJobLauncher.launchXmlFileToDatabaseJob();
			
		} catch (JobParametersInvalidException
				| JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
