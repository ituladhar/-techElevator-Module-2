package com.techelevator.projects.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Employee;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcEmployeeDao implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date "+
				"FROM employee;");
		while(result.next()){
			employees.add(mapRowToEmployee(result));
		}
		return employees;
//		return new ArrayList<>();
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT first_name, last_name, employee_id, department_id, birth_date, hire_date " +
						"FROM employee " +
						"WHERE first_name ILIKE '%" + firstNameSearch + "%' AND last_name ILIKE '%" + lastNameSearch + "%';";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()){
			employees.add(mapRowToEmployee(results));
		}
		return employees;
//		return List.of(new Employee());
	}


	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT employee.employee_id, department_id, first_name, last_name, birth_date, hire_date " +
			"FROM employee " +
			"JOIN project_employee USING (employee_id) " +
			"WHERE project_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,projectId);
		while(results.next()){
			employees.add(mapRowToEmployee(results));
		}
		return employees;
//		return new ArrayList<>();
	}


	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		String sql = "INSERT INTO project_employee (project_id, employee_id)" +
			"VALUES (?, ?);";
	jdbcTemplate.update(sql,projectId,employeeId);
	}


	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
	String sql = "DELETE FROM project_employee " +
			"WHERE project_id = ? AND employee_id = ?;";
		jdbcTemplate.update(sql,projectId,employeeId);
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT employee.employee_id, department_id, first_name, last_name, birth_date, hire_date " +
				"FROM employee " +
				"LEFT JOIN project_employee USING (employee_id) " +
				"WHERE project_id IS NULL; ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()){
			employees.add(mapRowToEmployee(results));
		}
		return employees;
		//		return new ArrayList<>();
	}

	private Employee mapRowToEmployee(SqlRowSet result) {
		Employee employee = new Employee();
		employee.setId(result.getLong("employee_id"));
		employee.setDepartmentId(result.getLong("department_id"));
		employee.setFirstName(result.getString("first_name"));
		employee.setLastName(result.getString("last_name"));
		Date birth_date = result.getDate("birth_date");
		 if(birth_date != null){
			 employee.setBirthDate(birth_date.toLocalDate());
		 }
		Date hire_date = result.getDate("hire_date");
		if(hire_date != null){
			employee.setHireDate(hire_date.toLocalDate());
		}
//		employee.getHireDate(result.getDate("hire_date").toLocalDate());
		return employee;
	}

}
