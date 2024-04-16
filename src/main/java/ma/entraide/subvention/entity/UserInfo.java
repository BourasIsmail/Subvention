package ma.entraide.subvention.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String roles;
    @Column(nullable = false)
    @Size(min = 5)
    private String password;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "deleguation_id")
    private Deleguation deleguation;

    public UserInfo(String name, String email, String roles, String password) {
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }
}
