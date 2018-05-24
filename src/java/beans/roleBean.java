package beans;

import dao.RoleDao;
import dao.RoleDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import model.Role;

@Named(value = "roleBean")
@RequestScoped
public class roleBean {

    private List<SelectItem> selectOneItemsRole;

    public roleBean() {
    }

    public List<SelectItem> getSelectOneItemsRole() {
        this.selectOneItemsRole = new ArrayList<SelectItem>();
        RoleDao roleDao = new RoleDaoImpl();
        List<Role> roles = roleDao.selectItems();
        for (Role role : roles) {
            SelectItem selectItem = new SelectItem(role.getId(), role.getName());
            this.selectOneItemsRole.add(selectItem);
        }
        return selectOneItemsRole;
    }
}
