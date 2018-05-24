package dao;

import java.util.List;
import model.User;

public interface UserDao {
    
    public User findByUser(User user);
    public User login(User user);
    public List<User> findAll();
    public boolean create(User user);
    public boolean update(User user);
    public boolean delete(Integer id);
};
