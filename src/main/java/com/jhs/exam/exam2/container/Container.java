package com.jhs.exam.exam2.container;

import java.util.ArrayList;
import java.util.List;

import com.jhs.exam.exam2.app.App;
import com.jhs.exam.exam2.http.controller.AdmArticleController;
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
	
	private static List<ContainerComponent> containerComponents;
	
	public static App app;
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
	public static AdmArticleController  admArticleController;
	
	public static void init() {
		containerComponents = new ArrayList<>();
		
		app = addContainerComponent(new App());
		articleRepository = addContainerComponent(new ArticleRepository());
		memberRepository = addContainerComponent(new MemberRepository());
		boardRepository = addContainerComponent(new BoardRepository());
		
		articleService = addContainerComponent(new ArticleService());
		memberService = addContainerComponent(new MemberService());
		boardService = addContainerComponent(new BoardService());
		
		beforeActionInterceptor = addContainerComponent(new BeforeActionInterceptor());
		needLoginInterceptor = addContainerComponent(new NeedLoginInterceptor());
		needLogoutInterceptor = addContainerComponent(new NeedLogoutInterceptor());
		needAdminInterceptor = addContainerComponent(new NeedAdminInterceptor());
		
		usrArticleController = addContainerComponent(new UsrArticleController());
		usrMemberController = addContainerComponent(new UsrMemberController());
		usrHomeController = addContainerComponent(new UsrHomeController());
		admHomeController = addContainerComponent(new AdmHomeController());
		admArticleController = addContainerComponent(new AdmArticleController());
		testMailController = addContainerComponent(new TestMailController());
		
		// ?????? ?????????
		for(ContainerComponent containerComponent : containerComponents) {
			containerComponent.init();
		}
		
	}
	
	private static <T> T addContainerComponent(ContainerComponent containerComponent) {
		containerComponents.add(containerComponent);
		
		return (T) containerComponent;
	}
	
}
