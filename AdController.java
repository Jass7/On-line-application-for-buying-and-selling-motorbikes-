package Controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;
import java.util.Map;
import model.Ad;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.Bike;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/ad")
@Controller
public class AdController {

    private static SessionFactory factory;
    @Autowired
    private User user = new User();

    public AdController() {
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

    public void setUser(User user) {
        this.user = user;
    }

    @RequestMapping(value = "/give-ad", method = RequestMethod.GET)
    public ModelAndView giveAdd1(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            this.user = (User) session.getAttribute("user");
        } else {
            return new ModelAndView("redirect:/user/login.htm");
        }
        Ad adForm = new Ad();
        model.put("colorList", getColorList());
        model.put("genConditionList", getGenConditionList());
        model.put("adForm", adForm);
        model.put("user", user);
        return new ModelAndView("/give-ad");

    }

    @RequestMapping(value = "/give-ad", method = RequestMethod.POST)
    public ModelAndView registerForm2(@Valid @ModelAttribute("adForm") Ad adForm, BindingResult result, Map<String, Object> model, @RequestParam("photo") MultipartFile file) {
        if (result.hasErrors()) {
            return new ModelAndView("/give-ad");
        }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                adForm.getBike().setPhoto(bytes);
                Date date = new Date();
                adForm.setAdPostDate(date);
                adForm.getBike().getUser().setEmail(this.user.getEmail());

            } catch (Exception e) {

            }
        } else {

        }

        int adId = addAdHelper(adForm);
        return new ModelAndView("redirect:/index/index.htm");
    }

    @RequestMapping(value = "/get-ad", method = RequestMethod.GET)
    public ModelAndView getAdd1(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            this.user = (User) session.getAttribute("user");
        } else {
            return new ModelAndView("redirect:/user/login.htm");
        }
        List<Ad> ads = this.getAdd(user.getEmail());
        model.put("ads", ads);
        model.put("user", user);
        return new ModelAndView("/get-ad");

    }

    @RequestMapping(value = "/search-pre", method = RequestMethod.GET)
    public ModelAndView search1(Map<String, Object> model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            this.user = (User) session.getAttribute("user");
        } else {
            this.user = new User();
        }
        List<String> colorList = getColorList();
        List<String> modelList = getModelList();
        List<String> yOfProList = getYearOfProList();
        List<String> mileList = getMileageList();
        List<String> countryList = getCountryList();
        List<String> cityList = getCityList();
        List<String> postTimeList = getPostTimeList();

        model.put("modelList", modelList);
        model.put("colorList", colorList);
        model.put("mileList", mileList);
        model.put("countryList", countryList);
        model.put("cityList", cityList);
        model.put("yOfProList", yOfProList);
        model.put("postTimeList", postTimeList);
        model.put("adForm", new Ad());
        model.put("user", user);
        return new ModelAndView("/search-pre");

    }

    @RequestMapping(value = "/search-pre", method = RequestMethod.POST)
    public ModelAndView search2(@ModelAttribute("adForm") Ad adForm, BindingResult result, Map<String, Object> model, @RequestParam("to-limit") String toLimit, RedirectAttributes redirectAttributes) {
        List map = new ArrayList();
        map = search3(adForm, toLimit);
        model.put("myBikes", map);

        redirectAttributes.addFlashAttribute("myBikes", map);
        return new ModelAndView("redirect:/ad/search-result.htm");
    }

    public List search3(Ad adForm, String toLimit) {
        String left = null;
        String right = null;
        List result = new ArrayList();
        Session session = this.getFact().openSession();
        Transaction tx = null;
        String myQuery = getRequiredSql(adForm, toLimit);
        try {

            tx = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(myQuery);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            result = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    public String getRequiredSql(Ad adForm, String toLimit) {
        String temp;
        String result1 = "select a.ad_id, a.ad_post_date,b.model,b.year_of_pro , b.price from ad a, bike b where a.reg_id = b.reg_id AND b.email = a. email";
        String result = "";
        if (!adForm.getBike().getColor().isEmpty()) {
            temp = " AND b.color = '";
            temp += adForm.getBike().getColor();
            temp += "'";
            result += temp;
        }
        if (!adForm.getBike().getModel().isEmpty()) {
            temp = " AND b.model = '";
            temp += adForm.getBike().getModel();
            temp += "'";
            result += temp;

        }

        if (adForm.getBike().getYearOfPro() != 0) {
            temp = " AND b.year_of_pro = '";
            temp += adForm.getBike().getYearOfPro();
            temp += "'";
            result += temp;

        }
        if (!adForm.getBike().getCity().isEmpty()) {
            temp = " AND b.city = '";
            temp += adForm.getBike().getCity();
            temp += "'";
            result += temp;

        }
        if (!adForm.getBike().getCountry().isEmpty()) {
            temp = " AND b.country = '";
            temp += adForm.getBike().getCountry();
            temp += "'";
            result += temp;

        }
        if (adForm.getBike().getPrice() != null) {
            temp = " AND b.price BETWEEN ";
            temp += adForm.getBike().getPrice();
            temp += " AND ";
            temp += toLimit;
            result += temp;

        } else if (!toLimit.isEmpty()) {
            temp = " AND b.price BETWEEN ";
            temp += "0";
            temp += " AND ";
            temp += toLimit;
            result += temp;
        } else {

        }

        result1 += result;
        return result1;
    }

    @RequestMapping(value = "/search-result", method = RequestMethod.GET)
    public ModelAndView searchingResult(Map<String, Object> model) {
        if (this.user.getEmail() == null || this.user.getEmail() != null) {


        }

        List bikes = (List) model.get("myBikes");
        model.put("bikes", bikes);
        model.put("user", user);

        return new ModelAndView("/search-result");

    }

    public List<String> getCountryList() {
        List<String> myList = new ArrayList<>();
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT DISTINCT country from bike";
            SQLQuery query = session.createSQLQuery(sql);
            myList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return myList;
    }

    public List<String> getPostTimeList() {
        List<String> myList = new ArrayList<>();
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT DISTINCT ad_post_date from ad";
            SQLQuery query = session.createSQLQuery(sql);
            myList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return myList;
    }

    public List<String> getCityList() {
        List<String> myList = new ArrayList<>();
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT DISTINCT city from bike";
            SQLQuery query = session.createSQLQuery(sql);
            myList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return myList;
    }

    public List<String> getMileageList() {
        List<String> myList = new ArrayList<>();
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT DISTINCT mileage from bike";
            SQLQuery query = session.createSQLQuery(sql);
            myList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());

        } finally {
            session.close();
        }

        return myList;
    }

    public List<String> getYearOfProList() {
        List<String> myList = new ArrayList<>();
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT DISTINCT year_of_pro from bike";
            SQLQuery query = session.createSQLQuery(sql);
            myList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return myList;
    }

    public List<String> getColorList() {
        List<String> colorList = new ArrayList<>();
        colorList.add("RED");
        colorList.add("BLACK");
        colorList.add("BLUE");
        colorList.add("GREEN");
        colorList.add("YELLOW");
        colorList.add("GREY");
        colorList.add("WHITE");
        colorList.add("ORANGE");
        colorList.add("SILVER");
        colorList.add("GOLD");
        colorList.add("OTHER");
        return colorList;
    }

        public List<String> getGenConditionList() {
        List<String> getGenCondition = new ArrayList<>();
        getGenCondition.add("EXCELLENT");
        getGenCondition.add("VERY GOOD");
        getGenCondition.add("GOOD");
        getGenCondition.add("FAIR");
        getGenCondition.add("POOR");
        getGenCondition.add("OTHER");
        return getGenCondition;
    }
    public List<String> getModelList() {
        List<String> myList = new ArrayList<>();
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT DISTINCT model from bike";
            SQLQuery query = session.createSQLQuery(sql);
            myList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return myList;
    }

    public int addAdHelper(Ad ad) {
        AdController uCon = new AdController();
        int adId = uCon.addAd(ad);
        return adId;
    }

    public List getAdd(String email) {
        List<Ad> adLst = null;
        List adList = null;
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT a.ad_id,a.ad_post_date, a.ad_validity_date, b.city, b.color, b.country, b.gen_condition, b.mileage, b.model, b.photo, b.price, b.reg_id, b.temp_note, b.validity_of_reg, b.year_of_pro, u.email, u.full_name, u.tell_no FROM ad a, bike b, USER u WHERE u.email = b.email AND b.reg_id = a.reg_id AND b.email = a.email AND u.email = '" + email + "'";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            adList = query.list();
            tx.commit();
            Map<String, Object> map;
            for (int i = 0; i < adList.size(); i++) {
                map = (Map<String, Object>) adList.get(i);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("photo")) {
                        byte[] encodedBytes;
                        if (entry.getValue() != null) {
                            encodedBytes = Base64.encodeBase64((byte[]) entry.getValue());

                            entry.setValue(("data:image/jpg;base64," + new String(encodedBytes)));
                            int j = 0;
                        }
                    }

                }
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return adList;
    }

    public int addAd(Ad ad) {
        Session session = this.getFact().openSession();
        Transaction tx = null;
        int adId = -1;
        Bike b1 = ad.getBike();

        b1.getId().setEmail(b1.getUser().getEmail());
        try {
            tx = session.beginTransaction();

            List<User> userLst = session.createQuery("FROM User where email = '" + ad.getBike().getId().getEmail() + "'").list();
            User user1 = userLst.get(0);
            b1.setUser(user1);

            user1.getBikes().add(b1);
            ad.getId().setEmail(b1.getUser().getEmail());
            ad.getId().setRegId(b1.getId().getRegId());
            session.save(b1);
            ad.setBike(b1);
            b1.getAds().add(ad);
            session.save(ad);


            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return adId;
    }

    @RequestMapping(value = "/update-ad", method = RequestMethod.GET)
    public ModelAndView updateAd1(Map<String, Object> model, HttpSession session, @RequestParam("id") int id) {
        if (session.getAttribute("user") != null) {
            this.user = (User) session.getAttribute("user");
        } else {
            return new ModelAndView("redirect:/user/login.htm");
        }
        List<Ad> ads = this.getAdFromId(id);
        model.put("ads", ads);
        model.put("user", user);
        model.put("adForm", new Ad());
        return new ModelAndView("/update-ad");

    }

    @RequestMapping(value = "/update-ad", method = RequestMethod.POST)
    public ModelAndView updateAd2(@Valid @ModelAttribute("adForm") Ad adForm, BindingResult result, Map<String, Object> model, @RequestParam("photo") MultipartFile file) {
        if (result.hasErrors()) {
            return new ModelAndView("/give-ad");
        }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                adForm.getBike().setPhoto(bytes);


            } catch (Exception e) {

            }
        } else {

        }

        int adId = updateAdHelper(adForm);
        return new ModelAndView("redirect:/index/index.htm");
    }

    public int updateAdHelper(Ad ad) {
        Session session = this.getFact().openSession();
        Transaction tx = null;
        String reg_id = "";
        String email = "";
        List<Ad> adList = this.getAdFromId(ad.getId().getAdId());
        Map<String, Object> map1;
        for (int i = 0; i < adList.size(); i++) {
            map1 = (Map<String, Object>) adList.get(i);
            for (Map.Entry<String, Object> entry : map1.entrySet()) {
                if (entry.getKey().equals("reg_id")) {
                    String val = entry.getValue().toString();
                    reg_id = val;
                } else if (entry.getKey().equals("email")) {
                    email = (String) entry.getValue();
                }
            }

        }
        try {
            String hql = "DELETE FROM Bike "
                    + "WHERE id.regId = '" + reg_id + "' AND email = '" + email + "'";
            Query query = session.createQuery(hql);

            tx = session.beginTransaction();
            int result = query.executeUpdate();

            Bike b1 = ad.getBike();
            List<User> userLst = session.createQuery("FROM User where email = '" + ad.getBike().getId().getEmail() + "'").list();
            User user1 = userLst.get(0);
            b1.setUser(user1);
          b1.getId().setRegId(ad.getId().getRegId());
            user1.getBikes().add(b1);
            ad.getId().setRegId(b1.getId().getRegId());
            session.save(b1);
            ad.setBike(b1);
            b1.getAds().add(ad);
            session.save(ad);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return 0;
    }

    public List getAdFromId(int id) {
        List<Ad> adLst = null;
        List adList = null;
        Session session = this.getFact().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT a.ad_id, a.ad_post_date, a.ad_validity_date, b.city, b.color, b.country, b.gen_condition, b.mileage, b.model, b.photo, b.price, b.reg_id, b.temp_note, b.validity_of_reg, b.year_of_pro, u.email, u.full_name, u.tell_no FROM ad a, bike b, USER u WHERE u.email = b.email AND b.reg_id = a.reg_id AND b.email = a.email AND a.ad_id= '" + id + "'";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            adList = query.list();

            tx.commit();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date startDate;
            Date endDate;

            Map<String, Object> map1;
            for (int i = 0; i < adList.size(); i++) {
                map1 = (Map<String, Object>) adList.get(i);
                for (Map.Entry<String, Object> entry : map1.entrySet()) {
                    if (entry.getKey().equals("ad_validity_date") || entry.getKey().equals("validity_of_reg")) {
                        endDate = (Date) entry.getValue();

                        entry.setValue(df.format(endDate));
                    } else if (entry.getKey().equals("ad_post_date")) {
                        startDate = (Date) entry.getValue();

                        entry.setValue(df1.format(startDate));
                    }
                }

            }

            Map<String, Object> map;
            for (int i = 0; i < adList.size(); i++) {
                map = (Map<String, Object>) adList.get(i);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("photo")) {
                        byte[] encodedBytes;
                        if (entry.getValue() != null) {
                            encodedBytes = Base64.encodeBase64((byte[]) entry.getValue());

                            entry.setValue(("data:image/jpg;base64," + new String(encodedBytes)));
                            int j = 0;
                        }
                    }

                }
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return adList;
    }
     @RequestMapping(value = "/preview-ad", method = RequestMethod.GET)
    public ModelAndView previewAd1(Map<String, Object> model, HttpSession session, @RequestParam("id") int id) {
        if (session.getAttribute("user") != null) {
            this.user = (User) session.getAttribute("user");
        } 
        List<Ad> ads = this.getAdFromId(id);
        model.put("ads", ads);
        model.put("user", user);
        model.put("adForm", new Ad());
        return new ModelAndView("/preview-ad");

    }
     @RequestMapping(value = "/delete-ad", method = RequestMethod.GET)
    public ModelAndView DelAd1(Map<String, Object> model, HttpSession session1, @RequestParam("id") int id) {
        if (session1.getAttribute("user") != null) {
            this.user = (User) session1.getAttribute("user");
        } 
        
       Session session = this.getFact().openSession();
        Transaction tx = null;
        String reg_id = "";
        String email = "";
        List<Ad> adList = this.getAdFromId(id);
        Map<String, Object> map1;
        for (int i = 0; i < adList.size(); i++) {
            map1 = (Map<String, Object>) adList.get(i);
            for (Map.Entry<String, Object> entry : map1.entrySet()) {
                if (entry.getKey().equals("reg_id")) {
                    String val = entry.getValue().toString();
                    reg_id = val;
                } else if (entry.getKey().equals("email")) {
                    email = (String) entry.getValue();
                }
            }

        }
        try {
            String hql = "DELETE FROM Bike "
                    + "WHERE id.regId = '" + reg_id + "' AND email = '" + email + "'";
            Query query = session.createQuery(hql);

            tx = session.beginTransaction();
            int result = query.executeUpdate();
            tx.commit();

     
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return new ModelAndView("redirect:/index/index.htm");
    }
}
