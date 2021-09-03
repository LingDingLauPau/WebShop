package com.example.webshop.Repository;

import com.example.webshop.Model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class ItemsRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String SQL_USE = "USE webshop;";

    /**
     * Retrieves all the items from the database.
     * @author Kasper
     * @return List<Items>
     */
    public List<Items> fetchAllItems() {
        String sqlItems =
                "SELECT id, name, price FROM items ORDER BY items.id";
        RowMapper<Items> rowMapper = new BeanPropertyRowMapper<>(Items.class);

        jdbcTemplate.update(SQL_USE);    //selects the database
        List<Items> itemsList = jdbcTemplate.query(sqlItems,rowMapper);

        return itemsList;
    }

    public void addNew(Items items){
        String sqlItems = "INSERT INTO webshop.items (id, name, price) VALUES (DEFAULT, ?, ?);";
        jdbcTemplate.update(SQL_USE);
        jdbcTemplate.update(sqlItems, items.getName(), items.getPrice());
    }

    public void updateItem(Items items, int id){
        String sqlDeleteItem = "UPDATE webshop.items SET name = ?, price = ? WHERE id = ?;";
        jdbcTemplate.update(sqlDeleteItem, items.getName(),items.getPrice(),id);
    }

    public Items findById(int id) {
        String sql = "SELECT * FROM webshop.items " +
                "WHERE id = ?;";
        RowMapper<Items> rowMapper = new BeanPropertyRowMapper<>(Items.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void deleteItem(int id){
        String sqlUpdateItem = "DELETE FROM webshop.items WHERE id = ?;";
        jdbcTemplate.update(SQL_USE);
        jdbcTemplate.update(sqlUpdateItem, id);

    }
}
