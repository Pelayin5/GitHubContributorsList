package com.pelayo.projects.githubcontributorslist;

public class Repository {
    //Variables miembro
    private int mId;

    private String mName;
    private String mOwner;

    //Constructor
    public Repository(int id, String name, String owner) {
        this.mId = id;

        this.mName = name;
        this.mOwner = owner;
    }

    //Getters
    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getOwner() {
        return this.mOwner;
    }

    public String getUrl() {
        return "https://api.github.com/repos/" + mOwner + "/" + mName + "/contributors";
    }

    //Setters
    public void setName(String name) {
        this.mName = name;
    }

    public void setOwner(String owner) {
        this.mOwner = owner;
    }

    //Metodos
    public static String generateApiCall(String owner, String name) {
        return "https://api.github.com/repos/" + owner + "/" + name + "/contributors";
    }
}
