package beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "menuBean")
@RequestScoped
public class menuBean {

    /**
     * Creates a new instance of menuBean
     */
    public menuBean() {
    }
    
    public void save(){
        addMessage("Data saved");
    }
    
    public void update(){
        addMessage("Data updated");
    }
    
    public void delete(){
        addMessage("Data deleted");
    }
    
    public void addMessage(String summary){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
