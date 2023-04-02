package pavelev.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pavelev.DB.entity.Role;
import pavelev.DB.entity.User;
import pavelev.DB.entity.User_;
import pavelev.DB.utils.HibernateUtilUpdate;

import java.util.List;
import java.util.Scanner;

public class Authorization {


    public static boolean aut() {

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
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(User_.login),user.getLogin()));
            List<User> roles = entityManager.createQuery(criteria).getResultList();



            session.getTransaction().commit();
        } catch (Exception exception) {

        } finally {
            session.close();
            sessionFactory.close();
        }

        return true;
    }
}
