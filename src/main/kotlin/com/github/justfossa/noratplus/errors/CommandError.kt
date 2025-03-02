package com.github.justfossa.noratplus.errors

class CommandError(message: String, cause: Throwable) : Error(message, cause)