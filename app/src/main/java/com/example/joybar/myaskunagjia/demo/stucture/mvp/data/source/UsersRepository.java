package com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.User;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.util.CheckUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joybar on 06/01/2017.
 */

public class UsersRepository implements UsersDataSource {

    private static UsersRepository INSTANCE = null;


    private final UsersDataSource mUsersRemoteDataSource;

    private final UsersDataSource mUsersLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, User> mCachedUsers;
    boolean mCacheIsDirty = false;


    // Prevent direct instantiation.
    private UsersRepository(@NonNull UsersDataSource usersRemoteDataSource,
                            @NonNull UsersDataSource usersLocalDataSource) {
        mUsersRemoteDataSource = usersRemoteDataSource;
        mUsersLocalDataSource = usersLocalDataSource;
    }

    public static UsersRepository getInstance(UsersDataSource usersRemoteDataSource,
                                              UsersDataSource usersLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository(usersRemoteDataSource, usersLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void saveUser(@NonNull User user) {
        CheckUtils.checkNotNull(user);
        mUsersRemoteDataSource.saveUser(user);
        mUsersLocalDataSource.saveUser(user);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedUsers == null) {
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.put(user.getUserId(), user);
    }

    @Override
    public void deleteAllUsers() {
        mUsersRemoteDataSource.deleteAllUsers();
        mUsersLocalDataSource.deleteAllUsers();

        if (mCachedUsers == null) {
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.clear();
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        mUsersRemoteDataSource.deleteUser(CheckUtils.checkNotNull(userId));
        mUsersLocalDataSource.deleteUser(CheckUtils.checkNotNull(userId));

        mCachedUsers.remove(userId);
    }

    @Override
    public void updateUser(@NonNull String userId) {

    }

    @Override
    public User getUser(@NonNull String userId) {
        return null;
    }

    @Override
    public void getUser(@NonNull final String userId, @NonNull final GetUserCallback callback) {
        CheckUtils.checkNotNull(userId);
        CheckUtils.checkNotNull(callback);

        User cachedUser = getUserWithId(userId);

        // Respond immediately with cache if available
        if (cachedUser != null) {
            callback.onUserLoaded(cachedUser);
            return;
        }

        // Load from server/persisted if needed.

        // Is the User in the local data source? If not, query the network.
        mUsersLocalDataSource.getUser(userId, new GetUserCallback() {
            @Override
            public void onUserLoaded(User User) {
                // Do in memory cache update to keep the app UI up to date
                if (mCachedUsers == null) {
                    mCachedUsers = new LinkedHashMap<>();
                }
                mCachedUsers.put(User.getUserId(), User);
                callback.onUserLoaded(User);
            }



            @Override
            public void onUserNotAvailable() {
                mUsersRemoteDataSource.getUser(userId, new GetUserCallback() {
                    @Override
                    public void onUserLoaded(User User) {
                        // Do in memory cache update to keep the app UI up to date
                        if (mCachedUsers == null) {
                            mCachedUsers = new LinkedHashMap<>();
                        }
                        mCachedUsers.put(User.getUserId(), User);
                        callback.onUserLoaded(User);
                    }

                    @Override
                    public void onUserNotAvailable() {
                        callback.onUserNotAvailable();
                    }


                });
            }
        });
    }

    @Override
    public ArrayList<User> getUsers() {
        return null;
    }

    @Override
    public void getUsers(@NonNull final LoadUsersCallback callback) {
        CheckUtils.checkNotNull(callback);
        // Respond immediately with cache if available and not dirty
        if (mCachedUsers != null && !mCacheIsDirty) {
            callback.onUsersLoaded(new ArrayList<>(mCachedUsers.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getUsersFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mUsersLocalDataSource.getUsers(new LoadUsersCallback() {
                @Override
                public void onUsersLoaded(ArrayList<User> users) {
                    refreshCache(users);
                    callback.onUsersLoaded(new ArrayList<>(mCachedUsers.values()));
                }

                @Override
                public void onUserNotAvailable() {
                    getUsersFromRemoteDataSource(callback);
                }


            });
        }
    }

    @Override
    public void refreshUsers() {
        mCacheIsDirty = true;
    }



    private void getUsersFromRemoteDataSource(@NonNull final LoadUsersCallback callback) {
        mUsersRemoteDataSource.getUsers(new LoadUsersCallback() {
            @Override
            public void onUsersLoaded(ArrayList<User> users) {
                refreshCache(users);
                refreshLocalDataSource(users);
                callback.onUsersLoaded(new ArrayList<>(mCachedUsers.values()));
            }

            @Override
            public void onUserNotAvailable() {
                callback.onUserNotAvailable();
            }


        });
    }

    private void refreshCache(List<User> Users) {
        if (mCachedUsers == null) {
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.clear();
        for (User User : Users) {
            mCachedUsers.put(User.getUserId(), User);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<User> Users) {
        mUsersLocalDataSource.deleteAllUsers();
        for (User User : Users) {
            mUsersLocalDataSource.saveUser(User);
        }
    }

    @Nullable
    private User getUserWithId(@NonNull String id) {
        CheckUtils.checkNotNull(id);
        if (mCachedUsers == null || mCachedUsers.isEmpty()) {
            return null;
        } else {
            return mCachedUsers.get(id);
        }
    }
}
