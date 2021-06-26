package jsfbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import zadatak.app.entity.Role;
import zadatak.app.entity.User;
import zadatak.app.entity.facade.RoleFacadeLocal;
import zadatak.app.entity.facade.UserFacadeLocal;

@Named(value = "usersManagedBean")
@SessionScoped
public class UsersManagedBean implements Serializable {

    private List<User> _usersList;
    private String usernameInput = null;
    private String passwordInput = null;
    private int ID;
    private int roleId;

    @Inject
    UserFacadeLocal personFacadeLocal;

    @Inject
    RoleFacadeLocal roleFacadeLocal;

    @PostConstruct
    private void init() {
        _usersList = personFacadeLocal.findAll();

    }

    public UsersManagedBean() {
    }

    public List<User> getUsersList() {
        return _usersList;
    }

    public void setUsersList(List<User> _usersList) {
        this._usersList = _usersList;
    }

    public String getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(String usernameInput) {
        this.usernameInput = usernameInput;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String login() {
        ID = 0;
        for (User user : _usersList) {
            if (usernameInput != null && usernameInput.equals(user.getUsername())) {
                if (passwordInput != null && passwordInput.equals(user.getPassword())) {
                    ID = user.getId();
                }
            } else {

            }
        }
        if (ID == 1) {
            return "write";
        } else if (ID == 2) {
            return "read";
        } else {
            return "";
        }
    }

    public String register() {
        Role role = roleFacadeLocal.find(roleId);
        User user = new User();
        user.setIdRole(role);
        user.setUsername(usernameInput);
        user.setPassword(passwordInput);
        personFacadeLocal.create(user);
        return "index";
    }
}
