package net.example.springbatch.processor;

import net.example.springbatch.bo.PersonVO;
import net.example.springbatch.entity.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<PersonVO, Person> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonProcessor.class);

    @Override
    public Person process(PersonVO item) throws Exception {
        LOGGER.info("Processing person information: {}", item);
        Person person = new Person();
        person.setAge(item.getAge());
        person.setPhoneNumber(item.getPhoneNumber());
        person.setName(item.getFirstName() + " " + item.getLastName());
        return person;
    }
}
