package com.tabeldata.bootcamp.web.dao;


import com.tabeldata.bootcamp.web.model.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KategoryDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Kategori> list() {
        String sql = "select * from category";
        return this.jdbcTemplate.query(sql,new RowMapperInner());
    }

    public Kategori findById(Integer id) {
        String sql = "select * from category where category_id = :kode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", id);
        return this.jdbcTemplate.queryForObject(sql, map, new RowMapperInner());
    }

    public Integer insertData(Kategori datak){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO category(category_id, department_id, name, description)" +
                "VALUES (:kode, :department_id, :name, :description)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", datak.getCategory_id());
        map.addValue("department_id", datak.getDepartment_id());
        map.addValue("name", datak.getName());
        map.addValue("description", datak.getDescription());
        this.jdbcTemplate.update(sql, map, keyHolder);
        return (Integer) keyHolder.getKeys().get("department_id");

    }
    public void updateCategory(Kategori datak) {
        String sql = "update category set name = :name, description = :description  where category_id = :kode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", datak.getCategory_id());
        map.addValue("department_id", datak.getDepartment_id());
        map.addValue("name", datak.getName());
        map.addValue("description", datak.getDescription());
        this.jdbcTemplate.update(sql, map);
    }

    public void delete(Integer id) {
        String sql = "delete from category where category_id = :kode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", id);
        this.jdbcTemplate.update(sql, map);
    }

    public class RowMapperInner implements RowMapper<Kategori> {
        @Override
        public Kategori mapRow(ResultSet rs, int rowNum) throws SQLException {
            Kategori data = new Kategori();
            data.setCategory_id(rs.getInt("category_id"));
            data.setDepartment_id(rs.getInt("department_id"));
            data.setName(rs.getString("name"));
            data.setDescription(rs.getString("description"));
            return data;
        }
    }

}
