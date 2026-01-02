document.querySelector("#goToList").addEventListener("click", () => {
  location.href = "/lostandfound/";
});

const statusChangeBtn = document.querySelector("#statusChangeBtn");
const statusSelect = document.querySelector("#statusSelect");
statusChangeBtn.addEventListener("click", ()=>{
  if (statusSelect.style.display === "none" || statusSelect.style.display === "") {
        statusSelect.style.display = "inline-block";
        statusSelect.focus();
      } else {
        statusSelect.style.display = "none";
      }
});