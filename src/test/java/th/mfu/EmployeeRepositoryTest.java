package th.mfu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import th.mfu.domain.Employee;

// This test is already complete — read it together, then make it pass.
// @DataJpaTest starts ONLY the JPA slice on a fresh in-memory H2 database
// and rolls back after each test. The lab's UserRepositoryTest grades you
// exactly this way.
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repo;

    @Test
    public void testSaveAndFindById() {
        Employee emp = new Employee();
        emp.setName("Alice");
        emp.setEmail("alice@example.com");
        Employee saved = repo.save(emp);

        Optional<Employee> found = repo.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
    }

    @Test
    public void testFindByName() {
        Employee emp = new Employee();
        emp.setName("Bob");
        emp.setEmail("bob@example.com");
        repo.save(emp);

        List<Employee> found = repo.findByName("Bob");

        assertEquals(1, found.size());
        assertEquals("bob@example.com", found.get(0).getEmail());
    }

    @Test
    public void testDelete() {
        Employee emp = new Employee();
        emp.setName("Carol");
        emp.setEmail("carol@example.com");
        Employee saved = repo.save(emp);

        repo.delete(saved);

        assertFalse(repo.findById(saved.getId()).isPresent());
    }
}
