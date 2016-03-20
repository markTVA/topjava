package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, Integer userId);

    boolean delete(int id, Integer userId);

    UserMeal get(int id, Integer userId);

    Collection<UserMeal> getAll(Integer userId);

    Collection<UserMeal> getFiltered(LocalDate startDate, LocalDate endDate, int userId);
}
