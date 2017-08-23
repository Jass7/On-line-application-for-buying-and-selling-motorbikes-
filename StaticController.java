package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.Enquiry;
import model.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/static")

public class StaticController {

    @Autowired
    private User user = new User();
    private static SessionFactory factory;

    public StaticController() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
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

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView index(final Model model, HttpSession session) {

        if (session.getAttribute("user") != null) {
            this.user = (User) session.getAttribute("user");
        } else {
            this.user = new User();
        }
        model.addAttribute(user);
        //  model.addAttribute("email", user.getEmail());
        return new ModelAndView("/news");

    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView contact1(final Model model, HttpSession session) {

        if (session.getAttribute("user") != null) {
            this.user = (User) session.getAttribute("user");
        } else {
            this.user = new User();
        }
        List<String> enqType1 = new ArrayList<>();
        enqType1.add("GENERAL ISSUES");
        enqType1.add("SALE");
        enqType1.add("SUPPORT");
        model.addAttribute("enqType", enqType1);
        model.addAttribute("enqForm", new Enquiry());
        model.addAttribute(user);
        return new ModelAndView("/contact");

    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView contact2(@Valid @ModelAttribute("userForm") Enquiry enqForm, BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            return new ModelAndView("/contact");
        }

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);
            session.save(enqForm);

            tx.commit();

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                enqForm.error("Couldn’t roll back transaction", rbe);
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return new ModelAndView("redirect:/index/index.htm");
    }
}
