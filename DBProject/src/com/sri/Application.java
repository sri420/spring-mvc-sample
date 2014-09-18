/**
 * 
 */
package com.sri;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sri.domain.Employee;

/**
 * @author The Owner
 *
 */
public class Application {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("***********Application STARTED*****");
		DAO dao=new DAO();
		/*System.out.println("***********AllRecords");
		dao.getAllRecords();
		System.out.println("***********ByName");
		dao.getRecordsByName("Ram");
		System.out.println("***********ByNameUsingPreparedStatement");
		dao.getRecordsByNameUsingPreparedStatement("Mary");
		System.out.println("***********ByNameAndIDUsingPreparedStatement");
		dao.getRecordsByNameAndIDUsingPreparedStatement("Mary",new Integer(4));*/
		/*System.out.println("*************START: ByDynamicCriteria***********");
		dao.getRecordsByCriteria("Mary",null);
		System.out.println("*************END:   ByDynamicCriteria***********");
		System.out.println("*************START: ByDynamicCriteria***********");
		dao.getRecordsByCriteria(null,null);
		System.out.println("*************END: ByDynamicCriteria***********");
		System.out.println("*************START: ByDynamicCriteria***********");
		dao.getRecordsByCriteria(null,new Integer(4));
		System.out.println("*************END: ByDynamicCriteria***********");
		System.out.println("*************START: ByDynamicCriteria***********");
		dao.getRecordsByCriteria("Ram",new Integer(2));
		System.out.println("*************END: ByDynamicCriteria***********");*/
		
	/*	System.out.println("*************START: getRecordsByCriteriaUsingPS***********");
		dao.getRecordsByCriteriaUsingPS("Mary",null);
		System.out.println("*************END:   getRecordsByCriteriaUsingPS***********");
		System.out.println("*************START: getRecordsByCriteriaUsingPS***********");
		dao.getRecordsByCriteriaUsingPS(null,null);
		System.out.println("*************END: getRecordsByCriteriaUsingPS***********");
		System.out.println("*************START: getRecordsByCriteriaUsingPS***********");
		dao.getRecordsByCriteriaUsingPS(null,new Integer(4));
		System.out.println("*************END: getRecordsByCriteriaUsingPS***********");
		System.out.println("*************START: getRecordsByCriteriaUsingPS***********");
		dao.getRecordsByCriteriaUsingPS("Ram",new Integer(4));
		System.out.println("*************END: getRecordsByCriteriaUsingPS***********");*/
		
		//DBUtil.MyBatisSessionFactory();
		
		/*List<Employee> empList=dao.getEmployeesUsingMyBatis();
		Employee employee=dao.getEmployeeByIDUsingMyBatis(new Integer(4));
		*///List<Employee> employeeList1=dao.getEmployeeByNameORAllUsingMyBatis(null);
		//List<Employee> employeeList2=dao.getEmployeeByNameORAllUsingMyBatis(3);
		System.out.println("*************Employee list using Mybatis***********");
		System.out.println("*************START: getRecordsByCriteriaUsingNamedParameterPS***********");
		System.out.println();System.out.println();
		dao.getRecordsByCriteriaUsingNamedParameterPS("Mary", new Integer(3));
		
		dao.getRecordsByCriteriaUsingNamedParameterPS(null, null);
		
		dao.getRecordsByCriteriaUsingNamedParameterPS("Ram", null);
		
		dao.getRecordsByCriteriaUsingNamedParameterPS(null, new Integer(1));
		System.out.println("*************END: getRecordsByCriteriaUsingNamedParameterPS***********");
		
		
		
	}

}
