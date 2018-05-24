package dao;

import java.util.List;
import model.Role;
import org.hibernate.Session;
import util.HibernateUtil;

public class RoleDaoImpl implements RoleDao{

    @Override
    public List<Role> selectItems() {
        List<Role> liste = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Role";
        try {
            session.beginTransaction();
            liste =  session.createQuery(sql).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return liste;
    }
    
}
