import java.util.Scanner;

public class SimpleChatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm your chatbot. Ask me something or type 'exit' to end the conversation.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("Goodbye! Have a great day.");
                break;
            } else {
                String response = getChatbotResponse(userInput);
                System.out.println("Chatbot: " + response);
            }
        }

        scanner.close();
    }

    private static String getChatbotResponse(String userInput) {
        // Simple logic for generating responses based on user input
        if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! How can I help you?";
        } else if (userInput.contains("how are you")) {
            return "I'm just a computer program, but thanks for asking!";
        } else {
            return "I'm sorry, I don't understand. Can you please rephrase or ask something else?";
        }
    }
}
