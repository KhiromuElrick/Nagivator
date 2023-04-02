package pavelev.DB.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "users", schema = "navigatordb")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model {
    @NotNull
    @Column(name = "user_login",nullable = false, length = 64)
    private String login;

    @NotNull
    @Column(name = "user_password",nullable = false, length = 64)
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Place> userPlacies = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(userPlacies, user.userPlacies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, userPlacies);
    }
}
