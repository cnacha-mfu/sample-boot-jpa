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
        // TODO: if repo.findByName(...) is not empty -> 409 CONFLICT
        // TODO: otherwise repo.save(employee) -> 201 CREATED
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> listEmployees() {
        // TODO: return repo.findAll() with 200 OK
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        // TODO: if repo.findById(id) is empty -> 404 NOT FOUND
        // TODO: otherwise return the employee with 200 OK  (hint: Optional.get())
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        // TODO: if the id does not exist -> 404 NOT FOUND
        // TODO: otherwise repo.deleteById(id) -> 200 OK
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
