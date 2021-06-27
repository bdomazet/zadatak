package jsfbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import zadatak.app.entity.Role;
import zadatak.app.entity.User;
import zadatak.app.entity.facade.RoleFacadeLocal;
import zadatak.app.entity.facade.UserFacadeLocal;

@Named(value = "usersManagedBean")
@RequestScoped
public class UsersManagedBean implements Serializable {

    private List<User> _usersList;
    private String usernameInput;
    private String passwordInput;
    private int roleId;
    private String roleString;

    @Inject
    UserFacadeLocal userFacadeLocal;

    @Inject
    RoleFacadeLocal roleFacadeLocal;

    @PostConstruct
    private void init() {
        _usersList = userFacadeLocal.findAll();
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
        User user = userFacadeLocal.findByUsername(usernameInput);
        if (usernameInput != null && passwordInput != null) {
            if (usernameInput.equals(user.getUsername()) && passwordInput.equals(user.getPassword())) {
                roleString = user.getIdRole().getRole();
            }
        }
        if (roleString.equals("READ")) {
            return "read";
        } else if (roleString.equals("WRITE")) {
            return "write";
        } else {
            return "";
        }
    }

    public String register() {
        User user = new User();
        Role role = roleFacadeLocal.find(roleId);
        user.setIdRole(role);
        user.setUsername(usernameInput);
        user.setPassword(passwordInput);
        userFacadeLocal.create(user);
        return "index";
    }
}
