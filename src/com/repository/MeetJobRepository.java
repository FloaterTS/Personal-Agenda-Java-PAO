package com.repository;

import com.components.MeetingJob;
import com.connection.DatabaseConnection;
import com.helper.ZDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetJobRepository
{

    private static MeetJobRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO meetingsJob (id_mj, s_date, description, withBoss) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM meetingsJob WHERE id_mj = ?";
    private static final String UPDATE_STATEMENT = "UPDATE meetingsJob SET description = ? WHERE id_mj = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM meetingsJob WHERE id_mj = ?";

    private MeetJobRepository()
    {
    }

    public static MeetJobRepository getInstance()
    {
        if (instance == null)
        {
            instance = new MeetJobRepository();
        }

        return instance;
    }

    public void save(MeetingJob meet)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT))
        {
            statement.setInt(1, meet.getId());
            statement.setString(2, Integer.toString(meet.getDate().zi) + "-" +
                    Integer.toString(meet.getDate().luna) + "-" + Integer.toString(meet.getDate().an));
            statement.setString(3, meet.getDesc());
            statement.setInt(4, meet.getWithBoss() ? 1 : 0);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                System.out.println("A new meetingJob was inserted successfully!");
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to insert a new meetingJob: " + e.getMessage());
        }
    }

    public MeetingJob find(int id)
    {
        MeetingJob meet = new MeetingJob(0,0,0);
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT))
        {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery())
            {
                if (!result.next())
                {
                    System.out.println("Something went wrong when trying to find meetingJob: it was not found!");
                    return meet;
                }

                System.out.println("MeetingJob was found!");
                meet.setId(id);
                String[] d = result.getString("s_date").split("-");
                meet.setDate(new ZDate(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2])));
                meet.setDescriere(result.getString("description"));
                meet.setWithBoss(result.getInt("withBoss") != 0);
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to find meetingJob: " + e.getMessage());
        }
        return meet;
    }

    public void update(int id, String newDesc)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT))
        {
            statement.setString(1, newDesc);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("MeetingJob was updated successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to update meetingJob: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update meetingJob: it was not found!");
    }

    public void delete(int id)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT))
        {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("MeetingJob was deleted successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to delete meetingJob: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to delete meetingJob: it was not found!");
    }

}
