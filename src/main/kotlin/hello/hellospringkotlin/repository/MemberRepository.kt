package hello.hellospringkotlin.repository

import hello.hellospringkotlin.domain.Member

interface MemberRepository {
    fun save(member: Member): Member
    fun findById(id: Long): Member?
    fun findByName(name: String): Member?
    fun findAll(): List<Member>
    fun updateNameById(id: Long, name: String)
    fun deleteById(id: Long)
}