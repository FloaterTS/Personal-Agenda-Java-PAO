package com.repository;

import com.components.TaskJob;
import com.helper.ZDate;
import com.connection.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskJobRepository
{

    private static TaskJobRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO tasksJob (id_tj, title, description, must) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM tasksJob WHERE id_tj = ?";
    private static final String UPDATE_STATEMENT = "UPDATE tasksJob SET title = ? WHERE id_tj = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM tasksJob WHERE id_tj = ?";

    private TaskJobRepository()
    {
    }

    public static TaskJobRepository getInstance()
    {
        if (instance == null)
        {
            instance = new TaskJobRepository();
        }

        return instance;
    }

    public void save(TaskJob task)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT))
        {
            statement.setInt(1, task.getId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDesc());
            statement.setString(4, Integer.toString(task.getDeadline().zi) + "-" +
                    Integer.toString(task.getDeadline().luna) + "-" + Integer.toString(task.getDeadline().an));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                System.out.println("A new taskJob was inserted successfully!");
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to insert a new taskJob: " + e.getMessage());
        }
    }

    public TaskJob find(int id)
    {
        TaskJob task = new TaskJob(null);
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT))
        {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery())
            {
                if (!result.next())
                {
                    System.out.println("Something went wrong when trying to find taskJob: it was not found!");
                    return task;
                }

                System.out.println("TaskJob was found!");
                task.setId(id);
                task.setTitle(result.getString("title"));
                task.setDescriere(result.getString("description"));
                String[] dl = result.getString("deadline").split("-");
                task.setDeadline(new ZDate(Integer.parseInt(dl[0]),Integer.parseInt(dl[1]),Integer.parseInt(dl[2])));
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to find taskJob: " + e.getMessage());
        }
        return task;
    }

    public void update(int id, String newTitle)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT))
        {
            statement.setString(1, newTitle);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("TaskJob was updated successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to update taskJob: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update taskJob: it was not found!");
    }

    public void delete(int id)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT))
        {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("TaskJob was deleted successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to delete taskJob: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to delete taskJob: it was not found!");
    }

}
