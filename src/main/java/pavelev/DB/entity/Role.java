package pavelev.DB.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_roles", schema = "navigatordb")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Model {
    @NotNull
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "role")
    private Set<User> users=new HashSet<>();


}
