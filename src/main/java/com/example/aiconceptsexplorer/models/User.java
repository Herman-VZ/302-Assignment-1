package com.example.aiconceptsexplorer.models;

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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Full constructor (optional if needed)
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

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getLatestAchievement() { return latestAchievement; }

    public void setLatestAchievement(String latestAchievement) { this.latestAchievement = latestAchievement; }

    public String getLatestLesson() { return latestLesson; }

    public void setLatestLesson(String latestLesson) { this.latestLesson = latestLesson; }

    public int getLessonStreak() { return lessonStreak; }

    public void setLessonStreak(int lessonStreak) { this.lessonStreak = lessonStreak; }

    public String getAchievement() { return achievement; }

    public void setAchievement(String achievement) { this.achievement = achievement; }

    public String getMedal() { return medal; }

    public void setMedal(String medal) { this.medal = medal; }

    public int getscore() { return score; }

    public void setscore(int score) { this.score = score; }
}
