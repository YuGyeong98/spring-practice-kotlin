package hello.hellospringkotlin.service

import hello.hellospringkotlin.domain.Member
import hello.hellospringkotlin.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var memberService: MemberService

    @Test
    fun 회원가입() {
        // given
        val member = Member("hello")

        // when
        val saveId = memberService.join(member)

        // then
        val findMember = memberService.findOne(saveId)
        assertThat(findMember?.name).isEqualTo(member.name)
    }

    @Test
    fun 중복_회원_예외() {
        // given
        val member1 = Member("spring")
        val member2 = Member("spring")

        // when
        memberService.join(member1)
        val e = assertThrows<IllegalStateException> {
            memberService.join(member2)
        }

        // then
        assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
    }

    @Test
    fun 회원_이름_변경() {
        // given
        val member = Member("spring1")
        val id = memberService.join(member)

        // when
        memberService.updateMemberName(id, "spring2")

        // then
        val name = memberService.findOne(id)?.name
        assertThat(name).isEqualTo("spring2")
    }

    @Test
    fun 회원삭제() {
        // given
        val member1 = Member("spring1")
        val id1 = memberService.join(member1)
        val member2 = Member("spring2")
        memberService.join(member2)

        // when
        memberService.deleteMember(id1)

        // then
        val id = memberService.findOne(id1)
        assertThat(id).isEqualTo(null)
    }
}