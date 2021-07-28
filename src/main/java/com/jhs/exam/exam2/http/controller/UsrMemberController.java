package com.jhs.exam.exam2.http.controller;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.service.MemberService;
import com.jhs.exam.exam2.util.Ut;

public class UsrMemberController extends Controller {
	private MemberService memberService = Container.memberService;

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "login":
			actionShowLogin(rq);
			break;
		case "doLogin":
			actionDoLogin(rq);
			break;
		case "doLogout":
			actionDoLogout(rq);
			break;
		case "join":
			actionShowJoin(rq);
			break;
		case "doJoin":
			actionDoJoin(rq);
			break;
		case "findLoginId":
			actionShowFindLoginId(rq);
			break;
		case "doFindLoginId":
			actionDoFindLoginId(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoFindLoginId(Rq rq) {
		String name = rq.getParam("name", "");
		String email = rq.getParam("email", "");
		boolean isExist = false;
		
		if (name.length() == 0) {
			rq.historyBack("name을 입력해주세요.");
			return;
		}
		
		if (email.length() == 0) {
			rq.historyBack("email을 입력해주세요.");
			return;
		}
		
		ResultData doFindLoginIdRd = memberService.doFindLoginId(name, email);

		if (doFindLoginIdRd.isFail()) {
			rq.setAttr("message", "일치하는 회원이 없습니다.");
			rq.jsp("usr/member/findLoginId");
			return;
		}
		
		Member member = (Member) doFindLoginIdRd.getBody().get("member");
		
		rq.setAttr("message", "회원님의 아이디는 " + member.getLoginId() + " 입니다.");
		rq.jsp("usr/member/findLoginId");
		
	}

	private void actionShowFindLoginId(Rq rq) {
		rq.jsp("usr/member/findLoginId");
		
	}

	private void actionDoJoin(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String loginPw = rq.getParam("loginPw", "");
		String name = rq.getParam("name", "");
		String nickname = rq.getParam("nickname", "");
		String email = rq.getParam("email", "");
		String cellphoneNo = rq.getParam("cellphoneNo", "");

		if (loginId.length() == 0) {
			rq.historyBack("loginId를 입력해주세요.");
			return;
		}

		if (loginPw.length() == 0) {
			rq.historyBack("loginPw를 입력해주세요.");
			return;
		}
		
		if (name.length() == 0) {
			rq.historyBack("name을 입력해주세요.");
			return;
		}
		
		if (nickname.length() == 0) {
			rq.historyBack("nickname을 입력해주세요.");
			return;
		}
		
		if (email.length() == 0) {
			rq.historyBack("email을 입력해주세요.");
			return;
		}
		
		if (cellphoneNo.length() == 0) {
			rq.historyBack("cellphoneNo를 입력해주세요.");
			return;
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, email, cellphoneNo);

		if (joinRd.isFail()) {
			rq.historyBack(joinRd.getMsg());
			return;
		}
		
		rq.replace(joinRd.getMsg(), "../../");
	}

	private void actionShowJoin(Rq rq) {
		rq.jsp("usr/member/join");
	}

	private void actionDoLogout(Rq rq) {
		rq.removeSessionAttr("loginedMemberJson");
		rq.replace(null, "../article/list");
	}

	private void actionDoLogin(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String loginPw = rq.getParam("loginPw", "");
		String redirectUri = rq.getParam("redirectUri", "../../");

		if (loginId.length() == 0) {
			rq.historyBack("loginId를 입력해주세요.");
			return;
		}

		if (loginPw.length() == 0) {
			rq.historyBack("loginPw를 입력해주세요.");
			return;
		}

		ResultData loginRd = memberService.login(loginId, loginPw);

		if (loginRd.isFail()) {
			rq.historyBack(loginRd.getMsg());
			return;
		}
		
		Member member = (Member) loginRd.getBody().get("member");

		rq.setSessionAttr("loginedMemberJson", Ut.toJson(member, ""));
		
		rq.replace(loginRd.getMsg(), redirectUri);
		
	}

	private void actionShowLogin(Rq rq) {
		
//		String referer = rq.getHeader();
//		rq.setAttr("referer", referer);
		
		rq.jsp("usr/member/login");
	}
}
