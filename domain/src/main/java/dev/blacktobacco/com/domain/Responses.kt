package dev.blacktobacco.com.domain

sealed class ServerResponse
class Correct: ServerResponse()
class UnknownError: ServerResponse()

//Auth
class EmailNotVerifiedError: ServerResponse()
class WrongCredentialsException: ServerResponse()





