package hello.hellospringkotlin.repository

import hello.hellospringkotlin.domain.Member
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import javax.sql.DataSource

class JdbcTemplateMemberRepository(dataSource: DataSource) : MemberRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters = hashMapOf<String, Any>()
        parameters["name"] = member.name

        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()
        return member
    }

    override fun findById(id: Long): Member? {
        val result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id)
        return result.firstOrNull()
    }

    override fun findByName(name: String): Member? {
        val result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name)
        return result.firstOrNull()
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("select * from member", memberRowMapper())
    }

    override fun updateNameById(id: Long, name: String) {
        jdbcTemplate.update("update member set name = ? where id = ?", name, id)
    }

    override fun deleteById(id: Long) {
        jdbcTemplate.update("delete from member where id = ?", id)
    }

    private fun memberRowMapper(): RowMapper<Member> {
        return RowMapper { rs, _ ->
            Member(id = rs.getLong("id"), name = rs.getString("name"))
        }
    }
}