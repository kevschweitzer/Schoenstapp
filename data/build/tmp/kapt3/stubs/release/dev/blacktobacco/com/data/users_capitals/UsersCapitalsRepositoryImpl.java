package dev.blacktobacco.com.data.users_capitals;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000f"}, d2 = {"Ldev/blacktobacco/com/data/users_capitals/UsersCapitalsRepositoryImpl;", "Ldev/blacktobacco/com/domain/users_capitals/UsersCapitalsRepository;", "getCurrentUserUseCase", "Ldev/blacktobacco/com/domain/user/GetCurrentUserUseCase;", "(Ldev/blacktobacco/com/domain/user/GetCurrentUserUseCase;)V", "usersCapitals", "Lcom/google/firebase/firestore/CollectionReference;", "getUsersCapitals", "()Lcom/google/firebase/firestore/CollectionReference;", "addOwnedCapital", "", "capitalId", "", "createAssociationForUser", "uid", "data_release"})
public final class UsersCapitalsRepositoryImpl implements dev.blacktobacco.com.domain.users_capitals.UsersCapitalsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference usersCapitals = null;
    private final dev.blacktobacco.com.domain.user.GetCurrentUserUseCase getCurrentUserUseCase = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.CollectionReference getUsersCapitals() {
        return null;
    }
    
    @java.lang.Override()
    public void createAssociationForUser(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
    }
    
    @java.lang.Override()
    public void addOwnedCapital(@org.jetbrains.annotations.NotNull()
    java.lang.String capitalId) {
    }
    
    public UsersCapitalsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    dev.blacktobacco.com.domain.user.GetCurrentUserUseCase getCurrentUserUseCase) {
        super();
    }
}