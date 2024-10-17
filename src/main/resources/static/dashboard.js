let details = document.getElementsByClassName("details-section");
let span = document.createElement('h3');
span.textContent = 'show more...'
span.setAttribute('id',"show");
span.classList.add('fs-5','text-primary','text-end');
collapseDiv();
span.addEventListener('click',()=>{
    collapseDiv();
    if(span.textContent === "show more...")
        span.textContent="show less...";
    else
        span.textContent = "show more...";
})
for (let i = 0; i < details.length; i++) {
    details[i].appendChild(span)
}
function collapseDiv(){
    let divs = document.querySelectorAll(".details-section>div");
    for (let i = 1; i < divs.length; i++) {
        divs[i].classList.toggle('collapse');
    }
}
console.log("HTML")