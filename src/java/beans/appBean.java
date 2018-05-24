package beans;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import util.MyUtil;

/**
 *
 * @author islab-kc-319
 */
@Named(value = "appBean")
@ApplicationScoped
public class appBean {

    public appBean() {
    }
    
    public String getBaseUrl(){
        return MyUtil.baseurl();
    }
    
    public String getBasePath(){
        return MyUtil.basepath();
    }
    
}
