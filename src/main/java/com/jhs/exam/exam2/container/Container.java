package com.jhs.exam.exam2.container;

import com.jhs.exam.exam2.http.controller.AdmHomeController;
import com.jhs.exam.exam2.http.controller.TestMailController;
import com.jhs.exam.exam2.http.controller.UsrArticleController;
import com.jhs.exam.exam2.http.controller.UsrHomeController;
import com.jhs.exam.exam2.http.controller.UsrMemberController;
import com.jhs.exam.exam2.interceptor.BeforeActionInterceptor;
import com.jhs.exam.exam2.interceptor.NeedAdminInterceptor;
import com.jhs.exam.exam2.interceptor.NeedLoginInterceptor;
import com.jhs.exam.exam2.interceptor.NeedLogoutInterceptor;
import com.jhs.exam.exam2.repository.ArticleRepository;
import com.jhs.exam.exam2.repository.BoardRepository;
import com.jhs.exam.exam2.repository.MemberRepository;
import com.jhs.exam.exam2.service.ArticleService;
import com.jhs.exam.exam2.service.BoardService;
import com.jhs.exam.exam2.service.MemberService;

public class Container {
	public static BeforeActionInterceptor beforeActionInterceptor;
	public static NeedLoginInterceptor needLoginInterceptor;
	public static NeedLogoutInterceptor needLogoutInterceptor;
	public static NeedAdminInterceptor needAdminInterceptor;
	
	public static ArticleRepository articleRepository;
	public static ArticleService articleService;
	public static UsrArticleController usrArticleController;
	public static TestMailController testMailController;

	public static MemberRepository memberRepository;
	public static MemberService memberService;
	public static UsrMemberController usrMemberController;
	
	public static BoardRepository boardRepository;
	public static BoardService boardService;
	
	public static UsrHomeController usrHomeController;
	public static AdmHomeController  admHomeController;
	
	public static void init() {
		articleRepository = new ArticleRepository();
		memberRepository = new MemberRepository();
		boardRepository = new BoardRepository();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		boardService = new BoardService();
		
		beforeActionInterceptor = new BeforeActionInterceptor();
		needLoginInterceptor = new NeedLoginInterceptor();
		needLogoutInterceptor = new NeedLogoutInterceptor();
		needAdminInterceptor = new NeedAdminInterceptor();
		
		usrArticleController = new UsrArticleController();
		usrMemberController = new UsrMemberController();
		usrHomeController = new UsrHomeController();
		admHomeController = new AdmHomeController();
		testMailController = new TestMailController();
		
		// 객체 초기화
		articleRepository.init();
		memberRepository.init();
		boardRepository.init();
		
		articleService.init();
		memberService.init();
		boardService.init();
		
		beforeActionInterceptor.init();
		needLoginInterceptor.init();
		needLogoutInterceptor.init();
		needAdminInterceptor.init();
		
		usrArticleController.init();
		usrMemberController.init();
		usrHomeController.init();
		admHomeController.init();
		testMailController.init();
		
	}
}
