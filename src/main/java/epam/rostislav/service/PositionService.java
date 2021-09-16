package epam.rostislav.service;

import epam.rostislav.dao.PositionDao;
import epam.rostislav.dto.Position;
import epam.rostislav.utils.AppConnection;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;


@Service
public class PositionService extends AppConnection implements PositionDao {
    private final Connection connection;

    public PositionService() {
        this.connection = getConnection();
    }

    @Override
    public void addPosition(Position position) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO position (POSITION) VALUES(?)")) {

            preparedStatement.setString(1, position.getPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            connection.close();
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Position> getAllPositions() throws SQLException {
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
            connection.close();
            e.printStackTrace();
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
            connection.close();
            e.printStackTrace();
        }
        return position;
    }
}
