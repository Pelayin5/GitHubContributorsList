package com.pelayo.projects.githubcontributorslist;

public class Contributor {
    //Propiedades privadas
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

    private int id;
    private int contributions;
    private int idRepository;

    private boolean site_admin;

    //Constructor
    public Contributor(String login, String nodeid, String avatarUrl, String gravatarId,
                       String url, String htmlUrl, String followersUrl, String followingUrl,
                       String gistsUrl, String starredUrl, String subscriptionsUrl,
                       String organizationsUrl, String reposUrl, String eventsUrl,
                       String receivedEventsUrl, String type, int id, int idRepository,
                       int contributions, boolean siteAdmin) {

        this.login = login;
        this.node_id = nodeid;
        this.avatar_url = avatarUrl;
        this.gravatar_id = gravatarId;
        this.url = url;
        this.html_url = htmlUrl;
        this.followers_url = followersUrl;
        this.following_url = followingUrl;
        this.gists_url = gistsUrl;
        this.starred_url = starredUrl;
        this.subscriptions_url = subscriptionsUrl;
        this.organizations_url = organizationsUrl;
        this.repos_url = reposUrl;
        this.events_url = eventsUrl;
        this.received_events_url = receivedEventsUrl;
        this.type = type;

        this.id = id;
        this.idRepository = idRepository;
        this.contributions = contributions;

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

    public int getId() {
        return this.id;
    }

    public int getIdRepository() {
        return this.idRepository;
    }

    public int getContributions() {
        return this.contributions;
    }

    public boolean isSiteAdmin() {
        return site_admin;
    }

    public void setIdRepository(int id) {
        this.idRepository = id;
    }

}
