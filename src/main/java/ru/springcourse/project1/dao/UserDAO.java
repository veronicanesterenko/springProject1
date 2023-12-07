package ru.springcourse.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.springcourse.project1.models.User;

import java.util.List;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
       return jdbcTemplate.query("select * from User", new BeanPropertyRowMapper<>(User.class));
    }

    public User show( int id) {
        return jdbcTemplate.query("select * from User where id=?",new Object[] {id}, new BeanPropertyRowMapper<>(User.class)).
                stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("insert into User(fullName, yearOfBirth) values (?,?)", user.getFullName(),
                user.getYearOfBirth());
    }

    public void update(int id, User updatedUser) {
        jdbcTemplate.update("update User set fullName=?, yearOfBirth=? where id = ?",
                updatedUser.getFullName(), updatedUser.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from User where id=?", id);
    }
}
