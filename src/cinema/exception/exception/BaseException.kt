package cinema.exception.exception

import java.lang.Exception

open class BaseException (override val message: String?): Exception(message) {
}