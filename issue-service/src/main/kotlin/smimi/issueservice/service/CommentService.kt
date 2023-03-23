package smimi.issueservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import smimi.issueservice.domain.Comment
import smimi.issueservice.domain.CommentRepository
import smimi.issueservice.domain.Issue
import smimi.issueservice.domain.IssueRepository
import smimi.issueservice.exception.NotFoundException
import smimi.issueservice.model.CommentRequest
import smimi.issueservice.model.CommentResponse
import smimi.issueservice.model.toResponse

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(issueId: Long, userId: Long, username: String, request: CommentRequest): CommentResponse {
        val issue = issue(issueId)

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }

    private fun issue(issueId: Long): Issue {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("not exist issue $issueId")
        return issue
    }

    @Transactional
    fun edit(id: Long, userId: Long, request: CommentRequest): CommentResponse? {

        return commentRepository.findByIdAndUserId(id, userId)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(issueId: Long, id: Long, userId: Long) {
        val issue = issue(issueId)
        commentRepository.findByIdAndUserId(id, userId)?.let {
            comment -> issue.comments.remove(comment)
        }
    }
}