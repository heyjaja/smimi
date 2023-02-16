package smimi.issueservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import smimi.issueservice.domain.Issue
import smimi.issueservice.domain.IssueRepository
import smimi.issueservice.domain.enums.IssueStatus
import smimi.issueservice.exception.NotFoundException
import smimi.issueservice.model.IssueRequest
import smimi.issueservice.model.IssueResponse

@Service
@Transactional
class IssueService(
    private val issueRepository: IssueRepository,
) {

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

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus)
    = issueRepository.findAllByStatusOrderByCreatedDateDesc(status).map { IssueResponse(it) }

    @Transactional(readOnly = true)
    fun get(id: Long): IssueResponse {
        return IssueResponse(getIssue(id))
    }

    private fun getIssue(id: Long) =
        issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

    fun edit(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue: Issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

        return with(issue) {
            summary = request.summary
            description = request.description
            this.userId = userId
            type = request.type
            priority = request.priority
            status = request.status

            IssueResponse((issueRepository.save(this)))
        }

    }

    fun delete(id: Long) = issueRepository.delete(getIssue(id))
}