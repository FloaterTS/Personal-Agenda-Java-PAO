package com.repository;

import com.components.Meeting;
import com.components.MeetingHome;
import com.helper.ZDate;
import com.connection.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetHomeRepository
{

    private static MeetHomeRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO meetingsHome (id_mh, s_date, description, family) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM meetingsHome WHERE id_mh = ?";
    private static final String UPDATE_STATEMENT = "UPDATE meetingsHome SET description = ? WHERE id_mh = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM meetingsHome WHERE id_mh = ?";

    private MeetHomeRepository()
    {
    }

    public static MeetHomeRepository getInstance()
    {
        if (instance == null)
        {
            instance = new MeetHomeRepository();
        }

        return instance;
    }

    public void save(MeetingHome meet)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT))
        {
            statement.setInt(1, meet.getId());
            statement.setString(2, Integer.toString(meet.getDate().zi) + "-" +
                    Integer.toString(meet.getDate().luna) + "-" + Integer.toString(meet.getDate().an));
            statement.setString(3, meet.getDesc());
            statement.setInt(4, meet.getFamily() ? 1 : 0);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                System.out.println("A new meetingHome was inserted successfully!");
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to insert a new meetingHome: " + e.getMessage());
        }
    }

    public MeetingHome find(int id)
    {
        MeetingHome meet = new MeetingHome(0,0,0);
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT))
        {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery())
            {
                if (!result.next())
                {
                    System.out.println("Something went wrong when trying to find meetingHome: it was not found!");
                    return meet;
                }

                System.out.println("MeetingHome was found!");
                meet.setId(id);
                String[] d = result.getString("s_date").split("-");
                meet.setDate(new ZDate(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2])));
                meet.setDescriere(result.getString("description"));
                meet.setFamily(result.getInt("must") != 0);
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to find meetingHome: " + e.getMessage());
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
                System.out.println("MeetingHome was updated successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to update meetingHome: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to update meetingHome: it was not found!");
    }

    public void delete(int id)
    {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT))
        {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("MeetingHome was deleted successfully!");
                return;
            }
        } catch (SQLException e)
        {
            System.out.println("Something went wrong when trying to delete meetingHome: " + e.getMessage());
            return;
        }

        System.out.println("Something went wrong when trying to delete meetingHome: it was not found!");
    }

}
