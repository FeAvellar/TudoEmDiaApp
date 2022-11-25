package com.mycompany.tudoemdiaapp;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;
import util.ConnectionFactory;

public class Main {
	
	public static void main(String[] args){
		
		ProjectController projectController = new ProjectController();
		
		  java.util.Date utilDate = new java.util.Date();
		  java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		Project project = new Project();
		project.setId(1);
		project.setName("Novo nome projeto");
		projectController.update(project);
		
		
		
		project.setName("Projeto teste");
		project.setDescription("description");
		project.setCreatedAt(sqlDate);
		project.setUpdatedAt(sqlDate);
		projectController.save(project);
		
		
		
		List<Project> projects = projectController.getAll();
		System.out.println("Total de projetos = " + projects.size());
		
		TaskController taskcontroller = new TaskController();
		
		Task task = new Task();
		task.setId(0);
		task.setName("Criar telas da aplicação");
		task.setDescription("Devem ser criadas telas para cadastros");
		task.setNotes("Sem notas");
		Connection c = ConnectionFactory.getConnection();
	


	    }

	}

