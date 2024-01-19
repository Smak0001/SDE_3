Creation design pattern
1. Builder Class


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

Explanation:
This code represents the Builder class within the SimpleChatbot class, providing methods to set optional configurations (setGreeting and setFarewell) and a build method to create the final SimpleChatbot instance.

<br>
Behavioural design pattern
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

Explanation:

The SimpleChatbot utilizes the Command design pattern to handle different types of responses. Each response type (greeting, well-being inquiry, default response) is encapsulated in a separate command class implementing the ChatCommand interface.

GreetingCommand: Responds with a greeting when the user input contains "hello" or "hi."

HowAreYouCommand: Responds to queries about well-being.

DefaultCommand: Provides a default response for unrecognized inputs.

The main class, SimpleChatbot, orchestrates the chatbot's behavior by using these command objects based on user input. The code is organized for flexibility, allowing easy extension with new commands.
