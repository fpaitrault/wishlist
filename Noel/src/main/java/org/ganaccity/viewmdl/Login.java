package org.ganaccity.viewmdl;

import org.ganaccity.AuthenticationService;
import org.ganaccity.mdl.User;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

public class Login {
    private boolean firstLogin = false;
    private String name = "";
    private String password = "";
    private String passwdConfirm = "";
    private String passwdErrorMsg = "";

    public boolean getFirstLogin() {
        return firstLogin;
    }
    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
    public String getPasswdConfirm() {
        return passwdConfirm;
    }
    public void setPasswdConfirm(String passwdConfirm) {
        this.passwdConfirm = passwdConfirm;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswdErrorMsg() {
        return passwdErrorMsg;
    }
    public void setPasswdErrorMsg(String passwdErrorMsg) {
        this.passwdErrorMsg = passwdErrorMsg;
    }

    @Command
    @NotifyChange("firstLogin")
    public void login() {
        if(AuthenticationService.instance().isFirstLogin(getName()))
            setFirstLogin(true);
        else
        {
            if(AuthenticationService.instance().login(getName(), getPassword()))
                Executions.sendRedirect("/");
        }
    }
    
    @NotifyChange("passwdErrorMsg")
    @Command
    public void firstLogin() {
        if(!getPassword().equals(getPasswdConfirm()))
        {
            setPasswdErrorMsg("Les mots de passe ne correspondent pas");
            return;
        }
        //Update password on database
        AuthenticationService.instance().updatePassword(getName(), getPassword());
        //Login application
        if(AuthenticationService.instance().login(getName(), getPassword()))
            Executions.sendRedirect("/");
    }
}