package hello.hellospringkotlin.repository

import hello.hellospringkotlin.domain.Member

interface MemberRepository {
    fun save(member: Member): Member
    fun findById(id: Long): Member?
    fun findByName(name: String): Member?
    fun findAll(): List<Member>
    fun update(id: Long, name: String): Member?
    fun delete(id: Long): List<Member>
}