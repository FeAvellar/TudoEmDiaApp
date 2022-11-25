package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
	
	public void save(Task task) {
		String sql = "INSERT INTO tasks(idProject, "
				+ "name, "
				+ "description, "
				+ "status, "
				+ "notes, "
				+ "deadline, "
				+ "completed, "
				+ "createdAt, "
				+ "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conex�o com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, task.getIdProject());
            stmt.setString(2, task.getName());
            stmt.setString(3, task.getDescription());
            stmt.setBoolean(4, task.isIsCompleted());
            stmt.setString(5, task.getNotes());
            stmt.setDate(6, new Date(task.getDeadline().getTime()));
            stmt.setDate(7, new Date (task.getCreatedAt().getTime()));
            stmt.setDate(8, new Date (task.getUpdatedAt().getTime()));

            //Executa a sql para inser��o dos dados
            stmt.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
      
        } finally {
        	ConnectionFactory.closeConnection(conn, stmt);
           
        }
    }
	       
	
	public void update(Task task) {
		
		String sql = "UPDATE tasks SET "
				+ "idProject = ?, "
				+ "name = ?, "
				+ "description = ?, "
				+ "notes = ?, "
				+ "completed = ?, "
				+ "deadline = ?, "
				+ "createdAt = ?, "
				+ "updatedAt = ? "
				+ "WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conex�o com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);

            stmt.setInt (1, task.getIdProject());
            stmt.setString (2, task.getName());
            stmt.setString  (3, task.getDescription());
            stmt.setString  (4, task.getNotes());
            stmt.setBoolean (5, task.isIsCompleted());
            stmt.setDate (6, new Date(task.getDeadline().getTime()));
            stmt.setDate (7, new Date(task.getCreatedAt().getTime()));
            stmt.setDate (8, new Date(task.getUpdatedAt().getTime()));
            stmt.setInt (9, task.getId());

            //Executa a sql para inser��o dos dados
            stmt.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa", ex);
        }
		
	}
	public void removeById(int taskId) throws SQLException {
		
		String sql = "DELETE FROM tasks WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql); //ajuda a preparar o comando sql
            stmt.setInt(1, taskId);
            stmt.execute();
            
        } catch (Exception ex) {
        	throw new RuntimeException("Erro ao deletar tarefa" + ex.getMessage(), ex);
    
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
	}

	
	public List<Task> getAll(int idProject) {
		String sql = "SELECT * FROM tasks WHERE idProject = ?";

      
        Connection conn = null;
        PreparedStatement stmt = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        
        List<Task> tasks = new ArrayList<Task>();

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProject);
            rset =  stmt.executeQuery();
 
            while (rset.next()) {
            	Task task = new Task();
            	task.setId(rset.getInt("id"));
                task.setIdProject(rset.getInt("idProject"));
                task.setName(rset.getString("name"));
                task.setDescription(rset.getString("description"));
                task.setNotes(rset.getString("notes"));
                task.setIsCompleted(rset.getBoolean("completed"));
                task.setDeadline(rset.getDate("deadline"));
                task.setCreatedAt(rset.getDate("createdAt"));
                task.setUpdatedAt(rset.getDate("updatedAt"));
            	
                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
           
           }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar as tarefas" + ex.getMessage(), ex);
        } finally {
        	ConnectionFactory.closeConnection(conn, stmt, rset);
        }
            
		return tasks;
	
		
		 }
}