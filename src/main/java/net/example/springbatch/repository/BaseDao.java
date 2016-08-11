package net.example.springbatch.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> genericType;



	@Transactional
	public List<T> list() {
	    @SuppressWarnings("unchecked")
	    List<T> listPerson = (List<T>) sessionFactory.getCurrentSession()
	            .createCriteria(genericType.getClass())
	            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	
	    return listPerson;
	}

	@Transactional
	public void saveOrUpdate(T student) {
	    sessionFactory.getCurrentSession().saveOrUpdate(student);
	}

	@Transactional
	public T get(int id) {

		@SuppressWarnings("unchecked")
		T obj = (T)sessionFactory.getCurrentSession().load(genericType.getClass(), id);
	     
	    return obj;
	}

	
}
