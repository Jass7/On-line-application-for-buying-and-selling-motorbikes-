package Controller;


import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")


public class UserController {
    
    @Autowired
    private User user = new User();
    private static SessionFactory factory;

        public UserController() {
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

    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm1(Map<String, Object> model) {
        User userForm = new User();
        model.put("userForm", userForm);

        return new ModelAndView("/login");

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginForm2(HttpSession session, @Valid @ModelAttribute("userForm") User userForm, BindingResult result, Map<String, Object> model, RedirectAttributes redirectAttributes) {

        ValidatorFactory factory1 = Validation.buildDefaultValidatorFactory();
        Validator validator = factory1.getValidator();
        Set<ConstraintViolation<User>> constraintViolations0 = validator.validateProperty(userForm, "email");
        Set<ConstraintViolation<User>> constraintViolations1 = validator.validateProperty(userForm, "password");
        if (!constraintViolations0.isEmpty() || !constraintViolations1.isEmpty()) {
            return new ModelAndView("/login");
        }

        user = authUserHelper(userForm);
        session.setAttribute("user", user);
        if (user.getEmail() == null) {
            model.put("key1", "1");
            return new ModelAndView("/login");
        } else {


        }

        return new ModelAndView("redirect:/index/index.htm");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logOut1(Map<String, Object> model, HttpSession session) {
        this.user = new User();
        session.invalidate();

        return new ModelAndView("redirect:/index/index.htm");

    }

  

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerForm1(Map<String, Object> model, HttpSession session) {
        User userForm = new User();
        model.put("userForm", userForm);
        model.put("user", user);
        return new ModelAndView("/register");

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerForm2(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            return new ModelAndView("/register");
        }

        userForm.setRole("registered");
        String userName = addUserHelper(userForm);
        return new ModelAndView("redirect:/index/index.htm");
    }

    public String addUserHelper(User user) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        UserController uCon = new UserController();
        String userName = uCon.addUser(user.getEmail(), user.getPassword(), user.getFullName(), user.getTellNo(), user.getRole());
        return userName;
    }

    public String addUser(String email, String password, String fullName, String tellNo, String roll) {
        Session session = factory.openSession();
        Transaction tx = null;
        String username = null;
      

       String pw = DigestUtils.md5DigestAsHex(password.getBytes(Charset.forName("UTF-8")));
        
        try {
            tx = session.beginTransaction();
            user.setEmail(email);
            user.setFullName(fullName);
            user.setPassword(pw);
            user.setRole(roll);
            user.setTellNo(tellNo);
            username = (String) session.save(user);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return username;
    }

    public User authUserHelper(User user) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        UserController uCon = new UserController();
        User authUser1 = uCon.authUser(user.getEmail(), user.getPassword());
        return authUser1;
    }

    public User authUser(String email, String password) {
        Session session = factory.openSession();
        Transaction tx = null;
        User user = new User();

        String pw = DigestUtils.md5DigestAsHex(password.getBytes(Charset.forName("UTF-8")));

        try {
            String hql = "FROM User E WHERE E.email = '" + email + "' AND E.password = '" + pw + "'";
            Query query = session.createQuery(hql);
            List<User> results = query.list();
            if (!results.isEmpty()) {
                user = results.get(0);
            }
        } catch (HibernateException e) {

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return user;
    }
}
