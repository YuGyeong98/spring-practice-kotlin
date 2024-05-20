package hello.hellospringkotlin.repository

import hello.hellospringkotlin.domain.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MemoryMemberRepositoryTest {

    private val repository = MemoryMemberRepository()

    @Test
    fun save() {
        val member = Member("spring")
        repository.save(member)

        val result = repository.findById(member.id)

        assertThat(result).isEqualTo(member)
    }

    @Test
    fun findByName() {
        val member1 = Member("spring1")
        repository.save(member1)

        val member2 = Member("spring2")
        repository.save(member2)

        val result = repository.findByName("spring1")

        assertThat(result).isEqualTo(member1)
    }

    @Test
    fun findAll() {
        val member1 = Member("spring1")
        repository.save(member1)

        val member2 = Member("spring2")
        repository.save(member2)

        val result = repository.findAll()

        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun update() {
        val member1 = Member("spring1")
        repository.save(member1)
        val member2 = Member("spring2")
        repository.save(member2)

        repository.update(1, "spring3")
        repository.update(2, "spring4")

        val result1 = repository.findByName("spring3")
        val result2 = repository.findByName("spring4")

        assertThat(result1?.name).isEqualTo("spring3")
        assertThat(result2?.name).isEqualTo("spring4")
    }

    @Test
    fun delete() {
        val member1 = Member("spring1")
        repository.save(member1)

        val member2 = Member("spring2")
        repository.save(member2)

        repository.delete(member1.id)
        val result = repository.findAll()

        assertThat(result.size).isEqualTo(1)
    }
}