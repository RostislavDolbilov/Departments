package company.dao.impl;

import company.dao.PositionDao;
import company.model.Position;
import company.utils.AppConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PositionDaoImpl implements PositionDao {
    private final Connection connection;

    @Autowired
    public PositionDaoImpl(AppConnection connection) {
        this.connection = connection.getConnection();
    }

    @Override
    public void addPosition(Position position) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO position (POSITION) VALUES(?)")) {

            preparedStatement.setString(1, position.getPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Position> getAllPositions() throws SQLException {
        ArrayList<Position> positionList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM position");

            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getInt("id"));
                position.setPosition(resultSet.getString("position"));
                positionList.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
        return positionList;
    }

    @Override
    public Position getPositionById(int id) throws SQLException {
        Position position = new Position();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM position WHERE id = " + id);

            while (resultSet.next()) {
                position.setId(resultSet.getInt("id"));
                position.setPosition(resultSet.getString("position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
        return position;
    }
}
