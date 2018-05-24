package beans;

import dao.UserDao;
import dao.UserDaoImpl;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.User;


@Named(value = "userBean")
@RequestScoped
public class userBean {

    private List<User> users;
    private User selectedUser;
    
    public userBean() {
        this.selectedUser = new User();
        this.users = new ArrayList<User>();
    }

    public List<User> getUsers() {
        UserDao userDao = new UserDaoImpl();
        this.users = userDao.findAll();
        return users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public void btnCreateUser(ActionEvent actionEvent){
        UserDao userDao = new UserDaoImpl();
        String msg;
        this.selectedUser.setPassword(this.selectedUser.getUsername());
        this.selectedUser.setUsercreation("admin");
        Date cdate = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cdate);
        this.selectedUser.setDatecreation(java.sql.Date.valueOf(date));
        if (userDao.create(this.selectedUser)) {
            msg = "User was created correctly.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error creating the user.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnUpdateUser(ActionEvent actionEvent)
    {
        UserDao userDao = new UserDaoImpl();
        String msg;
        if (userDao.update(this.selectedUser)) {
            msg = "User was modified correctly.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error modifying the user.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteUser(ActionEvent actionEvent)
    {
        UserDao userDao = new UserDaoImpl();
        String msg;
        if (userDao.delete(this.selectedUser.getId())) {
            msg = "User was deleted correctly.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error deleting theuser.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
  
}
