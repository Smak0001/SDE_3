public class ChatbotContext {
    private final SimpleChatbot chatbot;

    public ChatbotContext() {
        this.chatbot = new SimpleChatbot();
    }

    public ChatbotContext setGreetingCommand(ChatCommand greetingCommand) {
        chatbot.setGreetingCommand(greetingCommand);
        return this;
    }

    public ChatbotContext setHowAreYouCommand(ChatCommand howAreYouCommand) {
        chatbot.setHowAreYouCommand(howAreYouCommand);
        return this;
    }

    public ChatbotContext setDefaultCommand(ChatCommand defaultCommand) {
        chatbot.setDefaultCommand(defaultCommand);
        return this;
    }

    public SimpleChatbot build() {
        return chatbot;
    }
}