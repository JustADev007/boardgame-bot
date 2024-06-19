package org.example;

import commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
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
            guild.upsertCommand("hello","Says hello").queue();
            guild.upsertCommand("add-game", "adds new board game to the list")
                    .addOptions(
                            new OptionData(OptionType.STRING, "name", "enter name of game", true),
                            new OptionData(OptionType.STRING, "weight", "enter the difficulty", true),
                            new OptionData(OptionType.INTEGER, "min-players", "enter minimum amount of players", true),
                            new OptionData(OptionType.INTEGER, "max-players", "maximum amount of players", true)
                    )
                    .queue();
            guild.upsertCommand("games","list all of the board games").queue();
            guild.upsertCommand("random","picks a random board game").queue();
            guild.upsertCommand("suggest-games","suggests games for your group to play")
                    .addOptions(
                            new OptionData(OptionType.STRING, "weight", "enter the difficulty",true),
                            new OptionData(OptionType.INTEGER, "player-count", "enter your player count", true)
                    )
                    .queue();
            guild.upsertCommand("add-player", "adds new player to the list")
                    .addOptions(
                            new OptionData(OptionType.STRING, "name", "enter name of player", true)
                    )
                    .queue();
        }


    }
}
