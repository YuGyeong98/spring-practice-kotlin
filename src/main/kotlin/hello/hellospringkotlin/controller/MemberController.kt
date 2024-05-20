package hello.hellospringkotlin.controller

import hello.hellospringkotlin.domain.Member
import hello.hellospringkotlin.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/members")
    fun list(model: Model): String {
        val members = memberService.findMembers()

        model.addAttribute("members", members)

        return "members/memberList"
    }

    @GetMapping("/members/{id}")
    fun updateForm(@PathVariable("id") id: Long): String {
        return "members/updateMemberForm"
    }

    @PutMapping("/members/{id}")
    fun update(@PathVariable("id") id: Long, @RequestParam("name") name: String, model: Model): String {
        model.addAllAttributes(mapOf("id" to id, "name" to name))
        memberService.updateMemberName(id, name)
        return "redirect:/members"
    }

    @DeleteMapping("/members/{id}")
    fun delete(@PathVariable("id") id: Long): String {
        memberService.deleteMember(id)
        return "redirect:/members"
    }
}