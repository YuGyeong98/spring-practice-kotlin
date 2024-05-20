package hello.hellospringkotlin.repository

import hello.hellospringkotlin.domain.Member
import jakarta.persistence.EntityManager

class JpaMemberRepository(private val em: EntityManager) : MemberRepository {

    override fun save(member: Member): Member {
        em.persist(member)
        return member
    }

    override fun findById(id: Long): Member? {
        val member = em.find(Member::class.java, id)
        return member
    }

    override fun findByName(name: String): Member? {
        val result = em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
        return result.firstOrNull()
    }

    override fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java)
            .resultList
    }

    override fun updateNameById(id: Long, name: String) {
        val member = em.find(Member::class.java, id)
        member.name = name
    }

    override fun deleteById(id: Long) {
        val member = em.find(Member::class.java, id)
        em.remove(member)
    }
}