package com.common.services.themes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 * @author AsMatveev
 */
@SpringBootApplication(scanBasePackages = "com.common.services.themes")
public class ThemesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ThemesApplication.class, args);
    }
}
