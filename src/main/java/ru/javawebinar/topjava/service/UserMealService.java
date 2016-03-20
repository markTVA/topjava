package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(UserMeal userMeal, Integer userId);

    UserMeal update(UserMeal userMeal, Integer userId);

    void delete(int id, Integer userId);

    UserMeal get(int id, Integer userId);

    Collection<UserMeal> getAll(Integer userId);

    Collection<UserMeal> getFiltered(LocalDate startDate, LocalDate endDate, int userId);

}
