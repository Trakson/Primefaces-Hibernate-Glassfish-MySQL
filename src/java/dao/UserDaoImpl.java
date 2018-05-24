package dao;

import java.util.List;
import model.User;
import org.hibernate.Session;
import util.HibernateUtil;

public class UserDaoImpl implements UserDao{

    @Override
    public User findByUser(User user) {
        User mode = null;
        Session session  = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM User WHERE username = '"+user.getUsername()+"'";
        try{
            session.beginTransaction();
            mode = (User) session.createQuery(sql).uniqueResult();
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }
        return mode;
    }

    @Override
    public User login(User user) {
        User mode = this.findByUser(user);
        if(mode != null){
            if(!user.getPassword().equals(mode.getPassword())){
                mode = null;
            }
        }
        return mode;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = null;
        Session session  = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM User";
        try{
            session.beginTransaction();
            userList = session.createQuery(sql).list();
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }
        return userList;
    }
    
    public boolean create(User user) {
        boolean test;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            test = false;
            session.getTransaction().rollback();
        }
        return test;
    }

    public boolean update(User user) {
        boolean test;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            User userup = (User) session.load(User.class, user.getId());
            userup.setEmail(user.getEmail());
            userup.setUsername(user.getUsername());
            userup.setRole(user.getRole());
            session.update(userup);
            session.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            test = false;
            session.getTransaction().rollback();
        }
        return test;
    }

    public boolean delete(Integer id) {
        boolean test;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            test = false;
            session.getTransaction().rollback();
        }
        return test;
    }
    
}
