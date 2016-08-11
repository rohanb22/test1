package net.example.springbatch.config;

import net.example.springbatch.bo.PersonVO;
import net.example.springbatch.common.ExcelReader;
import net.example.springbatch.common.ExcelWriter;
import net.example.springbatch.entity.Person;
import net.example.springbatch.processor.PersonProcessor;
import net.example.springbatch.rowmapper.PersonExcelRowMapper;

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
public class PersonJobConfig {

    private static final String PROPERTY_EXCEL_SOURCE_FILE_PATH = "excel.to.database.job.source.file.path";

    @Bean
    ItemReader<PersonVO> excelPersonReader(Environment environment) {
        ExcelReader<PersonVO> reader = new ExcelReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource(environment.getRequiredProperty(PROPERTY_EXCEL_SOURCE_FILE_PATH)));
        reader.setSheetNumber(1);
        reader.setRowMapper(excelPersonRowMapper());
        return reader;
    }

    /**
     * If your Excel document has no header, you have to create a custom
     * row mapper and configure it here.
     */
    private RowMapper<PersonVO> excelPersonRowMapper() {
       return new PersonExcelRowMapper();
    }

    @Bean
    ItemProcessor<PersonVO, Person> excelPersonProcessor() {
        return new PersonProcessor();
    }

    @Bean
    ItemWriter<Person> excelPersonWriter() {
        return new ExcelWriter<Person>();
    }

    @Bean
    Step excelPersonFileToDatabaseStep(ItemReader<PersonVO> excelStudentReader,
                         ItemProcessor<PersonVO, Person> excelStudentProcessor,
                         ItemWriter<Person> excelStudentWriter,
                         StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("excelPersonFileToDatabaseStep")
                .<PersonVO, Person>chunk(1)
                .reader(excelStudentReader)
                .processor(excelStudentProcessor)
                .writer(excelStudentWriter)
                .build();
    }

    @Bean
    Job excelPersonFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("excelPersonFileToDatabaseStep") Step excelStudentStep) {
        return jobBuilderFactory.get("excelPersonFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(excelStudentStep)
                .end()
                .build();
    }
}
