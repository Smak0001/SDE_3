import java.util.Scanner;

public class SimpleChatbot {

    private static SimpleChatbot instance;

    private String greeting;
    private String farewell;

    // Private constructor to prevent instantiation outside the class
    private SimpleChatbot() {
        // Set default values
        this.greeting = "Hello! How can I help you?";
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

    public void startChat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(greeting);

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
        // Simple logic for generating responses based on user input
        if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! How can I help you?";
        } else if (userInput.contains("how are you")) {
            return "I'm just a computer program, but thanks for asking!";
        } else {
            return "I'm sorry, I don't understand. Can you please rephrase or ask something else?";
        }
    }

    public static void main(String[] args) {
        SimpleChatbot chatbot = new SimpleChatbot.Builder()
                .setGreeting("Greetings! Welcome to the chat.")
                .setFarewell("Goodbye! See you next time.")
                .build();

        chatbot.startChat();
    }
}
