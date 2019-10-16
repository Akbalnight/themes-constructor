package com.common.services.themes.repository;

import com.common.services.themes.model.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class ThemesRepository {

    @Value("${themes.table}")
    private String tableName;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<Theme> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Theme.class);

    private String SQL_GET_ALL;
    private String SQL_GET_BY_NAME;
    private String SQL_SAVE_THEME;

    @PostConstruct
    public void init(){
        SQL_GET_ALL = String.format("select name, description, theme from %s", tableName);
        SQL_GET_BY_NAME = String.format("select name, description, theme from %s where name = :nameTheme", tableName);
        SQL_SAVE_THEME = String.format("" +
                "INSERT INTO %s (name, description, theme) " +
                "VALUES ( :nameTheme, :descriptionTheme, :theme) " +
                "on conflict (name) do " +
                "update set description = :descriptionTheme, theme = :theme;", tableName);
    }

    public List<Theme> getAllThemes(){
        return namedParameterJdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER);
    }

    public Theme getThemeByName(String nameTheme){
        return DataAccessUtils.singleResult(namedParameterJdbcTemplate.query(SQL_GET_BY_NAME, new MapSqlParameterSource("nameTheme", nameTheme), ROW_MAPPER));
    }

    public int saveTheme(Theme theme){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nameTheme", theme.getName());
        params.addValue("descriptionTheme", theme.getDescription());
        params.addValue("theme", theme.getTheme());
        return namedParameterJdbcTemplate.update(SQL_SAVE_THEME, params);
    }
}
