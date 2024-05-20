package hello.hellospringkotlin.controller

import hello.hellospringkotlin.domain.Member
import hello.hellospringkotlin.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(@Autowired private val memberService: MemberService) {

    @GetMapping("/members/new")
    fun createForm(): String {
        return "/members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(form: MemberForm): String {
        val member = Member(form.name)

        memberService.join(member)

        return "redirect:/"
    }
}