package ma.entraide.subvention.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String roles;
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
