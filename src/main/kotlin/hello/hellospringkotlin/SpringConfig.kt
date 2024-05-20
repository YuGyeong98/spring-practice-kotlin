package hello.hellospringkotlin

import hello.hellospringkotlin.repository.JpaMemberRepository
import hello.hellospringkotlin.repository.MemberRepository
import hello.hellospringkotlin.service.MemberService
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig(private val em: EntityManager) {

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        return JpaMemberRepository(em)
    }
}