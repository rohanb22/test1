package net.example.springbatch.rowmapper;

import java.text.NumberFormat;

import net.example.springbatch.bo.PersonVO;
import net.example.springbatch.entity.Person;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

public class PersonExcelRowMapper implements RowMapper<PersonVO> {

	public static NumberFormat numberFormat = NumberFormat.getIntegerInstance();
	
	
    @Override
    public PersonVO mapRow(RowSet rowSet) throws Exception {
        PersonVO person = new PersonVO();
        numberFormat.setParseIntegerOnly(true);
        person.setFirstName(rowSet.getColumnValue(0));
        person.setLastName(rowSet.getColumnValue(1));
        person.setAge(numberFormat.parse(rowSet.getColumnValue(2)).intValue());
        person.setPhoneNumber(rowSet.getColumnValue(3));

        return person;
    }
}
