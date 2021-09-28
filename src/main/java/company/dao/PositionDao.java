package company.dao;

import company.model.Position;
import java.sql.SQLException;
import java.util.List;

public interface PositionDao {
    void addPosition(Position position) throws SQLException;
    List<Position> getAllPositions() throws SQLException;
    Position getPositionById(int id) throws SQLException;
}
