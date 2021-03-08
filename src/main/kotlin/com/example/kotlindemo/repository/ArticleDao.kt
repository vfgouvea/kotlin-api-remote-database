package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Article
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException


@Repository
class ArticleDao {

    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null

    fun loadAll(): List<Article>? {
        return jdbcTemplate!!.query("select * from article") { resultSet: ResultSet?, i: Int -> toArticle(resultSet) }
    }

    @Throws(SQLException::class)
    private fun toArticle(resultSet: ResultSet?): Article? {
        val article = resultSet?.let { resultSet ->
            Article(id = resultSet.getLong("id"), title = resultSet.getString("title"), content = resultSet.getString("content"))
        }
        return article
    }

}