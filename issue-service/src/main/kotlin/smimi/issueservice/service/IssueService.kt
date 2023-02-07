package smimi.issueservice.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import smimi.issueservice.domain.Issue
import smimi.issueservice.domain.IssueRepository
import smimi.issueservice.model.IssueRequest
import smimi.issueservice.model.IssueResponse

@Service
class IssueService(
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status,
        )

        return IssueResponse(issueRepository.save(issue))
    }
}