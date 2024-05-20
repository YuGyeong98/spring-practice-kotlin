package hello.hellospringkotlin

import hello.hellospringkotlin.repository.MemberRepository
import hello.hellospringkotlin.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig(private val memberRepository: MemberRepository) {

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository)
    }
}