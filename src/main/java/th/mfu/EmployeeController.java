package th.mfu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Employee;

@RestController
public class EmployeeController {

    // Spring creates the repository implementation and injects it here
    @Autowired
    private EmployeeRepository repo;

    @PostMapping("/employees")
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {
        if (!repo.findByName(employee.getName()).isEmpty()) {
            return new ResponseEntity<>("Employee name already exists", HttpStatus.CONFLICT);
        }
        repo.save(employee);
        return new ResponseEntity<>("Employee registered successfully", HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> listEmployees() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        if (!repo.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        if (!repo.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repo.deleteById(id);
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }

}
