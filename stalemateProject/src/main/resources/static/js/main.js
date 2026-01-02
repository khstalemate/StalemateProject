const loginForm = document.querySelector("#loginForm");
const loginId = document.querySelector("#loginForm input[name='memberId']");
const loginPw = document.querySelector("#loginForm input[name='memberPw']");

if (loginForm != null) {
  loginForm.addEventListener("submit", function (e) {
    if (loginId.value.trim().length === 0) {
      alert("아이디가 미작성 되었습니다.");
      e.preventDefault();
      loginId.focus();

      return;
    }

    if (loginPw.value.trim().length === 0) {
      alert("비밀번호가 미작성 되었습니다.");
      e.preventDefault();
      loginPw.focus();

      return;
    }
  });
}

//내게시글 갯수 가져오기
window.onload = function () {
  fetch("/member/myPostCount")
    .then((resp) => {
      if (resp.ok) return resp.text();
      throw new Error("요청 실패");
    })
    .then((count) => {
      console.log("가져온 갯수 : " + count);
      const display = document.getElementById("myPostCount");
      if (display) display.innerText = count;
    })
    .catch((err) => {
      console.error(err);
    }); 
}; 