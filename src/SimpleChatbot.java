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
