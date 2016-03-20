package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(userMeal -> this.save(userMeal, userMeal.getUserId()));
    }

    @Override
    public UserMeal save(UserMeal userMeal, Integer userId) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        else {
            if (get(userMeal.getId(), userId) == null) return null;
            userMeal.setUserId(userId);
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }


    @Override
    public boolean delete(int id, Integer userId) {
        if (!repository.containsKey(id) || !repository.get(id).getUserId().equals(userId)){
            return false;
        }
        repository.remove(id);
        return true;
    }

    @Override
    public UserMeal get(int id, Integer userId) {
        if (!repository.containsKey(id) || !repository.get(id).getUserId().equals(userId)){
            return null;
        }
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll(Integer userId) {
        return getFiltered(LocalDate.MIN, LocalDate.MAX, userId);
    }

    @Override
    public Collection<UserMeal> getFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.values()
                .stream()
                .filter(u -> u.getUserId().equals(userId)
                        && TimeUtil.isBetween(u.getDateTime().toLocalDate(), startDate, endDate))
                .sorted((u1, u2) -> u1.getDateTime().compareTo(u2.getDateTime()))
                .collect(Collectors.toList());
    }
}

