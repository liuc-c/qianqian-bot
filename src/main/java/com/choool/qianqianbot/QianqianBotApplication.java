package com.choool.qianqianbot;

import love.forte.simbot.spring.autoconfigure.EnableSimbot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author <a href="https://github.com/liuc-c">choool</a>
 */
@EnableSimbot
@SpringBootApplication
@EnableScheduling
@MapperScan("com.choool.qianqianbot.mapper")
public class QianqianBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(QianqianBotApplication.class, args);
    }

}
