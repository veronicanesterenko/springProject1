package ru.springcourse.project1.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.springcourse.project1.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
       return jdbcTemplate.query("select * from Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from Book where id=?", new Object[] {id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into Book (title, author, year) values (?,?,?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public void update(Book book, int id) {
        jdbcTemplate.update("update Book set title=?, author=?, year=? where id=?", book.getTitle(),
                book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Book where id=?", id);
    }
}
