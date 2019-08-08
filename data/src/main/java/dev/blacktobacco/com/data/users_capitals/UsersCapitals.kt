package dev.blacktobacco.com.data.users_capitals

data class UsersCapitals(var userId: String = "", var ownedIds: List<String> = listOf(), var joinedIds: List<String> = listOf()) {

    companion object {
        const val USER_ID_FIELD = "userId"
        const val OWNED_IDS_FIELD = "ownedIds"
        const val JOINED_IDS_FIELD = "joinedIds"
    }
}