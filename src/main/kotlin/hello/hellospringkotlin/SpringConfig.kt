package hello.hellospringkotlin

import hello.hellospringkotlin.repository.JdbcTemplateMemberRepository
import hello.hellospringkotlin.repository.MemberRepository
import hello.hellospringkotlin.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(private val dataSource: DataSource) {

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        return JdbcTemplateMemberRepository(dataSource)
    }
}