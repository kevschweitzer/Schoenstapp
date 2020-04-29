package dev.blacktobacco.com.data.account;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Ldev/blacktobacco/com/data/account/AccountServiceImpl;", "Ldev/blacktobacco/com/domain/account/AccountService;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "sendForgotEmail", "Lio/reactivex/Observable;", "", "mail", "", "data_release"})
public final class AccountServiceImpl implements dev.blacktobacco.com.domain.account.AccountService {
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.lang.Boolean> sendForgotEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String mail) {
        return null;
    }
    
    public AccountServiceImpl() {
        super();
    }
}