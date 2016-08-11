package net.example.springbatch.common;

import java.util.List;

import javax.transaction.Transactional;

import net.example.springbatch.repository.BaseDao;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class ExcelWriter<T> implements ItemWriter<T> {

	
	@Autowired
	private BaseDao<T> baseDao;
	
    @Override
    public void write(List<? extends T> items) throws Exception {
        
        items.forEach(i -> baseDao.saveOrUpdate((T)i));
        
    }
    
    
}
