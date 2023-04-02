package pavelev.DB.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<Model,Integer> id;
    public static volatile SingularAttribute<User,String> login;
    public static volatile SingularAttribute<User,String> password;
    public static volatile SingularAttribute<User,Role> role;
    public static volatile SetAttribute<User,Place> userPlacies;

}
