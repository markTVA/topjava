package ru.javawebinar.topjava.dao.impl;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Владимир on 11.03.2016.
 */
public class InMemoryDaoImpl implements MealDao {

    private AtomicInteger count = new AtomicInteger(0);
    private Map<Integer, UserMeal> memoryMeals = new ConcurrentHashMap<>();

    public InMemoryDaoImpl(){
        memoryMeals.put(count.incrementAndGet(), new UserMeal(count.get(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        memoryMeals.put(count.incrementAndGet(), new UserMeal(count.get(), LocalDateTime.of(2015, Month.MAY, 30, 15, 0), "Обед", 1000));
        memoryMeals.put(count.incrementAndGet(), new UserMeal(count.get(), LocalDateTime.of(2015, Month.MAY, 30, 18, 0), "Ужин", 500));
        memoryMeals.put(count.incrementAndGet(), new UserMeal(count.get(), LocalDateTime.of(2015, Month.JUNE, 30, 8, 0), "Завтрак", 600));
        memoryMeals.put(count.incrementAndGet(), new UserMeal(count.get(), LocalDateTime.of(2015, Month.JUNE, 30, 13, 0), "Обед", 1000));
        memoryMeals.put(count.incrementAndGet(), new UserMeal(count.get(), LocalDateTime.of(2015, Month.JUNE, 30, 18, 0), "Ужин", 500));
    }

    @Override
    public List<UserMeal> getAllMeals() {
        return new ArrayList<>(memoryMeals.values());
    }

    @Override
    public UserMeal getById(int id) {
        return memoryMeals.get(id);
    }

    @Override
    public int getId() {
        return count.get();
    }

    @Override
    public void remove(int id) {
        memoryMeals.remove(id);
    }

    @Override
    public void create(LocalDateTime localDateTime, String description, int calories) {
        memoryMeals.putIfAbsent(count.incrementAndGet(), new UserMeal(count.get(), localDateTime, description, calories));
    }

    @Override
    public void update(int id, LocalDateTime localDateTime, String description, int calories) {
        memoryMeals.put(count.get(), new UserMeal(id, localDateTime, description, calories));
    }
}
