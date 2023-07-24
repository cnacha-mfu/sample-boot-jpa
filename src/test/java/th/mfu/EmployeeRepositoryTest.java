package th.mfu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import th.mfu.domain.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testQuery(){
        employeeRepository.deleteAll();
        Employee employee1 = new Employee();
        employee1.setName("test1@test.com");
        employee1.setEmail("test1@test.com");
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setName("test2@test.com");
        employee2.setEmail("test2@test.com");
        employeeRepository.save(employee2);

        // find sum of all employees
        Iterator<Employee> emps = employeeRepository.findAll().iterator();
        int sum = 0;
        while(emps.hasNext()){
            sum++;
            emps.next();
        }
        assertEquals(2, sum);
    }
}
