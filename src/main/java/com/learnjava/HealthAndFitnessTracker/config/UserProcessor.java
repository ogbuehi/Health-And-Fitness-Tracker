package com.learnjava.HealthAndFitnessTracker.config;

import com.learnjava.HealthAndFitnessTracker.model.User;
import org.springframework.batch.item.ItemProcessor;

public class UserProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user) throws Exception {
        user.setUserId(null);
        return user;
    }
}
