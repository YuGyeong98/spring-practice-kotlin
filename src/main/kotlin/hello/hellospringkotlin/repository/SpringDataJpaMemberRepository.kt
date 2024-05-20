package hello.hellospringkotlin.repository

import hello.hellospringkotlin.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SpringDataJpaMemberRepository : JpaRepository<Member, Long>, MemberRepository {

    override fun findByName(name: String): Member?

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.name = :name where m.id = :id")
    override fun updateNameById(@Param("id") id: Long, @Param("name") name: String)
}