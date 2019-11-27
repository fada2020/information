package jp.co.info.ais.asm.mapper;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.info.ais.ams.domain.Todo;
import jp.co.info.ais.ams.mapper.TodoMapper;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoMapperTest {

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    @Test
    public void insert() {

        // setup
        // none

        // perform test and assertions
        {
            Todo newTodo = new Todo();
            newTodo.setTitle("飲み会");
            newTodo.setDetails("銀座 19:00");
            todoMapper.insert(newTodo);

            Todo actualTodo =
                jdbcOperations.queryForObject("SELECT * FROM todo WHERE id = :id",
                    new MapSqlParameterSource("id", newTodo.getId()),
                    new BeanPropertyRowMapper<>(Todo.class));
            assertThat(actualTodo.getId()).isEqualTo(newTodo.getId());
            assertThat(actualTodo.getTitle()).isEqualTo("飲み会");
            assertThat(actualTodo.getDetails()).isEqualTo("銀座 19:00");
            assertThat(actualTodo.isFinished()).isEqualTo(false);
        }
    }


    @Test
    public void select() {

        // setup
        Todo newTodo = new Todo();
        newTodo.setTitle("飲み会");
        newTodo.setDetails("銀座 19:00");

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(
            "INSERT INTO todo (title, details, finished) VALUES(:title, :details, :finished)",
            new BeanPropertySqlParameterSource(newTodo), keyHolder);

        // perform test and assertions
        {
            Todo actualTodo = todoMapper.select(keyHolder.getKey().intValue());

            assertThat(actualTodo.getId()).isEqualTo(keyHolder.getKey().intValue());
            assertThat(actualTodo.getTitle()).isEqualTo("飲み会");
            assertThat(actualTodo.getDetails()).isEqualTo("銀座 19:00");
            assertThat(actualTodo.isFinished()).isEqualTo(false);
        }
    }

}