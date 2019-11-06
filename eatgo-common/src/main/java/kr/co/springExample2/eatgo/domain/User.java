package kr.co.springExample2.eatgo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotNull
    private Long level;

    @NotEmpty
    private String password;

    public boolean isAdmin() {
        return level >= 100;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deactivate() {
       this.level = 0L;
    }

    @JsonIgnore
    public String getAccessToken() {
        if(password == null) {
            return "";
        }
        return password.substring(0,10);
    }
}
