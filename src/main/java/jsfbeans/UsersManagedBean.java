package jsfbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import zadatak.app.entity.Role;
import zadatak.app.entity.Users;
import zadatak.app.entity.facade.UsersFacadeLocal;

@Named(value = "usersManagedBean")
@SessionScoped
public class UsersManagedBean implements Serializable {

    private List<Users> _usersList;
    private String usernameInput = null;
    private String passwordInput = null;
    private int ID;
    private int roleId;

    @Inject
    UsersFacadeLocal personFacadeLocal;

    @PostConstruct
    private void init() {
        _usersList = personFacadeLocal.findAll();

    }

    public UsersManagedBean() {
    }

    public List<Users> getUsersList() {
        return _usersList;
    }

    public void setUsersList(List<Users> _usersList) {
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
        for (Users user : _usersList) {
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

    public void register() {
        for (Users userTemp : _usersList) {
            if (!userTemp.getUsername().equals(usernameInput)) {
                if (usernameInput != null && passwordInput != null) {
                    if (!usernameInput.isEmpty() && !passwordInput.isEmpty()) {
                        if (roleId > 0 && roleId < 3) {
                            Role newRole = new Role(roleId);
                            Users user = new Users(null, usernameInput, passwordInput, newRole);
                            personFacadeLocal.create(user);
                        }
                    }
                }
            }
        }
    }
}
