let details = document.getElementsByClassName("details-section");
for (let i = 0; i < details.length; i++) {
    details[i].appendChild(showElement(i,details[i]));
}
function showElement(index,ele){
    const parent = ele;
    let state = 'close'
    let span = document.createElement('h3');
    span.textContent = 'show more...'
    span.setAttribute('id',`show-${index}`);
    span.setAttribute("index",index);
    // span.setAttribute("data-bs-toggle","collapse");
    // span.setAttribute("data-bs-target","#hide");
    span.classList.add('fs-5','text-primary','text-end');
    span.addEventListener('click',()=>{
        collapseDiv();
        console.log(parent);
        if(state === 'open'){
            span.textContent="show less...";
        }
        else{
            span.textContent = "show more...";
        }
    })
    function collapseDiv(){
        let divs = parent.querySelectorAll(".hide");
        for (let i = 0; i < divs.length; i++) {
            if(state === 'open'){
                divs[i].classList.add('collapse');
                state = 'close'
            }else{
                divs[i].classList.remove('collapse');
                state = 'open';
            }

        }

    }
    return span;
}