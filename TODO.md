# In-Class Demonstration Flow — Spring Data JPA (one entity)

This repository has these branches:

- **`main`** — the in-class version. Some code is blanked out with `TODO` comments. We fill these in together during the demonstration.
- **[`complete`](https://github.com/cnacha-mfu/sample-boot-jpa/tree/complete)** — the finished code for every step. Use it to catch up or check your work.
- **[`relations`](https://github.com/cnacha-mfu/sample-boot-jpa/tree/relations)** — a bigger multi-entity example (OneToOne, ManyToMany, …) for a later week; ignore it today.

**Start the application first** and restart it after each change:

```
mvn spring-boot:run
```

> **Postman:** import [`sample-boot-jpa.postman_collection.json`](sample-boot-jpa.postman_collection.json) — every request used below, in order, with status-code tests.

---

## Step 1: The moving parts — read the pom and the properties

**Files:** `pom.xml`, `src/main/resources/application.properties` (both already complete — read together)

**Idea:** `spring-boot-starter-data-jpa` brings Hibernate (the ORM) and Spring Data; `h2` is an in-memory database that lives and dies with the app — nothing to install. The properties tell Hibernate to **create the tables from our classes** at startup (`ddl-auto=create-drop`), print every SQL statement (`show-sql`), and load `data.sql` after the tables exist.

**Try it:** run the app — it fails! `Not a managed type: class th.mfu.domain.Employee`. Perfect: nothing is an entity yet. That's Step 2.

---

## Step 2: Turn a class into a table — @Entity

**File:** `src/main/java/th/mfu/domain/Employee.java`

**Idea:** three annotations turn a POJO into a database table: `@Entity` (make a table from this class), `@Id` (this attribute is the primary key), `@GeneratedValue` (the database assigns it). Columns come from the attributes — no SQL written by us.

**Fill in together:** add `@Entity` on the class; `@Id` and `@GeneratedValue(strategy = GenerationType.AUTO)` on `id`.

**Try it:** run again. In the console find Hibernate's `create table employee ...` and the inserts from `data.sql`. Open the **H2 console** at <http://localhost:8080/h2-console> (JDBC URL `jdbc:h2:mem:testdb`, user `sa`, password `password`) and `SELECT * FROM EMPLOYEE` — the class became a real table with 5 rows.

**Solution:** [Employee](https://github.com/cnacha-mfu/sample-boot-jpa/blob/complete/src/main/java/th/mfu/domain/Employee.java)

---

## Step 3: The repository — queries from method names

**File:** `src/main/java/th/mfu/EmployeeRepository.java` (already complete — read together)

**Idea:** extend `CrudRepository<Employee, Integer>` and Spring Data hands you `save`, `findById`, `findAll`, `deleteById` — implemented at runtime, no class written by us. Better: declare `List<Employee> findByName(String name)` and Spring Data **derives the SQL from the method name**. This is the interface the lab asks you to complete.

---

## Step 4: The controller uses the repository, not a HashMap

**File:** `src/main/java/th/mfu/EmployeeController.java`

**Idea:** same REST patterns as two weeks ago (201/409/404, `@RequestBody`, `@PathVariable`) — but the data now survives in a database, through the injected (`@Autowired`) repository. Compare every line with the HashMap version: only the storage calls changed.

**Fill in together:**
1. `registerEmployee` — `repo.findByName(...)` not empty → `409 CONFLICT`; else `repo.save(employee)` → `201 CREATED`.
2. `listEmployees` — `repo.findAll()` → `200 OK`.
3. `getEmployee` — `repo.findById(id)` empty → `404 NOT FOUND`; else the employee → `200 OK`.
4. `deleteEmployee` — id missing → `404`; else `repo.deleteById(id)` → `200 OK`.

**Try it (Postman collection, or curl):** register a new employee, register the same name again (409), list (your row + the 5 from `data.sql`), get by id, get id `1` (404), delete one — then check the H2 console: the row is really gone.

**Solution:** [EmployeeController](https://github.com/cnacha-mfu/sample-boot-jpa/blob/complete/src/main/java/th/mfu/EmployeeController.java)

---

## Step 5: @DataJpaTest — how the lab grades you

**File:** `src/test/java/th/mfu/EmployeeRepositoryTest.java` (already complete — read it together)

**Idea:** `@DataJpaTest` starts only the JPA slice on a fresh H2 database and rolls back after each test — fast, isolated, no server. The tests exercise `save`, `findById`, `findByName`, `delete` — exactly the methods the lab's `UserRepositoryTest` checks on every push via GitHub Actions.

**Try it:**

```
mvn test -Dtest=EmployeeRepositoryTest
```

Run it **before** Step 2 (red — `Employee` is not an entity) and **after** (green). Red → implement → green is the rhythm of the lab.

**Solution:** [EmployeeRepositoryTest](https://github.com/cnacha-mfu/sample-boot-jpa/blob/complete/src/test/java/th/mfu/EmployeeRepositoryTest.java)

---

## After class

You are now ready for the graded lab: [lab-web-jpa](https://github.com/maefahluang-uni/lab-web-jpa) — a **user registration API backed by a real database**, built with exactly these pieces:

| Lab requirement | Demo step |
|---|---|
| `User` entity: `@Entity`, `@Id`, `@GeneratedValue` | Step 2 (`Employee`) |
| `UserRepository`: declare `findAll()` + `findByUsername(...)` | Step 3 |
| `data.sql` dummy rows — find it and explain it | Steps 1–2 |
| Controller register 201/409, get 200/404, list, delete via repository | Step 4 |
| Graded by `UserRepositoryTest` (`mvn verify`, `@DataJpaTest`) | Step 5 |

> **Tip:** if `mvn spring-boot:run` fails with `Port 8080 was already in use`, stop the other server first. The H2 console needs the app running — it is not a separate program.
