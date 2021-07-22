<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="아이디찾기" />
<%@ include file="../part/head.jspf"%>

<section class="section section-member-login flex flex-grow justify-center items-center">
	<div class="w-full max-w-md px-2">
		<div class="card bordered shadow-lg">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer">
					<i class="fas fa-chevron-left"></i>
				</a>
				<span>아이디찾기</span>
			</div>

			<div class="px-2 py-4">
				<script>
					let MemberFindId__submitDone = false;
					function MemberFindId__submit(form) {
						if (MemberFindId__submitDone) {
							return;
						}

						if (form.name.value.length == 0) {
							alert('아이디를 입력해주세요.');
							form.name.focus();

							return;
						}

						if (form.email.value.length == 0) {
							alert('이메일 입력해주세요.');
							form.email.focus();

							return;
						}

						form.submit();
						MemberFindId__submitDone = true;
					}
				</script>
				<form action="../member/doFindLoginId" method="POST"
					onsubmit="MemberFindId__submit(this); return false;">
					<div class="form-control">
						<label class="label">
							<span class="label-text">이름 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="name" type="text" placeholder="이름을 입력해주세요."/>
						</div>
					</div>

					<div class="form-control">
						<label class="label">
							<span class="label-text">이메일 입력</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="email" type="text" placeholder="이메일을 입력해주세요." />
						</div>
					</div>

					<div class="btns">
						<button type="submit" class="btn btn-link">아이디 찾기</button>
						<a href="javascript:history.back();" class="btn btn-link">
							취소
						</a>
					</div>
				</form>
				
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>