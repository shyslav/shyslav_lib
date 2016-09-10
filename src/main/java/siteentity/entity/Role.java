package siteentity.entity;

/**
 * Created by shyslav on 9/10/16.
 */
public class Role {
    private int id;
    private String name;
    private String home;
    private String blog;
    private String contacts;
    private String comments;
    private String admin_panel;

    public Role(int id, String name, String home, String blog, String contacts, String comments, String admin_panel) {
        this.id = id;
        this.name = name;
        this.home = home;
        this.blog = blog;
        this.contacts = contacts;
        this.comments = comments;
        this.admin_panel = admin_panel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAdmin_panel() {
        return admin_panel;
    }

    public void setAdmin_panel(String admin_panel) {
        this.admin_panel = admin_panel;
    }
}
