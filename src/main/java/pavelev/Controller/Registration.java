package pavelev.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pavelev.DB.entity.Role;
import pavelev.DB.entity.User;
import pavelev.DB.utils.HibernateUtilUpdate;

import java.util.List;
import java.util.Scanner;

public class Registration {

    public static void reg() {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите адрес электронной почты");
        String login = in.nextLine();


        System.out.println("Введите пароль");
        String pass = in.nextLine();


        SessionFactory sessionFactory = HibernateUtilUpdate.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();

            User user = new User();
            user.setLogin(login);
            user.setPassword(pass);

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Navigator");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> query = cb.createQuery(Role.class);
            Root<Role> root = query.from(Role.class);
            query.select(root);
            List<Role> roles = entityManager.createQuery(query).getResultList();

            for(Role role: roles){
                if(role.getTitle().equals("Пользователь,бесплатный")){
                    user.setRole(role);
                }
            }
            session.save(user);


            session.getTransaction().commit();
        }
        catch (Exception exception){

        }
        finally {
            session.close();
            sessionFactory.close();
        }






    }
}
