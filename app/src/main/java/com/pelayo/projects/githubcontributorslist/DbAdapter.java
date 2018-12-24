package com.pelayo.projects.githubcontributorslist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {
    //Variables miembro
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context mContext;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME      = "GITHUB_CONTRIBUTORS";
    private static final String REPOSITORIES_TABLE = "REPOSITORIES";
    private static final String CONTRIBUTORS_TABLE = "CONTRIBUTORS";
    private static final String USERS_TABLE        = "USERS";
    private static final String COL_ID = "_id";

    //Columnas de la tabla REPOSITORIES
    private static final String COL_NAME  = "NAME";
    private static final String COL_OWNER = "OWNER";

    //Columnas de la tabla CONTRIBUTORS
    private static final String COL_LOGIN                = "LOGIN";
    private static final String COL_NODE_ID              = "NODE_ID";
    private static final String COL_AVATAR_URL           = "AVATAR_URL";
    private static final String COL_GRAVATAR_ID          = "GRAVATAR_ID";
    private static final String COL_URL                  = "URL";
    private static final String COL_HTML_URL             = "HTML_URL";
    private static final String COL_FOLLOWERS_URL        = "FOLLOWERS_URL";
    private static final String COL_FOLLOWING_URL        = "FOLLOWING_URL";
    private static final String COL_GISTS_URL            = "GISTS_URL";
    private static final String COL_STARRED_URL          = "STARRED_URL";
    private static final String COL_SUBSCRIPTIONS_URL    = "SUBSCRIPTIONS_URL";
    private static final String COL_ORGANIZATIONS_URL    = "ORGANIZATIONS_URL";
    private static final String COL_REPOS_URL            = "REPOS_URL";
    private static final String COL_EVENTS_URL           = "EVENTS_URL";
    private static final String COL_RECEIVED_EVENTS_URL  = "RECEIVED_EVENTS_URL";
    private static final String COL_TYPE                 = "TYPE";
    private static final String COL_CONTRIBUTIONS        = "CONTRIBUTIONS";
    private static final String COL_ID_REPOSITORY        = "ID_REPOSITORY";
    private static final String COL_SITE_ADMIN           = "SITE_ADMIN";

    //Columnas de la tabla USERS
    private static final String COL_COMPANY      = "COMPANY";
    private static final String COL_BLOG         = "BLOG";
    private static final String COL_LOCATION     = "LOCATION";
    private static final String COL_EMAIL        = "EMAIL";
    private static final String COL_BIO          = "BIO";
    private static final String COL_PUBLIC_REPOS = "PUBLIC_REPOS";
    private static final String COL_PUBLIC_GISTS = "PUBLIC_GISTS";
    private static final String COL_FOLLOWERS    = "FOLLOWERS";
    private static final String COL_FOLLOWING    = "FOLLOWING";
    private static final String COL_CREATED_AT   = "CREATED_AT";
    private static final String COL_UPDATED_AT   = "UPDATED_AT";

    //Indices de la tabla REPOSITORIES
    public static final int INDEX_ID            = 0;
    public static final int INDEX_NAME          = 1;
    public static final int INDEX_OWNER         = 2;

    //Indices de la tabla CONTRIBUTORS
    public static final int INDEX_LOGIN                 = 1;
    public static final int INDEX_NODE_ID               = 2;
    public static final int INDEX_AVATAR_URL            = 3;
    public static final int INDEX_GRAVATAR_ID           = 4;
    public static final int INDEX_URL                   = 5;
    public static final int INDEX_HTML_URL              = 6;
    public static final int INDEX_FOLLOWERS_URL         = 7;
    public static final int INDEX_FOLLOWING_URL         = 8;
    public static final int INDEX_GISTS_URL             = 9;
    public static final int INDEX_STARRED_URL           = 10;
    public static final int INDEX_SUBSCRIPTIONS_URL     = 11;
    public static final int INDEX_ORGANIZATIONS_URL     = 12;
    public static final int INDEX_REPOS_URL             = 13;
    public static final int INDEX_EVENTS_URL            = 14;
    public static final int INDEX_RECEIVED_EVENTS_URL   = 15;
    public static final int INDEX_TYPE                  = 16;
    public static final int INDEX_CONTRIBUTIONS         = 17;
    public static final int INDEX_ID_REPOSITORY         = 18;
    public static final int INDEX_SITE_ADMIN            = 19;

    //Indices de la tabla USERS
    public static final int INDEX_NAME_USERS                = 1;
    public static final int INDEX_LOGIN_USERS               = 2;
    public static final int INDEX_NODE_ID_USERS             = 3;
    public static final int INDEX_AVATAR_URL_USERS          = 4;
    public static final int INDEX_GRAVATAR_ID_USERS         = 5;
    public static final int INDEX_URL_USERS                 = 6;
    public static final int INDEX_HTML_URL_USERS            = 7;
    public static final int INDEX_FOLLOWERS_URL_USERS       = 8;
    public static final int INDEX_FOLLOWING_URL_USERS       = 9;
    public static final int INDEX_GISTS_URL_USERS           = 10;
    public static final int INDEX_STARRED_URL_USERS         = 11;
    public static final int INDEX_SUBSCRIPTIONS_URL_USERS   = 12;
    public static final int INDEX_ORGANIZATIONS_URL_USERS   = 13;
    public static final int INDEX_REPOS_URL_USERS           = 14;
    public static final int INDEX_EVENTS_URL_USERS          = 15;
    public static final int INDEX_RECEIVED_EVENTS_URL_USERS = 16;
    public static final int INDEX_TYPE_USERS                = 17;
    public static final int INDEX_SITE_ADMIN_USERS          = 18;
    public static final int INDEX_COMPANY                   = 19;
    public static final int INDEX_BLOG                      = 20;
    public static final int INDEX_LOCATION                  = 21;
    public static final int INDEX_EMAIL                     = 22;
    public static final int INDEX_BIO                       = 23;
    public static final int INDEX_PUBLIC_REPOS              = 24;
    public static final int INDEX_PUBLIC_GISTS              = 25;
    public static final int INDEX_FOLLOWERS                 = 26;
    public static final int INDEX_FOLLOWING                 = 27;
    public static final int INDEX_CREATED_AT                = 28;
    public static final int INDEX_UPDATED_AT                = 29;

    //Sentencias
    private static final String CREATE_TABLE_REPOSITORIES = "CREATE TABLE IF NOT EXISTS " +
            REPOSITORIES_TABLE + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT, " + COL_OWNER + " TEXT);";

    private static final String CREATE_TABLE_CONTRIBUTORS = "CREATE TABLE IF NOT EXISTS " +
            CONTRIBUTORS_TABLE + " ( " + COL_ID + " INTEGER PRIMARY KEY, "  +
            COL_LOGIN + " TEXT, " + COL_NODE_ID + " TEXT, " + COL_AVATAR_URL + " TEXT, " +
            COL_GRAVATAR_ID + " TEXT, " + COL_URL + " TEXT, " + COL_HTML_URL + " TEXT, " +
            COL_FOLLOWERS_URL + " TEXT, " + COL_FOLLOWING_URL + " TEXT, " + COL_GISTS_URL +
            " TEXT, " + COL_STARRED_URL + " TEXT, " + COL_SUBSCRIPTIONS_URL + " TEXT, " +
            COL_ORGANIZATIONS_URL + " TEXT, " + COL_REPOS_URL + " TEXT, " + COL_EVENTS_URL +
            " TEXT, " + COL_RECEIVED_EVENTS_URL + " TEXT, " + COL_TYPE + " TEXT, " +
            COL_CONTRIBUTIONS + " INTEGER, " + COL_ID_REPOSITORY + " INTEGER, " +
            COL_SITE_ADMIN + " INTEGER );";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " +
            USERS_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT, " +
            COL_LOGIN + " TEXT, " + COL_NODE_ID + " TEXT, " + COL_AVATAR_URL + " TEXT, "  +
            COL_GRAVATAR_ID + " TEXT, " + COL_URL + " TEXT, " + COL_HTML_URL + " TEXT, "  +
            COL_FOLLOWERS_URL + " TEXT, " + COL_FOLLOWING_URL + " TEXT, " + COL_GISTS_URL +
            " TEXT, " + COL_STARRED_URL + " TEXT, "  + COL_SUBSCRIPTIONS_URL + " TEXT, " +
            COL_ORGANIZATIONS_URL + " TEXT, " + COL_REPOS_URL + " TEXT, " + COL_EVENTS_URL +
            " TEXT, " + COL_RECEIVED_EVENTS_URL + " TEXT, " + COL_TYPE + " TEXT, " +
            COL_SITE_ADMIN + " INTEGER, " + COL_COMPANY + " TEXT, " + COL_BLOG + " TEXT, " +
            COL_LOCATION + " TEXT, " + COL_EMAIL + " TEXT, " + COL_BIO + " TEXT, " +
            COL_PUBLIC_REPOS + " TEXT, " + COL_PUBLIC_GISTS + " TEXT, " + COL_FOLLOWERS +
            " INTEGER, " + COL_FOLLOWING + " INTEGER, " + COL_CREATED_AT + " TEXT, " +
            COL_UPDATED_AT + " TEXT);";

    //Constructor
    public DbAdapter(Context context) {
        this.mContext = context;
    }

    //Metodos de apertura y cierre
    public void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
        if (mDbHelper != null)
            mDbHelper.close();
    }

    //Metodos de creación de datos
    public void createRepository(Repository repository) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, repository.getName());
        content.put(COL_OWNER, repository.getOwner());
        mDb.insert(REPOSITORIES_TABLE, null, content);
    }

    public void createRepository(String repositoryName, String repositoryOwner) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, repositoryName);
        content.put(COL_OWNER, repositoryOwner);
        mDb.insert(REPOSITORIES_TABLE, null, content);
    }

    public void createContributor(Contributor contributor) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, contributor.getId());
        content.put(COL_LOGIN, contributor.getLogin());
        content.put(COL_NODE_ID, contributor.getNodeId());
        content.put(COL_AVATAR_URL, contributor.getAvatarUrl());
        content.put(COL_GRAVATAR_ID, contributor.getGravatarId());
        content.put(COL_URL, contributor.getUrl());
        content.put(COL_HTML_URL, contributor.getHtmlUrl());
        content.put(COL_FOLLOWERS_URL, contributor.getFollowersUrl());
        content.put(COL_FOLLOWING_URL, contributor.getFollowingUrl());
        content.put(COL_GISTS_URL, contributor.getGistsUrl());
        content.put(COL_ORGANIZATIONS_URL, contributor.getOrganizationsUrl());
        content.put(COL_REPOS_URL, contributor.getReposUrl());
        content.put(COL_EVENTS_URL, contributor.getEventsUrl());
        content.put(COL_CONTRIBUTIONS, contributor.getContributions());
        content.put(COL_ID_REPOSITORY, contributor.getIdRepository());
        content.put(COL_SITE_ADMIN, contributor.isSiteAdmin());
        mDb.insert(CONTRIBUTORS_TABLE, null, content);
    }

    public void createUser(User user) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, user.getId());
        content.put(COL_NAME, user.getLogin());
        content.put(COL_LOGIN, user.getLogin());
        content.put(COL_NODE_ID, user.getNodeId());
        content.put(COL_AVATAR_URL, user.getAvatarUrl());
        content.put(COL_GRAVATAR_ID, user.getGravatarId());
        content.put(COL_URL, user.getUrl());
        content.put(COL_HTML_URL, user.getHtmlUrl());
        content.put(COL_FOLLOWERS_URL, user.getFollowersUrl());
        content.put(COL_FOLLOWING_URL, user.getFollowersUrl());
        content.put(COL_GISTS_URL, user.getGistsUrl());
        content.put(COL_STARRED_URL, user.getStarredUrl());
        content.put(COL_SUBSCRIPTIONS_URL, user.getSubscriptionsUrl());
        content.put(COL_ORGANIZATIONS_URL, user.getOrganizationsUrl());
        content.put(COL_REPOS_URL, user.getReposUrl());
        content.put(COL_EVENTS_URL, user.getEventsUrl());
        content.put(COL_RECEIVED_EVENTS_URL, user.getReceivedEventsUrl());
        content.put(COL_TYPE, user.getType());
        content.put(COL_SITE_ADMIN, user.isSiteAdmin());
        content.put(COL_COMPANY, user.getCompany());
        content.put(COL_BLOG, user.getBlog());
        content.put(COL_LOCATION, user.getLocation());
        content.put(COL_EMAIL, user.getEmail());
        content.put(COL_BIO, user.getBio());
        content.put(COL_PUBLIC_REPOS, user.getPublicRepos());
        content.put(COL_PUBLIC_GISTS, user.getPublicGists());
        content.put(COL_FOLLOWERS, user.getFollowers());
        content.put(COL_FOLLOWING, user.getFollowing());
        content.put(COL_CREATED_AT, user.getCreatedAt());
        content.put(COL_UPDATED_AT, user.getUpdatedAt());
        mDb.insert(USERS_TABLE, null, content);
    }

    //Metodos de lectura de datos
    public Repository fetchRepositoryById(int id) {
        Cursor cursor = mDb.query(
                REPOSITORIES_TABLE,
                new String[] {
                        COL_ID,
                        COL_NAME,
                        COL_OWNER
                },
                COL_ID + "=?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst())
            return new Repository(
                    cursor.getInt(INDEX_ID),
                    cursor.getString(INDEX_NAME),
                    cursor.getString(INDEX_OWNER));

        return null;
    }

    public Contributor fetchContributorById(int id) {
        Cursor cursor = mDb.query(
                CONTRIBUTORS_TABLE,
                new String[]{
                        COL_ID,
                        COL_LOGIN,
                        COL_NODE_ID,
                        COL_AVATAR_URL,
                        COL_GRAVATAR_ID,
                        COL_URL,
                        COL_HTML_URL,
                        COL_FOLLOWERS_URL,
                        COL_FOLLOWING_URL,
                        COL_GISTS_URL,
                        COL_STARRED_URL,
                        COL_SUBSCRIPTIONS_URL,
                        COL_ORGANIZATIONS_URL,
                        COL_REPOS_URL,
                        COL_EVENTS_URL,
                        COL_RECEIVED_EVENTS_URL,
                        COL_TYPE,
                        COL_CONTRIBUTIONS,
                        COL_ID_REPOSITORY,
                        COL_SITE_ADMIN
                },
                COL_ID + "=?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst())
            return new Contributor(
                    cursor.getString(INDEX_LOGIN),
                    cursor.getString(INDEX_NODE_ID),
                    cursor.getString(INDEX_AVATAR_URL),
                    cursor.getString(INDEX_GRAVATAR_ID),
                    cursor.getString(INDEX_URL),
                    cursor.getString(INDEX_HTML_URL),
                    cursor.getString(INDEX_FOLLOWERS_URL),
                    cursor.getString(INDEX_FOLLOWING_URL),
                    cursor.getString(INDEX_GISTS_URL),
                    cursor.getString(INDEX_STARRED_URL),
                    cursor.getString(INDEX_SUBSCRIPTIONS_URL),
                    cursor.getString(INDEX_ORGANIZATIONS_URL),
                    cursor.getString(INDEX_REPOS_URL),
                    cursor.getString(INDEX_EVENTS_URL),
                    cursor.getString(INDEX_RECEIVED_EVENTS_URL),
                    cursor.getString(INDEX_TYPE),
                    cursor.getInt(INDEX_ID),
                    cursor.getInt(INDEX_ID_REPOSITORY),
                    cursor.getInt(INDEX_CONTRIBUTIONS),
                    cursor.getInt(INDEX_SITE_ADMIN) == 1
            );

        return null;
    }

    public User fetchUserById(int id) {
        Cursor cursor = mDb.query(
                USERS_TABLE,
                new String[] {
                        COL_ID,
                        COL_NAME,
                        COL_LOGIN,
                        COL_NODE_ID,
                        COL_AVATAR_URL,
                        COL_GRAVATAR_ID,
                        COL_URL,
                        COL_HTML_URL,
                        COL_FOLLOWERS_URL,
                        COL_FOLLOWING_URL,
                        COL_GISTS_URL,
                        COL_STARRED_URL,
                        COL_SUBSCRIPTIONS_URL,
                        COL_ORGANIZATIONS_URL,
                        COL_REPOS_URL,
                        COL_EVENTS_URL,
                        COL_RECEIVED_EVENTS_URL,
                        COL_TYPE,
                        COL_SITE_ADMIN,
                        COL_COMPANY,
                        COL_BLOG,
                        COL_LOCATION,
                        COL_EMAIL,
                        COL_BIO,
                        COL_PUBLIC_REPOS,
                        COL_PUBLIC_GISTS,
                        COL_FOLLOWERS,
                        COL_FOLLOWING,
                        COL_CREATED_AT,
                        COL_UPDATED_AT
                },
                COL_ID + "=?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst())
            return new User(
                    cursor.getInt(INDEX_ID),
                    cursor.getString(INDEX_LOGIN_USERS),
                    cursor.getString(INDEX_NODE_ID_USERS),
                    cursor.getString(INDEX_AVATAR_URL_USERS),
                    cursor.getString(INDEX_GRAVATAR_ID_USERS),
                    cursor.getString(INDEX_URL_USERS),
                    cursor.getString(INDEX_HTML_URL_USERS),
                    cursor.getString(INDEX_FOLLOWERS_URL_USERS),
                    cursor.getString(INDEX_FOLLOWING_URL_USERS),
                    cursor.getString(INDEX_GISTS_URL_USERS),
                    cursor.getString(INDEX_STARRED_URL_USERS),
                    cursor.getString(INDEX_SUBSCRIPTIONS_URL_USERS),
                    cursor.getString(INDEX_ORGANIZATIONS_URL_USERS),
                    cursor.getString(INDEX_REPOS_URL_USERS),
                    cursor.getString(INDEX_EVENTS_URL_USERS),
                    cursor.getString(INDEX_RECEIVED_EVENTS_URL_USERS),
                    cursor.getString(INDEX_TYPE_USERS),
                    cursor.getString(INDEX_NAME_USERS),
                    cursor.getString(INDEX_COMPANY),
                    cursor.getString(INDEX_BLOG),
                    cursor.getString(INDEX_LOCATION),
                    cursor.getString(INDEX_EMAIL),
                    cursor.getString(INDEX_BIO),
                    cursor.getString(INDEX_CREATED_AT),
                    cursor.getString(INDEX_UPDATED_AT),
                    cursor.getInt(INDEX_PUBLIC_REPOS),
                    cursor.getInt(INDEX_PUBLIC_GISTS),
                    cursor.getInt(INDEX_FOLLOWERS),
                    cursor.getInt(INDEX_FOLLOWING),
                    (cursor.getInt(INDEX_SITE_ADMIN) == 1)
            );

        return null;
    }

    public Cursor fetchAllContributorsByRepositoryId(int repositoryId) {
        Cursor cursor = mDb.query(CONTRIBUTORS_TABLE, new String[] {
                        COL_ID,
                        COL_LOGIN,
                        COL_NODE_ID,
                        COL_AVATAR_URL,
                        COL_GRAVATAR_ID,
                        COL_URL,
                        COL_HTML_URL,
                        COL_FOLLOWERS_URL,
                        COL_FOLLOWING_URL,
                        COL_GISTS_URL,
                        COL_STARRED_URL,
                        COL_SUBSCRIPTIONS_URL,
                        COL_ORGANIZATIONS_URL,
                        COL_REPOS_URL,
                        COL_EVENTS_URL,
                        COL_RECEIVED_EVENTS_URL,
                        COL_TYPE,
                        COL_ID,
                        COL_ID_REPOSITORY,
                        COL_CONTRIBUTIONS,
                        COL_SITE_ADMIN
                },
                COL_ID_REPOSITORY + "=?",
                new String[] {String.valueOf(repositoryId)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public Cursor fetchAllRepositories() {
        Cursor cursor = mDb.query(
                REPOSITORIES_TABLE,
                new String[] {
                        COL_ID,
                        COL_NAME,
                        COL_OWNER
                },
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    //Metodos para actualizar las filas
    public void updateRepository(Repository repository) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, repository.getName());
        content.put(COL_OWNER, repository.getOwner());
        mDb.update(REPOSITORIES_TABLE, content,COL_ID + "=?", new String[] {
                String.valueOf(repository.getId())});
    }

    public void updateContributor(Contributor contributor) {
        ContentValues content = new ContentValues();
        content.put(COL_LOGIN, contributor.getLogin());
        content.put(COL_NODE_ID, contributor.getNodeId());
        content.put(COL_AVATAR_URL, contributor.getAvatarUrl());
        content.put(COL_GRAVATAR_ID, contributor.getGravatarId());
        content.put(COL_URL, contributor.getUrl());
        content.put(COL_HTML_URL, contributor.getHtmlUrl());
        content.put(COL_FOLLOWERS_URL, contributor.getFollowersUrl());
        content.put(COL_FOLLOWING_URL, contributor.getFollowingUrl());
        content.put(COL_GISTS_URL, contributor.getGistsUrl());
        content.put(COL_STARRED_URL, contributor.getStarredUrl());
        content.put(COL_SUBSCRIPTIONS_URL, contributor.getSubscriptionsUrl());
        content.put(COL_ORGANIZATIONS_URL, contributor.getOrganizationsUrl());
        content.put(COL_REPOS_URL, contributor.getReposUrl());
        content.put(COL_EVENTS_URL, contributor.getEventsUrl());
        content.put(COL_RECEIVED_EVENTS_URL, contributor.getReceivedEventsUrl());
        content.put(COL_TYPE, contributor.getType());
        content.put(COL_ID_REPOSITORY, contributor.getIdRepository());
        content.put(COL_CONTRIBUTIONS, contributor.getContributions());
        content.put(COL_SITE_ADMIN, contributor.isSiteAdmin());
        mDb.update(CONTRIBUTORS_TABLE, content, COL_ID + "=?", new String[] {
                String.valueOf(contributor.getId())});
    }

    //Metodos para borrar datos
    public void deleteRepositoryById(int id) {
        mDb.delete(REPOSITORIES_TABLE, COL_ID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteRepositoryByRepositoryId(int id) {
        mDb.delete(REPOSITORIES_TABLE, COL_ID + "=?", new String[] {
                String.valueOf(id)});
    }

    public void deleteContributorById(int id) {
        mDb.delete(CONTRIBUTORS_TABLE, COL_ID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteContributorsByRepositoryId(int id) {
        mDb.delete(CONTRIBUTORS_TABLE, COL_ID_REPOSITORY + "=?", new String[] {
                String.valueOf(id)});
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null,
                    DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_REPOSITORIES);
            db.execSQL(CREATE_TABLE_CONTRIBUTORS);
            db.execSQL(CREATE_TABLE_USERS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //TODO: Implementar codigo para gestionar la actualización de la base de datos
        }
    }
}
