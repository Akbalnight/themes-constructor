package com.common.services.themes.controller;

import com.common.services.themes.model.Theme;
import com.common.services.themes.repository.ThemesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * @author AsMatveev
 */
@RestController()
@RequestMapping("/themes")
public class ThemeController
{
    @Autowired
    ThemesRepository themesRepository;

    @GetMapping("/")
    public ResponseEntity getAllThemes()
    {
        List results = themesRepository.getAllThemes();

        if(results != null)
            return ResponseEntity.ok().body(results);
        else
            return ResponseEntity.status(500).body("Тем не найдено");
    }

    @GetMapping("/{name}")
    public ResponseEntity getThemeByName(@PathVariable String name)
    {
        Theme result = themesRepository.getThemeByName(name);

        if(result != null)
            return ResponseEntity.ok().body(result);
        else
            return ResponseEntity.status(404).body("Тема не найдена");
    }

    @PutMapping("/")
    public ResponseEntity saveTheme(@RequestBody Theme theme)
    {
        int result = themesRepository.saveTheme(theme);

        if(result != 0)
            return ResponseEntity.ok().body(result);
        else
            return ResponseEntity.status(404).body("Тема не сохранена");
    }
}
