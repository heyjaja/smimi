package smimi.issueservice.domain

import org.springframework.data.jpa.repository.JpaRepository
import smimi.issueservice.domain.enums.IssueStatus

interface IssueRepository : JpaRepository<Issue, Long> {

    fun findAllByStatusOrderByCreatedDateDesc(status: IssueStatus): List<Issue>
}