package dev.blacktobacco.com.data.capital;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\rH\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u0006\u0010\u0019\u001a\u00020\u0016H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Ldev/blacktobacco/com/data/capital/CapitalsRepositoryImpl;", "Ldev/blacktobacco/com/domain/capitals/CapitalsRepository;", "getCurrentUserUseCase", "Ldev/blacktobacco/com/domain/user/GetCurrentUserUseCase;", "(Ldev/blacktobacco/com/domain/user/GetCurrentUserUseCase;)V", "capitals", "Lcom/google/firebase/firestore/CollectionReference;", "getCapitals", "()Lcom/google/firebase/firestore/CollectionReference;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "usersCapitals", "addCapitals", "Lio/reactivex/Observable;", "", "id", "", "capitalsAmount", "deleteCapital", "", "exitCapital", "", "Ldev/blacktobacco/com/domain/capitals/Capital;", "joinCapital", "newCapital", "capital", "data_release"})
public final class CapitalsRepositoryImpl implements dev.blacktobacco.com.domain.capitals.CapitalsRepository {
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference capitals = null;
    private final com.google.firebase.firestore.CollectionReference usersCapitals = null;
    private final dev.blacktobacco.com.domain.user.GetCurrentUserUseCase getCurrentUserUseCase = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.CollectionReference getCapitals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.lang.Boolean> newCapital(@org.jetbrains.annotations.NotNull()
    dev.blacktobacco.com.domain.capitals.Capital capital) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.util.List<dev.blacktobacco.com.domain.capitals.Capital>> getCapitals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.lang.Integer> addCapitals(@org.jetbrains.annotations.NotNull()
    java.lang.String id, int capitalsAmount) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.lang.Boolean> joinCapital(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.lang.Boolean> deleteCapital(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.lang.Boolean> exitCapital(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public CapitalsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    dev.blacktobacco.com.domain.user.GetCurrentUserUseCase getCurrentUserUseCase) {
        super();
    }
}