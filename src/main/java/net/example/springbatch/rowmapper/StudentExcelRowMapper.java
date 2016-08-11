package net.example.springbatch.rowmapper;

import net.example.springbatch.bo.StudentVO;
import net.example.springbatch.entity.Student;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;
public class StudentExcelRowMapper implements RowMapper<StudentVO> {

    @Override
    public StudentVO mapRow(RowSet rowSet) throws Exception {
    	StudentVO student = new StudentVO();

        student.setFirstName(rowSet.getColumnValue(0));
        student.setLastName(rowSet.getColumnValue(1));
        student.setEmailAddress(rowSet.getColumnValue(2));
        student.setPurchasedPackage(rowSet.getColumnValue(3));

        return student;
    }
}
