package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    private static final Logger LOG = getLogger(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public UserMeal create(UserMeal userMeal){
        userMeal.setId(null);
        LOG.info("create " + userMeal);
        return service.save(userMeal, LoggedUser.id());
    }

    public UserMeal update(UserMeal userMeal){
        LOG.info("update " + userMeal);
        return service.update(userMeal,LoggedUser.id());
    }

    public void delete(UserMeal userMeal){
        LOG.info("create " + userMeal);
        service.delete(userMeal.getId(), LoggedUser.id());
    }

    public UserMeal get(int id){
        LOG.info("get " + id);
        return service.get(id, LoggedUser.id());
    }

    public List<UserMealWithExceed> getAll(){
        LOG.info("getAll");
        return UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), LoggedUser.getCaloriesPerDay());
    }

    public Collection<UserMealWithExceed> getFiltered(String startDate, String endDate,
                                                      String startTime, String endTime) {
        LOG.info("get filtered in dates from {} to {} in time from {} to {}", startDate, endDate, startTime, endTime);
        final Collection<UserMeal> allFiltered = service.getFiltered(startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate),
                endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate), LoggedUser.id());
        return UserMealsUtil.getFilteredWithExceeded(allFiltered, startTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(startTime),
                endTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTime), LoggedUser.getCaloriesPerDay());
    }






}
