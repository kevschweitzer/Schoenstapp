package dev.blacktobacco.com.domain.users_capitals

interface UsersCapitalsRepository {

    fun createAssociationForUser(uid: String)
    fun addOwnedCapital(capitalId: String) 
}