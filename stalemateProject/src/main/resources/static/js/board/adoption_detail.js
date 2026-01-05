document.querySelector("#goToList").addEventListener("click", () => {
  location.href = "/adoption/";
});

// 상태 수정
const statusChangeBtn = document.querySelector("#statusChangeBtn");
const statusSelect = document.querySelector("#statusSelect");
if(statusChangeBtn && statusSelect) {
  statusChangeBtn.addEventListener("click", ()=>{
    if (statusSelect.style.display === "none" || statusSelect.style.display === "") {
          statusSelect.style.display = "inline-block";
          statusSelect.focus();
        } else {
          statusSelect.style.display = "none";
        }
  }); 
}

// 댓글 목록 조회
const replyListContent = document.querySelector("#replyList");

const selectReplyList = () => {
  fetch(`/reply?postNo=${postNo}`)
  .then(resp => resp.json())
  .then(replyList => {
    replyListContent.innerHTML = "";

    for(let reply of replyList) {
      const row = document.createElement("div");
      row.classList.add("comment-item");

      const profileImg = document.createElement("img");
      profileImg.classList.add("profile-avatar-large");
      profileImg.src = reply.profileImg ? reply.profileImg : "/images/logo.jpg";
      
      const nameTime = document.createElement("div");
      nameTime.classList.add("detail-meta", "comment-meta");

      const name = document.createElement("strong");
      name.innerText = reply.memberName;

      const time = document.createElement("span");
      time.innerText = reply.replyTime;

      nameTime.append(name, time);

      const content = document.createElement("p");
      content.classList.add("comment-text");
      content.innerText = reply.replyContent;

      row.append(profileImg, nameTime, content);

      if(loginMemberNo != null && loginMemberNo == reply.memberNo) {
        const updateBtn = document.createElement("button");
        updateBtn.classList.add("btn" ,"btn-primary");
        updateBtn.innerText = "수정";
        
        updateBtn.setAttribute("onclick", 
          `showUpdateReply(${reply.replyNo}, this)`
        );

        const deleteBtn = document.createElement("button");
        deleteBtn.classList.add("btn", "btn-primary");
        deleteBtn.innerText = "삭제";

        deleteBtn.setAttribute("onclick",
          `deleteReply(${reply.replyNo})`
        );

        row.append(updateBtn, deleteBtn);
      }
      replyListContent.append(row);
    }
  })
  .catch(err => console.log(err));
}

document.addEventListener("DOMContentLoaded", () => {
  selectReplyList();
});


// 댓글 등록
const insert = document.querySelector("#insertReplyBtn");
const replyContent = document.querySelector("#replyContent");

if(insert && replyContent) {
  insert.addEventListener("click", () => {
    if(loginMemberNo == null) {
      alert("로그인 후 이용해주세요.")
      return "common/main";
    }

    if(replyContent.value.trim().length == 0) {
      alert("댓글을 입력해주세요.");
      replyContent.focus();
      return;
    }

    const data = {
      "replyContent" : replyContent.value,
      "postNo" : postNo,
      "memberNo" : loginMemberNo
    }

    fetch("/reply", {
      method: "POST",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
      if(result > 0) {
        alert("댓글이 등록되었습니다.");
        replyContent.value = "";
        selectReplyList();
      } else {
        alert("댓글 등록에 실패했습니다.");
      }
    })
    .catch(err => console.log(err));
  })
}

// 댓글 수정
let beforeReplyRow;
let beforeContent = "";

const showUpdateReply = (replyNo, btn) => {
  const temp = document.querySelector(".comment-textarea-update");
  if(temp != null){
    if(confirm("수정 중인 댓글이 있습니다. 현재 댓글을 수정하시겠습니까?")) {
      const row = temp.closest(".comment-item");
      row.replaceWith(beforeReplyRow);
    } else {
      return;
    }
  }

  const row = btn.closest(".comment-item");
  const contentP = row.querySelector(".comment-text");

  beforeReplyRow = row.cloneNode(true);
  beforeContent = contentP.innerText;
  row.innerHTML = "";

  const textarea = document.createElement("textarea");
  textarea.classList.add("form-control", "comment-textarea", "comment-textarea-update");
  textarea.value = beforeContent;

  const btnContainer = document.createElement("div");
  btnContainer.classList.add("comment-actions");

  const updateBtn = document.createElement("button");
  updateBtn.innerText = "수정";
  updateBtn.classList.add("btn", "btn-primary");
  updateBtn.setAttribute("onclick", `updateReply(${replyNo}, this)`);

  const cancelBtn = document.createElement("button");
  cancelBtn.innerText = "취소";
  cancelBtn.classList.add("btn", "btn-secondary");
  cancelBtn.setAttribute("onclick", "updateCancel(this)");

  btnContainer.append(updateBtn, cancelBtn);
  row.append(textarea, btnContainer);
};

const updateCancel = (btn) => {
  if(confirm("취소하시겠습니까?")) {
    const row = btn.closest(".comment-item");
    row.replaceWith(beforeReplyRow);
  }
};

const updateReply = (replyNo, btn) => {
  const row = btn.closest(".comment-item");
  const textarea = row.querySelector("textarea");

  if(textarea.value.trim().length == 0) {
    alert("댓글을 작성해주세요.");
    textarea.focus();
    return;
  }

  const data = {
    "replyNo" : replyNo,
    "replyContent" : textarea.value
  }

  fetch("/reply", {
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result => {
    if(result > 0) {
      alert("댓글이 수정되었습니다.");
      selectReplyList();
    } else {
      alert("댓글 수정에 실패하였습니다.");
    }
  })
  .catch(err => console.log(err));
}

// 댓글 삭제
const deleteReply = replyNo => {
  if(!confirm("댓글을 삭제하시겠습니까?")) return;

  fetch("/reply", {
    method:"DELETE",
    headers : {"Content-Type" : "application/json"},
    body : replyNo
  })
  .then(resp => resp.text())
  .then(result => {
    if (result > 0) {
      alert("댓글이 삭제되었습니다.");
      selectReplyList();
    } else {
      alert("삭제에 실패하였습니다.");
    } 
  })
  .catch(err => console.log(err));
}

const reportBtn = document.querySelector("#reportBtn");
reportBtn.addEventListener("click", () => {
  if(!confirm("이 게시글을 신고하시겠습니까?")) return;

  const reportReason = prompt("신고 사유를 입력해주세요.", "");
  const data = {
    postNo:postNo,
    reportReason:reportReason
  }

  if (reportReason == null || reportReason.trim().length === 0) {
    alert("신고 사유를 입력해주세요.");
    return;
  }

  fetch("/report", {
    method:"POST",
    headers:{"Content-Type" : "application/json"},
    body:JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result => {
    if(result > 0) {
      alert("신고가 접수되었습니다.");
    } else {
      alert("신고 접수에 실패하였습니다.");
    }
  })
  .catch(err => console.log(err));
})