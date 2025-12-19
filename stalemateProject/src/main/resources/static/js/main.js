
const loginForm = document.querySelector("#loginForm");
const loginId = document.querySelector("#loginForm input[name='memberId']");
const loginPw = document.querySelector("#loginForm input[name='memberPw']"); 

if (loginForm != null) {

  loginForm.addEventListener("submit", function(e) {

    if(loginId.value.trim().length === 0) {

      alert("아이디가 미작성 되었습니다.");
      e.preventDefault();
      loginId.focus(); 

      return;

    }

    if(loginPw.value.trim().length === 0) {

      alert("비밀번호가 미작성 되었습니다.");
      e.preventDefault();
      loginPw.focus();
      
      return;

    }

  })
}
