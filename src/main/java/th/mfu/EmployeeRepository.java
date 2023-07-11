package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByName(String name);

    List<Employee> findAll();



}
