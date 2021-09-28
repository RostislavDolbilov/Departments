package company.service;

import company.dao.impl.PositionDaoImpl;
import company.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class PositionService{
    private final PositionDaoImpl positionDao;


    @Autowired
    public PositionService(PositionDaoImpl positionDao) {
        this.positionDao = positionDao;
    }

    public void addPosition(Position position) throws SQLException {
        positionDao.addPosition(position);
    }

    public List<Position> getAllPositions() throws SQLException {
        return positionDao.getAllPositions();
    }

    Position getPositionById(int id) throws SQLException{
        return positionDao.getPositionById(id);
    }
}
