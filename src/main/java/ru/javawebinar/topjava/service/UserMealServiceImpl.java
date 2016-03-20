package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;


    @Override
    public UserMeal save(UserMeal userMeal, Integer userId) throws NotFoundException {
        userMeal.setUserId(userId);
        return repository.save(userMeal, userId);
    }

    @Override
    public UserMeal update(UserMeal userMeal, Integer userId) {
        return ExceptionUtil.check(repository.save(userMeal, userId), userMeal.getId());
    }

    @Override
    public void delete(int id, Integer userId) throws NotFoundException {
        ExceptionUtil.check(repository.get(id, userId), userId);
    }

    @Override
    public UserMeal get(int id, Integer userId) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id, userId), userId);
    }

    @Override
    public Collection<UserMeal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<UserMeal> getFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.getFiltered(startDate, endDate, userId);
    }
}
