package epam.rostislav.controller;

import epam.rostislav.dto.Position;
import epam.rostislav.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/company")
public class CompanyController {
    final private PositionService positionService;

    @Autowired
    public CompanyController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/add_position")
    public void addPosition(@RequestBody Position position) throws SQLException {
        positionService.addPosition(position);
    }

    @GetMapping("/all_positions")
    public ArrayList<Position> getAllPositions() throws SQLException {
        return positionService.getAllPositions();
    }
}
