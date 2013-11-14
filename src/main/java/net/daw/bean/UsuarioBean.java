package net.daw.bean;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v1.0
 * @since mie, 12 noviembre 2013
 */
public class UsuarioBean {

    private int id = 0;
    private String login = "";
    private String password = "";

    public UsuarioBean() {

    }
    
    public UsuarioBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
