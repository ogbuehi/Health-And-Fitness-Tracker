package com.web.dev.SpringBoot.TodoList.repositories;

import com.web.dev.SpringBoot.TodoList.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
        @Override
        Optional<Task> findById(Long aLong);

        @Override
        void deleteById(Long aLong);

        List<Task> findByCompletedTrue();

        @Override
        <S extends Task> S save(S entity);

        List<Task> findByCompletedFalse();

        @Override
        Iterable<Task> findAll();

        Task updateById(Long id);

}
