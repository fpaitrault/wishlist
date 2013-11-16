package org.fpaitrault.viewmdl;

import java.util.List;

import org.fpaitrault.AuthenticationService;
import org.fpaitrault.dao.UserDAO;
import org.fpaitrault.mdl.User;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

public class MainWindow {
	
    private UserDAO users = new UserDAO();
    private User user = AuthenticationService.instance().getUserCredential();

	public List<User> getUsers() {
		return users.readAll();
	}

	@Command
	public void logout() {
		AuthenticationService.instance().logout();
        Executions.sendRedirect("/login.zul");
	}

    public User getUser() {
        return user;
    }

}
