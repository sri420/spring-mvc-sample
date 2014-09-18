package com.sri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sri.domain.Employee;
import com.sri.mappers.EmployeeMapper;

public class DAO {

	/* Fetch all Records*/
	public void getAllRecords() throws Exception {
		Connection connection=DBUtil.getConnection();
		
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("Select * from employee");
			outputResultSet(resultSet);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(null!=connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public void getRecordsByName(String strName) throws Exception {
		Connection connection=DBUtil.getConnection();
		
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("Select * from employee where name='" + strName +"'");
			outputResultSet(resultSet);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(null!=connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
		public void getRecordsByNameUsingPreparedStatement(String strName) throws Exception {
			Connection connection=DBUtil.getConnection();
			
			try {
				PreparedStatement preparedStatement=connection.prepareStatement("Select * from employee where name=?");
				preparedStatement.setString(1, strName);
				ResultSet resultSet=preparedStatement.executeQuery();
				outputResultSet(resultSet);
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				
				if(null!=connection) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	}
		
		public void getRecordsByNameAndIDUsingPreparedStatement(String strName,Integer intID) throws Exception {
			Connection connection=DBUtil.getConnection();
			
			try {
				PreparedStatement preparedStatement=connection.prepareStatement("Select * from employee where name=? or ID=?");
				preparedStatement.setString(1, strName);
				preparedStatement.setInt(2, intID.intValue());
				ResultSet resultSet=preparedStatement.executeQuery();
				outputResultSet(resultSet);
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				
				if(null!=connection) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	}
		
		
		public void getRecordsByCriteria(String strName,Integer intID) throws Exception {
			Connection connection=DBUtil.getConnection();
			String strQuery="Select * from employee ";
			try {
				if(null!=strName && strName.trim().length()>0) {
					strQuery+="  where name='" + strName + "'";
				}
				if(null!=intID) {
					if(null!=strName && strQuery.indexOf("name")>0) {
						strQuery+="  OR id="  + intID.toString();	
					}else {
						strQuery+="  where id="  + intID.toString();
					}
				}
				System.out.println("***Query Constructed is: " + strQuery);
				Statement statement=connection.createStatement();
				ResultSet resultSet=statement.executeQuery(strQuery);
				outputResultSet(resultSet);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				
				if(null!=connection) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
}

		public void getRecordsByCriteriaUsingPS(String strName,Integer intID) throws Exception {
			Connection connection=DBUtil.getConnection();
			
			StringBuffer strQuery=new StringBuffer("Select * from employee ");
			Map<String, Object> paramMap=new HashMap<String,Object>();
			
			try {
				
				int i=1;
				if(null!=strName && strName.trim().length()>0) {
					strQuery.append("  where name=? ");
					paramMap.put("name", new QueryParam(i++, "name", "String",strName));
				}
				if(null!=intID) {
					if(null!=strName && strQuery.indexOf("name")>0) {
						strQuery.append("  OR id=?");
							
					}else {
						strQuery.append("  where id=?");
					}
					paramMap.put("ID", new QueryParam(i++, "id", "Integer",intID.toString()));
				}
				System.out.println("***Query Constructed is: " + strQuery);
				
				
				PreparedStatement preparedStatement=connection.prepareStatement(strQuery.toString());
				setParametersToPreparedStatement(preparedStatement,paramMap);
				
				System.out.println("***Query with bind paramters: Constructed is: " + preparedStatement.toString());
				ResultSet resultSet=preparedStatement.executeQuery();
				outputResultSet(resultSet);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				
				if(null!=connection) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
}

		public void getRecordsByCriteriaUsingNamedParameterPS(String strName,Integer intID) throws Exception {
			Connection connection=DBUtil.getConnection();
			StringBuffer strQuery=new StringBuffer("Select * from employee ");
			Map<String, Object> paramMap=new HashMap<String,Object>();
			try {
				if(null!=strName && strName.trim().length()>0) {
					strQuery.append("  where name=:NAME ");
					paramMap.put("NAME", new QueryParam("String",strName));
				}
				if(null!=intID) {
					if(null!=strName && strQuery.indexOf("name")>0) {
						strQuery.append("  OR id=:ID OR ( name=:NAME )");
					}else {
						strQuery.append("  where id=( Select id from employee where id=:ID ) ");
					}
					paramMap.put("ID", new QueryParam("Integer",intID.toString()));
					
				}
				System.out.println("***Query Constructed is: " + strQuery);
				NamedParameterStatement namedParameterStatement=new NamedParameterStatement(connection, strQuery.toString());
				
				setParametersToNamedParameterPS(namedParameterStatement,paramMap);
				
				System.out.println("***Query with bind paramters: Constructed is: " + namedParameterStatement.getStatement().toString());
				ResultSet resultSet=namedParameterStatement.executeQuery();
				outputResultSet(resultSet);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				
				if(null!=connection) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
}

		private void setParametersToNamedParameterPS(
				NamedParameterStatement namedParameterStatement,
				Map<String, Object> paramMap) throws SQLException {
			for(String key: paramMap.keySet()){
				QueryParam paramObject=(QueryParam) paramMap.get((String)key);
				if(null!=paramObject) {
					if(paramObject.getParamType().equalsIgnoreCase("String")){
						namedParameterStatement.setString(key,paramObject.getParamValue());
					}else if (paramObject.getParamType().equalsIgnoreCase("Integer")){
						namedParameterStatement.setInt(key,Integer.parseInt(paramObject.getParamValue()));
					}else if (paramObject.getParamType().equalsIgnoreCase("TimeStamp")){
						//preparedStatement.setDate(paramObject.getParamPosition(),Double.parseDouble(paramObject.getParamValue()));
					}
				}
	        }
			
		}


		private void setParametersToPreparedStatement(
				PreparedStatement preparedStatement,
				Map<String, Object> paramMap) throws SQLException {
			for(String key: paramMap.keySet()){
				QueryParam paramObject=(QueryParam) paramMap.get((String)key);
				if(null!=paramObject) {
					if(paramObject.getParamType().equalsIgnoreCase("String")){
						preparedStatement.setString(paramObject.getParamPosition(),paramObject.getParamValue());
					}else if (paramObject.getParamType().equalsIgnoreCase("Integer")){
						preparedStatement.setInt(paramObject.getParamPosition(),Integer.parseInt(paramObject.getParamValue()));
					}else if (paramObject.getParamType().equalsIgnoreCase("Double")){
						preparedStatement.setDouble(paramObject.getParamPosition(),Double.parseDouble(paramObject.getParamValue()));
					}else if (paramObject.getParamType().equalsIgnoreCase("Date")){
						//preparedStatement.setDate(paramObject.getParamPosition(),Double.parseDouble(paramObject.getParamValue()));
					}
				}
	        }
			
		}

		
		public List<Employee> getEmployeesUsingMyBatis() {
			SqlSession sqlSession =
					DBUtil.MyBatisSessionFactory().openSession();
		
					try {
						EmployeeMapper employeeMapper =
						sqlSession.getMapper(EmployeeMapper.class);
						return employeeMapper.findAllEmployees();
						}finally {
						sqlSession.close();
					}
		}
		
		public Employee getEmployeeByIDUsingMyBatis(Integer id) {
			SqlSession sqlSession =
					DBUtil.MyBatisSessionFactory().openSession();
		
					try {
						EmployeeMapper employeeMapper =
						sqlSession.getMapper(EmployeeMapper.class);
						return employeeMapper.findEmployeeById(id.intValue());
						}finally {
						sqlSession.close();
					}
		}
		
		public List<Employee> getEmployeeByNameORAllUsingMyBatis(Integer name) {
			SqlSession sqlSession =
					DBUtil.MyBatisSessionFactory().openSession();
					try {
							EmployeeMapper employeeMapper =
							sqlSession.getMapper(EmployeeMapper.class);
							return employeeMapper.findByNameOrAll(name);
						}finally {
								sqlSession.close();
						}
		}
		
		  private static void outputResultSet(ResultSet rs) throws Exception {
			    ResultSetMetaData rsMetaData = rs.getMetaData();
			    int numberOfColumns = rsMetaData.getColumnCount();
			    for (int i = 1; i < numberOfColumns + 1; i++) {
			      String columnName = rsMetaData.getColumnName(i);
			      System.out.print(columnName + "   ");

			    }
			    System.out.println();
			    System.out.println("-------------------------------------");

			    while (rs.next()) {
			      for (int i = 1; i < numberOfColumns + 1; i++) {
			        System.out.print(rs.getString(i) + "   ");
			      }
			      System.out.println();
			      
			    }
			    System.out.println();
			    System.out.println();
			    System.out.println();

			  }
}

/*
 * 
 *  java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
    pstmt.setTimestamp(2, sqlDate);
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    pstmt.setDate(2, sqlDate);
 * 
*/