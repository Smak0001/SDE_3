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
            chatbot.greetingCommand = new LoggingCommandDecorator(greetingCommand);
            return this;
        }

        public Builder setHowAreYouCommand(ChatCommand howAreYouCommand) {
            chatbot.howAreYouCommand = new LoggingCommandDecorator(howAreYouCommand);
            return this;
        }

        public Builder setDefaultCommand(ChatCommand defaultCommand) {
            chatbot.defaultCommand = new LoggingCommandDecorator(defaultCommand);
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
