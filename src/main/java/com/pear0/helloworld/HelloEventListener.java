package com.pear0.helloworld;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class HelloEventListener extends ListenerAdapter {

    HelloBot bot;

    public HelloEventListener(HelloBot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        List<User> mentionedUsers = event.getMessage().getMentionedUsers();

        if (mentionedUsers.contains(bot.getJDA().getSelfUser())) {
            String content = event.getMessage().getContent();

            if (content.contains("info")) {
                event.getAuthor().openPrivateChannel().queue(channel -> {
                    Message message = new MessageBuilder()
                            .append("Hi ")
                            .append(event.getAuthor())
                            .append("!\n")
                            .append("I don't do much right now but you can find me at:\n")
                            .append("https://github.com/Pear0/discord-helloworld")
                            .build();

                    channel.sendMessage(message).queue();
                });
            }else if (content.contains("embed")) {
                String bobUrl = "https://yt3.ggpht.com/-uJh4oSQAwak/AAAAAAAAAAI/AAAAAAAAAAA/AMGKfKvDP3w/s900-c-k-no-mo-rj-c0xffffff/photo.jpg";
                String catUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1200px-Cat03.jpg";
                String catUrl2 = "https://d4n5pyzr6ibrc.cloudfront.net/media/27FB7F0C-9885-42A6-9E0C19C35242B5AC/4785B1C2-8734-405D-96DC23A6A32F256B/thul-90efb785-97af-5e51-94cf-503fc81b6940.jpg?response-content-disposition=inline";

                MessageEmbed embed = new EmbedBuilder()
                        .setTitle("This is a title")
                        .setAuthor("Author John Doe",
                                "https://example.com",
                                bobUrl)
                        .setDescription("This is a long description. It can contain general info about the embed")
                        .addField("Field 1", "This field has a bunch of text.", true)
                        .addField("Field 2", "This field has a bunch of text.", true)
                        .addField("Field 3", "This field has a bunch of text.", true)
                        .addField("Field 4", "This non-inline field has a bunch of text.", false)
                        .addField("Field 5", "This non-inline field has a bunch of text.", false)
                        .addField("Field 6", "This field has a bunch of text.", true)
                        .addField("Field 7", "This field has a bunch of text.", true)
                        .setImage(catUrl)
                        .setThumbnail(catUrl2)
                        .setColor(Color.GREEN)
                        .build();

                event.getMessage().getChannel().sendMessage(embed).queue();

            }else {
                event.getMessage().addReaction("\uD83D\uDC4D").queue();
            }

        }
    }
}
