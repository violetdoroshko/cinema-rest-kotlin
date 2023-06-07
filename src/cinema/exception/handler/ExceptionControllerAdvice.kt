package cinema.exception.handler

import cinema.exception.exception.AccessDeniedException
import cinema.exception.exception.BaseException
import cinema.exception.model.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleBaseException(ex: BaseException): ResponseEntity<ErrorDto> {

        val errorMessage = ErrorDto(ex.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleAccessDeniedException(ex: AccessDeniedException): ResponseEntity<ErrorDto> {

        val errorMessage = ErrorDto(ex.message)
        return ResponseEntity(errorMessage, HttpStatus.UNAUTHORIZED)
    }
}