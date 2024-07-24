package th.mfu;

import java.util.Collection;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Employee;
import th.mfu.domain.Position;
import th.mfu.domain.dto.EmployeeDTO;
import th.mfu.domain.dto.EmployeeMapper;

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

    @Autowired
    EmployeeMapper empDTOMapper;

    @PatchMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO emp, @PathVariable int id){
      //  EmployeeMapper mapper = 
      Optional<Employee> foundEmp = employeeRepository.findById(id);
      if(!foundEmp.isPresent())https://desktop.postman.com/?desktopVersion=11.4.0&userId=2189289&teamId=0&region=us
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      // get employee
      Employee empToUpdate = foundEmp.get();
      // update some fields from DTO
      empDTOMapper.updateEmployeeFromDto(emp, empToUpdate);
      employeeRepository.save(empToUpdate);
     
      return ResponseEntity.ok("employee is updated");

    }
    
}
