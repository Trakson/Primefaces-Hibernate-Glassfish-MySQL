package beans;

import dao.UserDao;
import dao.UserDaoImpl;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.User;

import org.primefaces.context.RequestContext;
import util.MyUtil;

@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {
    
    private User user;
    private UserDao userDao;
    
    public loginBean(){
        this.userDao = new UserDaoImpl();
        if(this.user == null){
            this.user = new User();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;
        String route = "";
         
        this.user = this.userDao.login(this.user);
        if(this.user != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", this.user.getUsername());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", this.user.getUsername());
            route = MyUtil.basepathlogin()+"views/home.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Username and/or password incorrect");
            if(this.user == null){
                this.user = new User();         
            }
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("route", route);
    } 
    
    public void logout(){
        String route = MyUtil.basepathlogin()+"login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.invalidate();
        
        context.addCallbackParam("loggedOut", true);
        context.addCallbackParam("route", route);
    }
    
}
