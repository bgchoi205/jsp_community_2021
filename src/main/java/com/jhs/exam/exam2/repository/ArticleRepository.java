package com.jhs.exam.exam2.repository;

import java.util.List;

import com.jhs.exam.exam2.dto.Article;
import com.jhs.mysqliutil.MysqlUtil;
import com.jhs.mysqliutil.SecSql;

public class ArticleRepository {
	public int write(int boardId, int memberId, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", boardId = ?", boardId);
		sql.append(", memberId = ?", memberId);
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		
		int id = MysqlUtil.insert(sql);
		
		return id;
	}

	public List<Article> getForPrintArticles(int startNumber, int pageCount, String searchKeywordTypeCode, String searchKeyword) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", IFNULL(M.nickName, '삭제된 회원') AS extra__writerName");
		sql.append("FROM article AS A");
		sql.append("LEFT JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		
		if(searchKeyword != null && searchKeyword.length() > 0) {
			switch(searchKeywordTypeCode) {
			case "title" :
				sql.append("WHERE A.title");
				sql.append("LIKE CONCAT ('%', ?, '%')", searchKeyword);
				break;
			case "body" :
				sql.append("WHERE A.body");
				sql.append("LIKE CONCAT ('%', ?, '%')", searchKeyword);
				break;
			case "title,body" :
				sql.append("WHERE A.title");
				sql.append("LIKE CONCAT ('%', ?, '%')", searchKeyword);
				sql.append("OR A.body");
				sql.append("LIKE CONCAT ('%', ?, '%')", searchKeyword);
				break;
			}
			
		}
		
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?, ?", startNumber, pageCount);
		
		return MysqlUtil.selectRows(sql, Article.class);
	}

	public Article getForPrintArticleById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", IFNULL(M.nickName, '삭제된 회원') AS extra__writerName");
		sql.append("FROM article AS A");
		sql.append("LEFT JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ?", id);
		
		return MysqlUtil.selectRow(sql, Article.class);
	}

	public int delete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		
		return MysqlUtil.delete(sql);
	}

	public int modify(int id, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		
		if ( title != null ) {
			sql.append(", title = ?", title);
		}
		
		if ( body != null ) {
			sql.append(", body = ?", body);
		}
		
		sql.append("WHERE id = ?", id);
		
		return MysqlUtil.update(sql);
	}

	public int getTotalArticlesCount(String searchKeywordTypeCode, String searchKeyword) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*)");
		sql.append("FROM article AS A");
		if(searchKeyword != null && searchKeyword.length() > 0) {
			switch(searchKeywordTypeCode) {
			case "title" :
				sql.append("WHERE A.title");
				sql.append("LIKE CONCAT ('%', ?, '%')", searchKeyword);
				break;
			case "body" :
				sql.append("WHERE A.body");
				sql.append("LIKE CONCAT ('%', ?, '%')", searchKeyword);
				break;
			}
			
		}
		return MysqlUtil.selectRowIntValue(sql);
	}
}
