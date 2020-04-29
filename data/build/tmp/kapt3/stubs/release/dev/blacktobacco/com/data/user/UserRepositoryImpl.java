package dev.blacktobacco.com.data.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Ldev/blacktobacco/com/data/user/UserRepositoryImpl;", "Ldev/blacktobacco/com/domain/user/UserRepository;", "context", "Landroid/content/Context;", "appDatabase", "Ldev/blacktobacco/com/data/database/AppDatabase;", "(Landroid/content/Context;Ldev/blacktobacco/com/data/database/AppDatabase;)V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "create", "Lio/reactivex/Observable;", "Ldev/blacktobacco/com/domain/ServerResponse;", "user", "", "password", "exists", "getCurrentUser", "Ldev/blacktobacco/com/domain/user/User;", "saveUser", "", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "setCurrentUserUid", "uid", "signOut", "data_release"})
public final class UserRepositoryImpl implements dev.blacktobacco.com.domain.user.UserRepository {
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    private final android.content.Context context = null;
    private final dev.blacktobacco.com.data.database.AppDatabase appDatabase = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<dev.blacktobacco.com.domain.ServerResponse> create(@org.jetbrains.annotations.NotNull()
    java.lang.String user, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    @java.lang.Override()
    public void signOut() {
    }
    
    private final void setCurrentUserUid(java.lang.String uid) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public dev.blacktobacco.com.domain.user.User getCurrentUser() {
        return null;
    }
    
    private final void saveUser(com.google.firebase.auth.FirebaseUser currentUser) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<dev.blacktobacco.com.domain.ServerResponse> exists(@org.jetbrains.annotations.NotNull()
    java.lang.String user, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    public UserRepositoryImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    dev.blacktobacco.com.data.database.AppDatabase appDatabase) {
        super();
    }
}