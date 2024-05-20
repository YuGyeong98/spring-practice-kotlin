package hello.hellospringkotlin.service

import hello.hellospringkotlin.domain.Member
import hello.hellospringkotlin.repository.MemberRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
class MemberService(private val memberRepository: MemberRepository) {

    /**
     * 회원 가입
     */
    fun join(member: Member): Long {
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id
    }

    private fun validateDuplicateMember(member: Member) {
        memberRepository.findByName(member.name)?.let {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    /**
     * 전체 회원 조회
     */
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }

    /**
     * 회원 이름 변경
     */
    fun updateMemberName(id: Long, name: String) {
        memberRepository.updateNameById(id, name)
    }

    /**
     * 회원 삭제
     */
    fun deleteMember(id: Long) {
        memberRepository.deleteById(id)
    }
}