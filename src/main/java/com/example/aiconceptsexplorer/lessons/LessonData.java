package com.example.aiconceptsexplorer.lessons;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class LessonData {

    // Static method to return lesson data
    public static List<LessonSection> getLessonSections() {
        List<LessonSection> sections = new ArrayList<>();

        // Creating the sections with more detailed content
        sections.add(new LessonSection(
                "What is Artificial Intelligence?",
                "Artificial Intelligence (AI) is the branch of computer science focused on creating machines capable of performing tasks that typically require human intelligence. These tasks include learning, reasoning, problem-solving, perception, and language understanding.\n\n" +
                        "AI systems are divided into two broad categories: narrow AI and general AI. Narrow AI is designed to handle a specific task, such as playing chess or recommending products. General AI, still theoretical, would be capable of performing any intellectual task a human can do, possessing cognitive abilities across a wide range of functions.",
                null
        ));

        sections.add(new LessonSection(
                "Everyday Examples of AI",
                "Virtual Assistants: Siri, Alexa, Google Assistant — These systems leverage natural language processing (NLP) and machine learning to understand user commands and provide useful responses or take actions.\n\n" +
                        "Recommendation Systems: Netflix and Amazon use AI algorithms to suggest movies, shows, or products based on previous interactions, preferences, and data analysis.\n\n" +
                        "Smart Devices: AI is embedded in devices like smart thermostats that learn your home’s heating preferences and adjust accordingly, or in voice-controlled home automation systems that learn to anticipate and respond to your needs.\n\n" +
                        "Autonomous Vehicles: AI enables self-driving cars to interpret sensor data and make decisions based on the car’s environment, improving safety and efficiency.",
                null
        ));

        sections.add(new LessonSection(
                "History of AI",
                "The roots of AI trace back to the 1940s and 1950s, when early pioneers like Alan Turing and John von Neumann laid the groundwork for the field. In 1956, the Dartmouth Conference formally introduced the concept of AI, and the term was coined by John McCarthy.\n\n" +
                        "Alan Turing's 1950 paper, 'Computing Machinery and Intelligence,' introduced the Turing Test, which proposed that a machine could be considered intelligent if it could engage in a conversation that was indistinguishable from a human.\n\n" +
                        "Since then, AI has gone through various phases of optimism, followed by periods of stagnation, commonly referred to as 'AI winters.' Major breakthroughs, like machine learning and deep learning, have revived interest and progress in the field.",
                null
        ));

        sections.add(new LessonSection(
                "Key Milestones",
                "1956: The Dartmouth Conference — The birth of AI as a formal research field, where the term 'Artificial Intelligence' was coined by John McCarthy.\n" +
                        "1997: IBM's Deep Blue defeated world chess champion Garry Kasparov, marking the first time a machine beat a human at the highest level of chess.\n" +
                        "2006: The emergence of deep learning as a powerful tool in AI, using artificial neural networks to improve machine learning tasks like speech and image recognition.\n" +
                        "2011: IBM's Watson won on the quiz show Jeopardy!, demonstrating AI's potential in natural language processing and machine understanding.\n" +
                        "2016: Google's AlphaGo defeated a world champion at the complex game of Go, showcasing the potential of deep reinforcement learning in strategic decision-making.",
                null
        ));

        sections.add(new LessonSection(
                "Future of AI",
                "As AI continues to evolve, we are entering an era where its capabilities can transform industries, from healthcare and education to entertainment and finance.\n\n" +
                        "AI’s potential to revolutionize sectors like healthcare includes advancements in personalized medicine, automated diagnostics, and drug discovery. In transportation, self-driving cars could reshape mobility and reduce traffic accidents.\n\n" +
                        "However, challenges remain, including ethical considerations, privacy concerns, and potential job displacement due to automation. These issues need to be addressed as AI technology advances rapidly.\n\n" +
                        "The future of AI is not just about intelligent machines; it's about how humans will collaborate with AI to create innovative solutions that improve our world while ensuring responsible development."
                , null
        ));

        return sections;
    }

    // Inner class to represent each lesson section
    public static class LessonSection {
        String title;
        String text;
        Image image;

        public LessonSection(String title, String text, Image image) {
            this.title = title;
            this.text = text;
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public Image getImage() {
            return image;
        }
    }
}
