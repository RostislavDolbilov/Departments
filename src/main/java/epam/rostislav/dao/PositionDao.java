package epam.rostislav.dao;

import org.springframework.stereotype.Component;
import epam.rostislav.dto.Position;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public interface PositionDao {
    void addPosition(Position position) throws SQLException;
    ArrayList<Position> getAllPositions() throws SQLException;
    Position getPositionById(int id) throws SQLException;
}
