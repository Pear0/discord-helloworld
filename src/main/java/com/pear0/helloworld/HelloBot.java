package com.pear0.helloworld;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloBot {

    private JSONObject config;
    private JDA jda;

    public HelloBot(JSONObject config) {
        this.config = config;
    }


    public void start() throws LoginException, InterruptedException, RateLimitedException {
        jda = new JDABuilder(AccountType.BOT)
                .setToken(config.getString("discord_token"))
                .setAudioEnabled(false)
                .setAutoReconnect(true)
                .setGame(Game.playing("games"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListener(new HelloEventListener(this))
                .buildBlocking();
    }

    public JDA getJDA() {
        return jda;
    }

    public static void main(String[] args) throws Exception {
        JSONObject config;

        try (FileInputStream is = new FileInputStream(new File("config.json"))) {
            config = new JSONObject(new JSONTokener(is));
        }catch (IOException e) {
            e.printStackTrace();

            System.exit(1);
            return;
        }

        HelloBot bot = new HelloBot(config);
        bot.start();

    }

}
