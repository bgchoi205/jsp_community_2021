package com.jhs.exam.exam2.http.controller;

import java.util.Random;

import com.jhs.exam.exam2.http.Rq;

public class AdmArticleController extends Controller {
	public void init() {
		
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "list":
			actionShowList(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	

	private void actionShowList(Rq rq) {
		rq.jsp("adm/article/list");
	}
}
