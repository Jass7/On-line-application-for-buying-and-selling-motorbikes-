package Controller;

import javax.servlet.http.HttpSession;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")

public class IndexController {

    @Autowired
    private User user = new User();
    private SessionFactory factory;

    public IndexController() {
        {
            try {
                factory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
    }

    public SessionFactory getFact() {
        return factory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(final Model model, HttpSession session) {

        if (session.getAttribute("user") != null) {
            User user1 = (User) session.getAttribute("user");
            this.user = user1;
        } else {
            this.user = new User();
        }

        model.addAttribute("user", user);
        return new ModelAndView("/index");
    }

}
