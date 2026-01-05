// 로그인 상태인 경우에 글쓰기 버튼 보여주기
const insertBtn = document.querySelector("#insertBtn");

if (insertBtn != null) {
  insertBtn.addEventListener("click", () => {
    location.href = ``;
  });
}

// 분양, 입양 게시판의 총 게시글 개수 가져오기
const allPostCount = document.querySelector("#allPostCount");

function getAllPostCount() {
  fetch("/adoption/getAllPostCount")
    .then((resp) => resp.text())
    .then((count) => {
      allPostCount.innerText = count;
    });
}

// 상태가 실종인 게시글의 개수 가져오기
const lostPostCount = document.querySelector("#salePostCount");

function getSalePostCount() {
  fetch("/adoption/getSalePostCount")
    .then((resp) => resp.text())
    .then((count) => {
      lostPostCount.innerText = count;
    });
}

// 상태가 목격인 게시글의 개수 가져오기
const witnessPostCount = document.querySelector("#adoptionPostCount");

function getAdoptionPostCount() {
  fetch("/adoption/getAdoptionPostCount")
    .then((resp) => resp.text())
    .then((count) => {
      witnessPostCount.innerText = count;
    });
}

// 오늘 등록한 게시글의 개수 가져오기
const todayPostCount = document.querySelector("#todayPostCount");

function getTodayPostCount() {
  fetch("/adoption/getTodayPostCount")
    .then((resp) => resp.text())
    .then((count) => {
      todayPostCount.innerText = count;
    });
}

getAllPostCount();
getSalePostCount();
getAdoptionPostCount();
getTodayPostCount();

// 게시글 제목, 게시글 내용 말줄임표
const characters = document.querySelectorAll(".character-limit");

characters.forEach((item) => {
  const limit = item.dataset.limit;
  const originalText = item.textContent.trim();
  if (originalText.length > limit) {
    const resultText = originalText.substring(0, limit - 3) + "...";
    item.textContent = resultText;
  }
});

const popup = document.querySelector(".input");
const popupLayer = document.querySelector(".popup");
const popupOverlay = document.querySelector(".popup-overlay");
const popupClose = document.querySelector("#popup-close");

// '제목, 내용으로 검색' 입력창을 눌렀을 때 검색 팝업창 보여주기
popup.addEventListener("click", () => {
  popupLayer.classList.remove("popup-hidden");
  popupOverlay.classList.remove("popup-hidden");
});

// 팝업창의 X 버튼 눌렀을 때 팝업창 숨기기
popupClose.addEventListener("click", () => {
  popupLayer.classList.add("popup-hidden");
  popupOverlay.classList.add("popup-hidden");
});

// 배경(오버레이) 클릭 시에도 닫히게 설정
popupOverlay.addEventListener("click", () => {
  popupLayer.classList.add("popup-hidden");
  popupOverlay.classList.add("popup-hidden");
});