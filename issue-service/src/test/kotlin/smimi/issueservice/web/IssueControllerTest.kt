package smimi.issueservice.web

import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import smimi.issueservice.domain.IssueRepository
import smimi.issueservice.domain.enums.IssuePriority
import smimi.issueservice.domain.enums.IssueStatus
import smimi.issueservice.domain.enums.IssueType
import smimi.issueservice.model.IssueRequest
import smimi.issueservice.service.IssueService

@WebMvcTest
internal class IssueControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var issueService: IssueService

    @Test
    fun `save issue`() {
        val issueRequest: IssueRequest = getIssue(1, "테스트 1번 이슈", "테스트 1번 이슈 내용")
    }

    private fun getIssue(id: Int, summary: String, description: String): IssueRequest = IssueRequest(
        summary = summary,
        description = description,
        type = IssueType.TASK,
        priority = IssuePriority.LOW,
        status = IssueStatus.TODO,
    )
}