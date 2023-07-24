package th.mfu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity // This tells Hibernate to make a table out of this class
public class Employee {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  @OneToOne( cascade = CascadeType.PERSIST)
  @JoinColumn(name = "acc_id", referencedColumnName = "id")
  private Account account;

  @ManyToOne( cascade = CascadeType.MERGE)
  @JoinColumn(name = "position_id", referencedColumnName = "id")
  private Position position;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "employee_role", 
           joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id"), 
           inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<Role> roles;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  
}
