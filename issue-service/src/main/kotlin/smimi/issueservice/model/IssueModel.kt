package smimi.issueservice.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import smimi.issueservice.domain.Issue
import smimi.issueservice.domain.enums.IssuePriority
import smimi.issueservice.domain.enums.IssueStatus
import smimi.issueservice.domain.enums.IssueType
import java.time.LocalDateTime

data class IssueRequest(
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
)

data class IssueResponse(
    val id: Long,
    val summary: String,
    val description: String,
    val userId: Long,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdDate: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedDate: LocalDateTime?,
) {
    companion object {
        operator fun invoke(issue: Issue) =
            with(issue) {
                IssueResponse(
                    id = id!!,
                    summary = summary,
                    description = description,
                    userId = userId,
                    type = type,
                    priority = priority,
                    status = status,
                    createdDate = createdDate,
                    updatedDate = updatedDate,
                )
            }
    }
}