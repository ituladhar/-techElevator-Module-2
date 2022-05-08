package com.techelevator.projects.dao;

import java.awt.print.PrinterJob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(Long projectId) {
		Project project = null;
		String sql = "SELECT project_id, name, from_date, to_date " +
				"FROM project " +
				"WHERE project_id = ?; ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		if(results.next()){
			project = mapRowToProject(results);
		}
		return project;
//		return new Project(0L, "Not Implemented Yet", null, null);
	}


	@Override
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT project_id, name, from_date, to_date " +
				"FROM project;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()){
			projects.add(mapRowToProject(results));
		}
		return projects;
//		return new ArrayList<>();
	}

	@Override
	public Project createProject(Project newProject) {
		String sql = "INSERT INTO project (name, from_date, to_date) " +
				"VALUES (?, ?, ?) RETURNING project_id;";
		Long newId = jdbcTemplate.queryForObject(sql,
				Long.class, //?
				newProject.getName(),
				newProject.getFromDate(),
				newProject.getToDate());
		newProject.setId(newId);
		return newProject;
//		return null;
	}

	@Override
	public void deleteProject(Long projectId) {
		String removeFromEmployee = "DELETE FROM project_employee WHERE project_id = ?;";
		String removeFromProject = "DELETE FROM project WHERE project_id = ?;";
		jdbcTemplate.update(removeFromEmployee, projectId);
		jdbcTemplate.update(removeFromProject, projectId);
	}

	private Project mapRowToProject(SqlRowSet results) {
		Project project = new Project();
		project.setId(results.getLong("project_id"));
		project.setName(results.getString("name"));
		Date from_date = results.getDate("from_date");
		if(from_date != null){
			project.setFromDate(from_date.toLocalDate());
		}
		Date to_date = results.getDate("to_date");
		if(to_date != null){
			project.setToDate(to_date.toLocalDate());
		}
		return project;
	}

}
