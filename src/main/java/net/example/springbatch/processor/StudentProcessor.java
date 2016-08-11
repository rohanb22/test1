package net.example.springbatch.processor;

import net.example.springbatch.bo.StudentVO;
import net.example.springbatch.entity.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<StudentVO, Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);

    @Override
    public Student process(StudentVO item) throws Exception {
        LOGGER.info("Processing student information: {}", item);
        
        Student student = new Student();
        student.setEmailAddress(item.getEmailAddress());
        student.setName(item.getFirstName() + " " + item.getLastName());
        student.setPurchasedPackage(item.getPurchasedPackage());
        return student;
    }
}
