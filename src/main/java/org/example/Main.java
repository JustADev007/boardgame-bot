package org.example;

import commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        JDA bot = JDABuilder.createDefault(System.getenv("BOT_KEY"))
                .enableIntents(GatewayIntent.GUILD_PRESENCES)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("Gloomhaven"))
                .addEventListeners(new BotCommands())
                .build().awaitReady();

        Guild guild = bot.getGuildById("1251653017443237909");

        if(guild != null){
            guild.upsertCommand("hello","Say hello").queue();
        }


    }
}
