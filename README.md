<b>
Introduction
</b><br><br>
Jens and I, Xander, teamed up to finish our latest project together. We spent a lot of time working by pair programming. This helped us solve problems and come up with different design patterns to implement.

Even though we did a bunch of work together, there were also times when we each did things on our own. Some parts of the project needed special attention, so we split up to focus on them individually.



<b>Creation design pattern</b>
1. <b>Builder Class</b>


      public static class Builder {
      private final SimpleChatbot chatbot;

      public Builder() {
      this.chatbot = new SimpleChatbot();
      }

      public Builder setGreeting(String greeting) {
      chatbot.greeting = greeting;
      return this;
      }

      public Builder setFarewell(String farewell) {
      chatbot.farewell = farewell;
      return this;
      }

      public SimpleChatbot build() {
      return chatbot;
      }
      }

<b>Explanation:</b><br>
This code represents the Builder class within the SimpleChatbot class, providing methods to set optional configurations (setGreeting and setFarewell) and a build method to create the final SimpleChatbot instance.

<br>
<b>Behavioural design pattern</b><br><br>
1.  Command design pattern

   1.1 ChatCommand.java
   
   
      // ChatCommand interface

      public interface ChatCommand {
      String execute(String userInput);
      }

   1.2 GreetingCommand.java

        // GreetingCommand class

    public class GreetingCommand implements ChatCommand {
        @Override
        public String execute(String userInput) {
            return "Hello! How can I help you?";
        }
    }



1.3 HowAreYouCommand.java


    // HowAreYouCommand class
    
    public class HowAreYouCommand implements ChatCommand {
        @Override
        public String execute(String userInput) {
            return "I'm just a computer program, but thanks for asking!";
        }
    }

1.4 DefaultCommand.java


    // DefaultCommand class

    public class DefaultCommand implements ChatCommand {
        @Override
        public String execute(String userInput) {
            return "I'm sorry, I don't understand. Can you please rephrase or ask something else?";
        }
    }

1.5 SimpleChatbot.java

    import java.util.Scanner;
    
    public class SimpleChatbot {
    private static SimpleChatbot instance;
    private ChatCommand greetingCommand;
    private ChatCommand howAreYouCommand;
    private ChatCommand defaultCommand;
    private String farewell;

    // Private constructor to prevent instantiation outside the class
    private SimpleChatbot() {
        // Set default commands
        this.greetingCommand = new GreetingCommand();
        this.howAreYouCommand = new HowAreYouCommand();
        this.defaultCommand = new DefaultCommand();
        this.farewell = "Goodbye! Have a great day.";
    }

    public static SimpleChatbot getInstance() {
        if (instance == null) {
            instance = new SimpleChatbot();
        }
        return instance;
    }

    // Builder class for constructing SimpleChatbot with optional configurations
    public static class Builder {
        private final SimpleChatbot chatbot;

        public Builder() {
            this.chatbot = new SimpleChatbot();
        }

        public Builder setGreetingCommand(ChatCommand greetingCommand) {
            chatbot.greetingCommand = greetingCommand;
            return this;
        }

        public Builder setHowAreYouCommand(ChatCommand howAreYouCommand) {
            chatbot.howAreYouCommand = howAreYouCommand;
            return this;
        }

        public Builder setDefaultCommand(ChatCommand defaultCommand) {
            chatbot.defaultCommand = defaultCommand;
            return this;
        }

        public SimpleChatbot build() {
            return chatbot;
        }
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(greetingCommand.execute(null));

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println(farewell);
                break;
            } else {
                String response = getChatbotResponse(userInput);
                System.out.println("Chatbot: " + response);
            }
        }

        scanner.close();
    }

    private String getChatbotResponse(String userInput) {
        // Use the appropriate command based on user input
        if (userInput.contains("hello") || userInput.contains("hi")) {
            return greetingCommand.execute(userInput);
        } else if (userInput.contains("how are you")) {
            return howAreYouCommand.execute(userInput);
        } else {
            return defaultCommand.execute(userInput);
        }
    }

    public static void main(String[] args) {
        // Use the Builder to set custom commands
        SimpleChatbot chatbot = new SimpleChatbot.Builder()
                .setGreetingCommand(new GreetingCommand())
                .setHowAreYouCommand(new HowAreYouCommand())
                .setDefaultCommand(new DefaultCommand())
                .build();

        chatbot.startChat();
    }
    }
    }

<b>Explanation:</b>

The SimpleChatbot utilizes the Command design pattern to handle different types of responses. Each response type (greeting, well-being inquiry, default response) is encapsulated in a separate command class implementing the ChatCommand interface.

<b>GreetingCommand:</b> Responds with a greeting when the user input contains "hello" or "hi."

<b>HowAreYouCommand:</b> Responds to queries about well-being.

<b>DefaultCommand:</b> Provides a default response for unrecognized inputs.

The main class, SimpleChatbot, orchestrates the chatbot's behavior by using these command objects based on user input. The code is organized for flexibility, allowing easy extension with new commands.
