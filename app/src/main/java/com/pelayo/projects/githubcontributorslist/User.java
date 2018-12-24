package com.pelayo.projects.githubcontributorslist;

public class User {
    private String login;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;
    private String created_at;
    private String updated_at;

    private int id;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;

    private boolean site_admin;

    public User(int id, String login, String nodeId, String avatarUrl, String gravatarId,
                String url, String htmlUrl, String followersUrl, String followingUrl,
                String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationUrl,
                String reposUrl, String eventsUrl, String receivedEventsUrl, String type,
                String name, String company, String blog, String location, String email, String bio,
                String createdAt, String updatedAt, int publicRepos, int publicGists, int followers,
                int following, boolean siteAdmin) {

        this.id = id;
        this.login = login;
        this.node_id = nodeId;
        this.avatar_url = avatarUrl;
        this.gravatar_id = gravatarId;
        this.url = url;
        this.html_url = htmlUrl;
        this.followers_url = followersUrl;
        this.following_url = followingUrl;
        this.gists_url = gistsUrl;
        this.starred_url = starredUrl;
        this.subscriptions_url = subscriptionsUrl;
        this.organizations_url = organizationUrl;
        this.repos_url = reposUrl;
        this.events_url = eventsUrl;
        this.received_events_url = receivedEventsUrl;
        this.type = type;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.bio = bio;
        this.created_at = createdAt;
        this.updated_at = updatedAt;

        this.public_repos = publicRepos;
        this.public_gists = publicGists;
        this.followers = followers;
        this.following = following;

        this.site_admin = siteAdmin;
    }

    //Getters
    public String getLogin() {
        return this.login;
    }

    public String getNodeId() {
        return this.node_id;
    }

    public String getAvatarUrl() {
        return this.avatar_url;
    }

    public String getGravatarId() {
        return this.gravatar_id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getHtmlUrl() {
        return this.html_url;
    }

    public String getFollowersUrl() {
        return this.followers_url;
    }

    public String getFollowingUrl() {
        return this.following_url;
    }

    public String getGistsUrl() {
        return this.gists_url;
    }

    public String getStarredUrl() {
        return this.starred_url;
    }

    public String getSubscriptionsUrl() {
        return this.subscriptions_url;
    }

    public String getOrganizationsUrl() {
        return this.organizations_url;
    }

    public String getReposUrl() {
        return this.repos_url;
    }

    public String getEventsUrl() {
        return this.events_url;
    }

    public String getReceivedEventsUrl() {
        return this.received_events_url;
    }

    public String getType() {
        return this.type;
    }

    public boolean isSiteAdmin() {
        return site_admin;
    }

    public String getName() {
        return this.name;
    }

    public String getCompany() {
        return this.company;
    }

    public String getBlog() {
        return this.blog;
    }

    public String getLocation() {
        return this.location;
    }

    public String getEmail() {
        if (this.email != null)
            return this.email;

        return "NULL";
    }

    public String getBio() {
        return this.bio;
    }

    public int getPublicRepos() {
        return this.public_repos;
    }

    public int getPublicGists() {
        return this.public_gists;
    }

    public int getFollowers() {
        return this.followers;
    }

    public int getFollowing() {
        return this.following;
    }

    public String getCreatedAt() {
        return this.created_at;
    }

    public String getUpdatedAt() {
        return this.updated_at;
    }

    public int getId() {
        return this.id;
    }
}
