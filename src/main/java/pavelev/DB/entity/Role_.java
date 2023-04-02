package pavelev.DB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@StaticMetamodel(Role.class)
public class Role_ {
    public static volatile SingularAttribute<Role,String> title;

    public static volatile SetAttribute<Role,User> users;

}
