package com.example.aiconceptsexplorer.models;

/**
 * Represents a user in the system with profile and progress information.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String latestAchievement;
    private String latestLesson;
    private int lessonStreak;
    private String achievement;
    private String medal;
    private int score;

    /**
     * Constructs a new User with the specified name, email & password.
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructs a new User with all fields specified
     * @param id the user's unique identifier
     * @param name the user's name
     * @param email the user's email
     * @param password the user's password
     * @param latestAchievement the user's latest achievement
     * @param latestLesson the user's latest lesson
     * @param lessonStreak the user's lesson streak
     * @param achievement the user's achievement description
     * @param medal the user's medal
     * @param score the user's score
     */
    public User(int id, String name, String email, String password, String latestAchievement,
                String latestLesson, int lessonStreak, String achievement, String medal, int score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.latestAchievement = latestAchievement;
        this.latestLesson = latestLesson;
        this.lessonStreak = lessonStreak;
        this.achievement = achievement;
        this.medal = medal;
        this.score = score;
    }

    /**
     * Gets the user's unique identifier.
     * @return the users id
     */
    public int getId() { return id; }

    /**
     * Sets the user's unique identifier.
     * @param id the user's id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the user's name.
     * @return the user's name
     */
    public String getName() { return name; }

    /**
     * Sets the user's name.
     * @param name the user's name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the user's email.
     * @return the user's email
     */
    public String getEmail() { return email; }

    /**
     * Sets the user's email.
     * @param email the user's email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets the user's password.
     * @return the user's password
     */
    public String getPassword() { return password; }

    /**
     * Sets the user's password.
     * @param password the user's password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the user's latest achievement.
     * @return the user's latest achievement
     */
    public String getLatestAchievement() { return latestAchievement; }

    /**
     * Sets the user's latest achievement.
     * @param latestAchievement the user's latest achievement
     */
    public void setLatestAchievement(String latestAchievement) { this.latestAchievement = latestAchievement; }

    /**
     * Gets the user's latest lesson.
     * @return the user's latest lesson
     */
    public String getLatestLesson() { return latestLesson; }

    /**
     * Sets the user's latest lesson.
     * @param latestLesson the user's latest lesson
     */
    public void setLatestLesson(String latestLesson) { this.latestLesson = latestLesson; }

    /**
     * Gets the user's lesson streak.
     * @return the user's lesson streak
     */
    public int getLessonStreak() { return lessonStreak; }

    /**
     * Sets the user's lesson streak.
     * @param lessonStreak the user's lesson streak
     */
    public void setLessonStreak(int lessonStreak) { this.lessonStreak = lessonStreak; }

    /**
     * Gets the user's achievement description.
     * @return the user's achievement description
     */
    public String getAchievement() { return achievement; }

    /**
     * Sets the user's achievement description.
     * @param achievement the user's achievement description
     */
    public void setAchievement(String achievement) { this.achievement = achievement; }

    /**
     * Gets the user's medal.
     * @return the user's medal
     */
    public String getMedal() { return medal; }

    /**
     * Sets the user's medal.
     * @param medal the user's medal
     */
    public void setMedal(String medal) { this.medal = medal; }

    /**
     * Gets the user's score.
     * @return the user's score
     */
    public int getscore() { return score; }

    /**
     * Sets the user's score.
     * @param score the user's score
     */
    public void setscore(int score) { this.score = score; }
}
