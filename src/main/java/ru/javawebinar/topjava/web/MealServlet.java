package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.impl.InMemoryDaoImpl;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;


public class MealServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private final MealDao mealDao = new InMemoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        List<UserMealWithExceed> mealWithExceedList = getMealWithExceedFor(0, 23, 2000);
        request.setAttribute("mealList", mealWithExceedList);
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);

//        response.sendRedirect("mealList.jsp");
    }

    private List<UserMealWithExceed> getMealWithExceedFor(int hourS, int houtE, int callories){
        return UserMealsUtil.getFilteredMealsWithExceeded(mealDao.getAllMeals(), LocalTime.of(hourS, 0), LocalTime.of(houtE, 0), callories);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String action = req.getParameter("action");

        switch (action) {
            case "delete":
                mealDao.remove(Integer.parseInt(req.getParameter("id")));
                doGet(req, resp);
                break;
            case "add": {
                final String description = req.getParameter("description");
                final LocalDate localDate = LocalDate.parse(req.getParameter("date"));
                final LocalTime localTime = LocalTime.parse(req.getParameter("time"));
                final int calories = Integer.parseInt(req.getParameter("calories"));
                mealDao.create(LocalDateTime.of(localDate, localTime),
                        description, calories);

                doGet(req, resp);
                break;
            }
            case "showFormForChange": {
                UserMeal userMeal = mealDao.getById(Integer.parseInt(req.getParameter("id")));
                if (userMeal != null) {
                    req.setAttribute("isNeedShowFormForChange", true);
                    req.setAttribute("mealForChanging", userMeal);
                }

                doGet(req, resp);
                break;
            }
            case "change": {
                final int id = Integer.parseInt(req.getParameter("id"));
                final String description = req.getParameter("description");
                final LocalDate localDate = LocalDate.parse(req.getParameter("date"));
                final LocalTime localTime = LocalTime.parse(req.getParameter("time"));
                final int calories = Integer.parseInt(req.getParameter("calories"));
                mealDao.update(id, LocalDateTime.of(localDate, localTime),
                        description, calories);

                doGet(req, resp);
                break;
            }
            default:
                LOG.error("Didn't find action in doPost");
                break;
        }
    }
}
