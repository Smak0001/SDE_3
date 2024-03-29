<b>
Introduction
</b><br><br>
Jens and I, Xander, teamed up to finish our latest project together. We spent a lot of time working by pair programming. This helped us solve problems and come up with different design patterns to implement.

Even though we did a bunch of work together, there were also times when we each did things on our own. Some parts of the project needed special attention, so we split up to focus on them individually.



<b>Creation design pattern</b>
1. <b>Builder design pattern</b>


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

2. <b>Singleton design pattern</b>

        public class SimpleChatbot {
        private static SimpleChatbot instance;

        private ChatCommand greetingCommand; 
        private ChatCommand howAreYouCommand;
           private ChatCommand defaultCommand;
           private String farewell;
    
       
       private SimpleChatbot() {
       
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

        public static void main(String[] args) {
        SimpleChatbot chatbot = SimpleChatbot.getInstance();

        chatbot.startChat();
         }

<b>Explanation:</b><br>

The SimpleChatbot class is designed following the Singleton Pattern. The Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance. In our program, the SimpleChatbot class has a private constructor, and the instance is created lazily in the getInstance method. This ensures that there is only one instance of the SimpleChatbot class throughout the application, allowing a centralized point for managing the chatbot.
<br>

<b>Behavioural design pattern</b><br><br>

1. <b>Command design pattern</b>

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

2. <b>strategy design pattern</b>

         // Context class that uses a strategy (ChatCommand)
         public class ChatbotContext {
         private ChatCommand chatCommand;
      
       public void setChatCommand(ChatCommand chatCommand) {
           this.chatCommand = chatCommand;
       }
   
       public String executeCommand(String userInput) {
           return chatCommand.execute(userInput);
       }
         }

loggingCommandDecorator

      import java.io.FileWriter;
      import java.io.IOException;
      import java.io.PrintWriter;
      
      // Decorator class for logging user interactions
      public class LoggingCommandDecorator implements ChatCommand {
      private ChatCommand decoratedCommand;
      
          // Specify the file path for the log
          private String logFilePath = "chatbot_log.txt";
      
          public LoggingCommandDecorator(ChatCommand decoratedCommand) {
              this.decoratedCommand = decoratedCommand;
          }
      
          @Override
          public String execute(String userInput) {
              logInteraction(userInput);
              return decoratedCommand.execute(userInput);
          }
      
          private void logInteraction(String userInput) {
              try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
                  writer.println("User input: " + userInput);
              } catch (IOException e) {
                  e.printStackTrace(); // Handle the exception according to your needs
              }
          }
      }

SimpleChatbot.java

      import java.util.Scanner;
      
      // Singleton class for the chatbot
      public class SimpleChatbot {

    private static SimpleChatbot instance;

    private ChatbotContext chatbotContext;
    private String farewell;

    // Private constructor to prevent instantiation outside the class
    private SimpleChatbot() {
        this.chatbotContext = new ChatbotContext();
        this.farewell = "Goodbye! Have a great day.";
    }

    public static SimpleChatbot getInstance() {
        if (instance == null) {
            instance = new SimpleChatbot();
        }
        return instance;
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! How can I help you?");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println(farewell);
                break;
            } else {
                String response = chatbotContext.executeCommand(userInput);
                System.out.println("Chatbot: " + response);
            }
        }

        scanner.close();
    }

    public void setChatCommand(ChatCommand chatCommand) {
        chatbotContext.setChatCommand(chatCommand);
    }

    // Other methods...

    public static void main(String[] args) {
        // Use the Singleton pattern to get an instance of the chatbot
        SimpleChatbot chatbot = SimpleChatbot.getInstance();

        // Wrap the initial command with the LoggingCommandDecorator
        ChatCommand initialCommand = new GreetingCommand();
        chatbot.setChatCommand(new LoggingCommandDecorator(initialCommand));

        // Start the chat
        chatbot.startChat();
    }
      }


<b>explanation:</b>

The Strategy Pattern is implemented with the introduction of the ChatbotContext class. The ChatbotContext class represents the context that uses a strategy (ChatCommand). The strategy can be set dynamically, allowing the chatbot to execute different commands based on user input.

Components of the Strategy Pattern:
Strategy Interface (ChatCommand): Defines the strategy interface, which is the ChatCommand interface in this case.

Concrete Strategies (GreetingCommand, HowAreYouCommand, DefaultCommand): Concrete implementations of the ChatCommand interface represent different strategies that the chatbot can use.
Context Class (ChatbotContext): The ChatbotContext class

<br>
<b>Structural design pattern</b><br><br>

1. <b>Decorator design pattern</b>


    import java.io.FileWriter; 
    import java.io.IOException;
    import java.io.PrintWriter;

    // Decorator class for logging user interactions
    public class LoggingCommandDecorator implements ChatCommand {
    private ChatCommand decoratedCommand;

    // Specify the file path for the log
    private String logFilePath = "chatbot_log.txt";

    public LoggingCommandDecorator(ChatCommand decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    @Override
    public String execute(String userInput) {
        logInteraction(userInput);
        return decoratedCommand.execute(userInput);
    }

    private void logInteraction(String userInput) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println("User input: " + userInput);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
    }

<b>Explanation:</b><br>

In the LoggingCommandDecorator class, 
This pattern allows behavior to be added to an object, 
either statically or dynamically, without affecting the behavior
of other objects from the same class. In our case, the LoggingCommandDecorator wraps 
around a ChatCommand and adds logging behavior to it. This allows us to log user interactions
while keeping the original ChatCommand classes unchanged.