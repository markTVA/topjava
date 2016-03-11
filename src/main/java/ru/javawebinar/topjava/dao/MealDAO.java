package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Владимир on 11.03.2016.
 */
public interface MealDao {
    List<UserMeal> getAllMeals();
    UserMeal getById(int id);
    int getId();
    void remove(int id);
    void create(LocalDateTime localDateTime, String description, int calories);
    void update(int id, LocalDateTime localDateTime, String description, int calories);
}
