package com.jhs.exam.exam2.http.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhs.exam.exam2.app.App;
import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.http.controller.Controller;
import com.jhs.mysqliutil.MysqlUtil;

abstract public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Rq rq = new Rq(req, resp);

		if (rq.isInvalid()) {
			rq.print("올바른 요청이 아닙니다.");
		}

		if (runInterceptors(rq) == false) {
			return;
		}

		Controller controller = getControllerByRq(rq);
		
		if (controller != null) {
			controller.performAction(rq);

			MysqlUtil.closeConnection();
		}else {
			rq.print("올바른 요청이 아닙니다.");
		}
		
		if(Container.app.isReady() == false) {
			rq.print("필수파일 만들었는지 체크해주세요.");
			return;
		}
	}
	
	
	private Controller getControllerByRq(Rq rq) {
		switch (rq.getControllerTypeName()) {
		case "usr":
			switch (rq.getControllerName()) {
			case "article":
				return Container.usrArticleController;
			case "member":
				return Container.usrMemberController;
			case "home":
				return Container.usrHomeController;

			}
			break;
		case "adm":
			switch (rq.getControllerName()) {
			case "home":
				return Container.admHomeController;
			case "article":
				return Container.admArticleController;
			}
			break;
		case "test":
			switch (rq.getControllerName()) {
			case "mail":
				return Container.testMailController;
			}

			break;
		}

		return null;

	}

	private boolean runInterceptors(Rq rq) {

		if (Container.beforeActionInterceptor.runBeforeAction(rq) == false) {
			return false;
		}

		if (Container.needLoginInterceptor.runBeforeAction(rq) == false) {
			return false;
		}

		if (Container.needLogoutInterceptor.runBeforeAction(rq) == false) {
			return false;
		}
		
		if (Container.needAdminInterceptor.runBeforeAction(rq) == false) {
			return false;
		}

		return true;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
