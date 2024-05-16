package hello.hellospringkotlin.controller

import hello.hellospringkotlin.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class MemberController(@Autowired private val memberService: MemberService) {}