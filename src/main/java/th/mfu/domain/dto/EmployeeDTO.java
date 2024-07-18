package th.mfu.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {
    private Integer id;

    @JsonProperty("fullName")
    private String name;

    @JsonProperty("emailAddress")
    private String email;
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

    
}
