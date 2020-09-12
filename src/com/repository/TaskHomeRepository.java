package com.repository;

import com.connection.DatabaseConnection;
import com.components.TaskHome;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskHomeRepository
{

    private static TaskHomeRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO tasksHome (id_th, title, description, must) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM tasksHome WHERE id_th = ?";
    private static final String UPDATE_STATEMENT = "UPDATE tasksHome SET title = ? WHERE id_th = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM tasksHome WHERE id_th = ?";

    private TaskHomeRepository()
    {
    }

    public static TaskHomeRepository getInstance()
    {
        if (instance == null)
        {
            instance = new TaskHomeRepository();
        }

        return instance;
    }

    public void save(TaskHome task)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT))
        {
            statement.setInt(1, task.getId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDesc());
            statement.setInt(4, task.getMust() ? 1 : 0);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                System.out.println("A new taskHome was inserted successfully!");
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to insert a new taskHome: " + e.getMessage());
        }
    }

    public TaskHome find(int id)
    {
        TaskHome task = new TaskHome(null);
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT))
        {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery())
            {
                if (!result.next())
                {
                    System.out.println("Something went wrong when trying to find taskHome: it was not found!");
                    return task;
                }

                System.out.println("TaskHome was found!");
                task.setId(id);
                task.setTitle(result.getString("title"));
                task.setDescriere(result.getString("description"));
                task.setMust(result.getInt("must") != 0);
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to find taskHome: " + e.getMessage());
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
                System.out.println("TaskHome was updated successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to update taskHome: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update taskHome: it was not found!");
    }

    public void delete(int id)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT))
        {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("TaskHome was deleted successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to delete taskHome: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to delete taskHome: it was not found!");
    }

}
