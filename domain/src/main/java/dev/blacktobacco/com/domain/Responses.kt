package dev.blacktobacco.com.domain

sealed class ServerResponse
class Correct: ServerResponse()
class UnknownError: ServerResponse()

//Auth
class EmailNotVerifiedError: ServerResponse()
class WrongCredentialsException: ServerResponse()
class UnusualActivityException: ServerResponse()
class WeakPasswordException: ServerResponse()
class EmailAlreadyInUseException: ServerResponse()





