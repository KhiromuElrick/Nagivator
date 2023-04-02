package pavelev.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pavelev.DB.entity.Role;
import pavelev.DB.entity.User;
import pavelev.DB.entity.User_;
import pavelev.DB.utils.HibernateUtilUpdate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Tests {
    SessionFactory sessionFactory;
    Session session;
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    CriteriaBuilder cb;
    User user;


    @BeforeEach
    void setUp() {
        sessionFactory = HibernateUtilUpdate.getSessionFactory();
        session = sessionFactory.openSession();
        user = new User();
        user.setLogin("danil@ya.ru");
        user.setPassword("123");

        entityManagerFactory = Persistence.createEntityManagerFactory("Navigator");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> query = cb.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root);
        List<Role> roles = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();

        for (Role role : roles) {
            if (role.getTitle().equals("Пользователь,бесплатный")) {
                user.setRole(role);
            }
        }

    }

    @AfterEach
    void tearDown() {
        session.close();
        sessionFactory.close();
    }

    @Test
    void findUser() {
        boolean flag=false;

        entityManagerFactory = Persistence.createEntityManagerFactory("Navigator");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = cb.createQuery(User.class);
        Root<User> rootUser = userQuery.from(User.class);
        userQuery.select(rootUser);
        List<User> users = entityManager.createQuery(userQuery).getResultList();

        for(User user1 :users){
            if(user1.getLogin().equals(user.getLogin())) flag = true;
        }

        assertEquals(true,flag);
    }

    @Test
    void reg() {
        session.getTransaction().begin();



        session.save(user);


        session.getTransaction().commit();
    }
    @Test
    void aut(){
        boolean flag = false;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Navigator");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(User_.login),user.getLogin()));
        List<User> users = entityManager.createQuery(criteria).getResultList();

        for(User user1: users){
            if(user1.getLogin().equals(user.getLogin()));
            flag = true;
        }

        assertEquals(true,flag);
    }
}