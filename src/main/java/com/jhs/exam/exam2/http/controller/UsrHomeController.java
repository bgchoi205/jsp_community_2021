package com.jhs.exam.exam2.http.controller;

import java.util.Random;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.http.Rq;

public class UsrHomeController extends Controller {
	
	public void init() {
		
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "main":
			actionShowMain(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	

	private void actionShowMain(Rq rq) {
		rq.jsp("usr/home/main");
	}
}
