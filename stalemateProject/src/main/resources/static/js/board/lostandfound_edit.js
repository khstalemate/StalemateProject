const uploadImgBtn = document.querySelector("#uploadImg");  

uploadImgBtn.addEventListener("change", (e) => {
  const files = e.target.files;
  const label = document.querySelector(".custom-file-upload");
  console.log(files);

  // 파일 개수 제한
  if(files.length > 5) {
    alert("사진은 최대 5장만 업로드할 수 있습니다.");
    uploadImgBtn.value = "";
    label.innerText = "📷 사진 업로드 (클릭하여 파일 선택)";
    return;
  }

  // 파일명 출력
  if(files.length > 0) {
    const fileNameList = Array.from(files).map(file => file.name);
    label.innerText = `선택된 파일 : ${fileNameList.join(', ')}`;
  } else {
    label.innerText = "📷 사진 업로드 (클릭하여 파일 선택)";
  }
});

const cancleBtn = document.querySelector("#cancleBtn");
cancleBtn.addEventListener("click", ()=>{
  if(confirm("게시글 작성을 취소하시겠습니까?")) {
    alert("게시글 작성을 취소합니다.");
    location.href = "/lostandfound/";
  } 
});

const form = document.querySelector("#postWriteForm");
form.addEventListener("submit", e => {
  const postTitle = document.querySelector("[name=postTitle]");
  const category = document.querySelector("[name=category]");
  const missingDate = document.querySelector("[name=missingDate]");
  const missingTime = document.querySelector("[name=missingTime]");
  const location = document.querySelector("[name=location]");
  const detailLocation = document.querySelector("[name=detailLocation]");
  const species = document.querySelector("[name=species]");
  const gender = document.querySelector("[name=gender]");
  const age = document.querySelector("[name=age]");
  const weight = document.querySelector("[name=weight]");
  const content = document.querySelector("[name=content]");

  if(postTitle.value.trim().length === 0){
    alert("제목을 작성해주세요");
    postTitle.focus();
    e.preventDefault();
    return;
  }

  if(category.value.trim().length === 0){
    alert("제목을 작성해주세요");
    category.focus();
    e.preventDefault();
    return;
  }
  
    if(species.value.trim().length === 0){
    alert("제목을 작성해주세요");
    species.focus();
    e.preventDefault();
    return;
  }  

    if(gender.value.trim().length === 0){
    alert("제목을 작성해주세요");
    gender.focus();
    e.preventDefault();
    return;
  }  

    if(age.value.trim().length === 0){
    alert("제목을 작성해주세요");
    age.focus();
    e.preventDefault();
    return;
  }  
  
    if(weight.value.trim().length === 0){
    alert("제목을 작성해주세요");
    weight.focus();
    e.preventDefault();
    return;
  }  
});