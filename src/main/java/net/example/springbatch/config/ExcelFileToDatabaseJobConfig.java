package net.example.springbatch.config;

import net.example.springbatch.bo.StudentVO;
import net.example.springbatch.common.ExcelReader;
import net.example.springbatch.common.ExcelWriter;
import net.example.springbatch.entity.Student;
import net.example.springbatch.processor.StudentProcessor;
import net.example.springbatch.rowmapper.StudentExcelRowMapper;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ExcelFileToDatabaseJobConfig {

    private static final String PROPERTY_EXCEL_SOURCE_FILE_PATH = "excel.to.database.job.source.file.path";

    @Bean
    ItemReader<StudentVO> excelStudentReader(Environment environment) {
        ExcelReader<StudentVO> reader = new ExcelReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource(environment.getRequiredProperty(PROPERTY_EXCEL_SOURCE_FILE_PATH)));
        reader.setSheetNumber(0);
        reader.setRowMapper(excelRowMapper());
        return reader;
    }

    /**
     * If your Excel document has no header, you have to create a custom
     * row mapper and configure it here.
     */
    private RowMapper<StudentVO> excelRowMapper() {
       return new StudentExcelRowMapper();
    }

    @Bean
    ItemProcessor<StudentVO, Student> excelStudentProcessor() {
        return new StudentProcessor();
    }

    @Bean
    ItemWriter<Student> excelStudentWriter() {
        return new ExcelWriter<Student>();
    }

    @Bean
    Step excelFileToDatabaseStep(ItemReader<StudentVO> excelStudentReader,
                         ItemProcessor<StudentVO, Student> excelStudentProcessor,
                         ItemWriter<Student> excelStudentWriter,
                         StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("excelFileToDatabaseStep")
                .<StudentVO, Student>chunk(1)
                .reader(excelStudentReader)
                .processor(excelStudentProcessor)
                .writer(excelStudentWriter)
                .build();
    }

    @Bean
    Job excelFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("excelFileToDatabaseStep") Step excelStudentStep) {
        return jobBuilderFactory.get("excelFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(excelStudentStep)
                .end()
                .build();
    }
}
