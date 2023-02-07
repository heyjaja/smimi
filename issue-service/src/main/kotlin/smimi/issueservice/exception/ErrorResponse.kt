package smimi.issueservice.exception

data class ErrorResponse(
    val code: Int, // custom code
    val message: String,
)
