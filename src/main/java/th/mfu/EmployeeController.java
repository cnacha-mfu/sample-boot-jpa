package th.mfu;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Employee;
import th.mfu.domain.Position;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @PostMapping("/employees/register")
    public ResponseEntity<String> registerUser(@RequestBody Employee emp) {

        if (employeeRepository.findByName(emp.getName()).size() > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        } 

        employeeRepository.save(emp);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/employees")
    public Collection<Employee> list() {
        
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{name}")
    public Employee getEmployee(@PathVariable String name) {

        return employeeRepository.findByName(name).get(0);
    }

    @PostMapping("/positions/new")
    public ResponseEntity<String> newPosition(@RequestBody Position pos) {

        positionRepository.save(pos);
        return ResponseEntity.ok("position added successfully");
    }
    
}
