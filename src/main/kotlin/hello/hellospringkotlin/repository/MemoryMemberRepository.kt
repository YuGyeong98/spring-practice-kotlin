package hello.hellospringkotlin.repository

import hello.hellospringkotlin.domain.Member

class MemoryMemberRepository : MemberRepository {

    private val store = hashMapOf<Long, Member>()
    private var sequence = 0L

    override fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id] = member
        return member
    }

    override fun findById(id: Long): Member? {
        return store[id]
    }

    override fun findByName(name: String): Member? {
        return store.values.find {
            it.name == name
        }
    }

    override fun findAll(): List<Member> {
        return store.values.toList()
    }
}