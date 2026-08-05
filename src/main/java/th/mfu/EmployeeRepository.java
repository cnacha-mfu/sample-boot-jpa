package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Employee;

// Extending CrudRepository gives us save/findById/findAll/deleteById for free —
// Spring Data writes the implementation at runtime; we only declare the interface.
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    // a "derived query": Spring Data reads the METHOD NAME and generates
    // SELECT * FROM employee WHERE name = ?   — no SQL written by us
    List<Employee> findByName(String name);

    List<Employee> findAll();

}
