package cinema.exception.exception

import java.lang.Exception

class AccessDeniedException (override val message: String?): Exception(message) {
}