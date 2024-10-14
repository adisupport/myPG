
let rooms;
window.addEventListener('load',()=>{
    rooms = document.querySelectorAll(".table tr");
})

document.querySelector('.sidebar-toggler').addEventListener('click',()=>{
    let sidebar_open_width = '200px';
    let sidebar_close_width = "78px";
    if (window.screen.orientation.type.startsWith('portrait')) {
        // sidebar_close_width = document.documentElement.style.getPropertyValue('--sidebar-close-width');
        // sidebar_open_width = document.documentElement.style.getPropertyValue('--sidebar-open-width');
        sidebar_open_width = '320px';
        sidebar_close_width='120px'
    } else if (window.screen.orientation.type.startsWith('landscape')) {
        sidebar_close_width = '78px';
        sidebar_open_width = '200px';
    }

    document.documentElement.style.setProperty('--sidebar-sliding-speed','0.65s');
    if(localStorage.getItem('sidebarStatus') !== 'open'){
        localStorage.setItem('sidebarStatus','open');
        document.documentElement.style.setProperty('--sidebar-width',sidebar_open_width);
        document.documentElement.style.setProperty('--tab-text-show','inline');
        document.documentElement.style.setProperty('--sidebar-logo-width','80%');
        document.documentElement.style.setProperty('--tab-text-scale','scale(1)');
        document.documentElement.style.setProperty('--sidebar-toggler-rotate','rotate(180deg)');
    }else{
        localStorage.setItem('sidebarStatus','close');
        document.documentElement.style.setProperty('--sidebar-width',sidebar_close_width);
        document.documentElement.style.setProperty('--tab-text-show','none');
        document.documentElement.style.setProperty('--sidebar-logo-width','80%');
        document.documentElement.style.setProperty('--tab-text-scale','scale(0.2)');
        document.documentElement.style.setProperty('--sidebar-toggler-rotate','rotate(0deg)');


    }
})

window.addEventListener('load',()=>{
    let sidebarStatus = localStorage.getItem('sidebarStatus');
    let sidebar_open_width = '200px';
    if (window.screen.orientation.type.startsWith('portrait')) {
        // sidebar_open_width = document.documentElement.style.getPropertyValue('--sidebar-open-width');
        sidebar_open_width = '320px'
    } else if (window.screen.orientation.type.startsWith('landscape')) {
        sidebar_open_width = '200px';
    }
    if(sidebarStatus === 'open'){
        document.documentElement.style.setProperty('--sidebar-width',sidebar_open_width);
        document.documentElement.style.setProperty('--tab-text-show','inline');
        document.documentElement.style.setProperty('--sidebar-logo-width','80%');
        document.documentElement.style.setProperty('--tab-text-scale','scale(1)');
        document.documentElement.style.setProperty('--sidebar-toggler-rotate','rotate(180deg)');
    }
    // document.documentElement.style.setProperty('--sidebar-sliding-spped','0.65s');

})

let errorWindow = document.querySelectorAll('.error-message');
errorWindow.forEach((element)=>{
    element.addEventListener('click',()=>{
        element.style.display = 'none';
    })
})



// Search Box Implementation
function searchTable(col= 0,value) {
    // Clear any previous highlights
    const rows = document.querySelectorAll("tr");
    rows.forEach(row => {
        row.classList = "";
    });
    if(!value){
        const searchBar = document.getElementById("searchInput");
        value = searchBar.value;
    }
    let found = false;

    // Loop through each row to find the match
    rows.forEach(row => {
        const cells = row.getElementsByTagName("td");
        for (let i = 0; i < cells.length; i++) {
            if (cells[col].innerHTML === value) {
                row.classList.add("table-primary");
                row.scrollIntoView({ behavior: "smooth", block: "center" });
                found = true;
                break;
            }
        }
    });

    // If no match is found, show an alert
    if (!found) {
        alert("No Record found.");
    }
}

//Scroll TOP Button

let scrollToTopBtn = document.createElement('button');
scrollToTopBtn.innerHTML = 'Go to Top';
scrollToTopBtn.classList.add('btn','btn-primary','scroll-top-btn');
scrollToTopBtn.style.display = 'none';
scrollToTopBtn.addEventListener('click',()=>{
    window.scrollTo({
        top:0,
        behavior:'smooth'
    })
})

document.body.appendChild(scrollToTopBtn);

window.addEventListener('scroll',()=>{
    if(window.pageYOffset>300){
        scrollToTopBtn.style.display = 'block';
    }else{
        scrollToTopBtn.style.display = 'none';
    }
})

let btn = document.querySelectorAll('.action-card');

btn.forEach((b,index)=>{
    b.addEventListener('click',()=>{
        for(let i=0;i<btn.length;i++) btn[i].classList.remove('action-card-active');
        b.classList.add('action-card-active');
        // if(index === 1)
        //     row_Filter("EMPTY")
        // else if(index === 2){
        //     row_Filter("FILLED")
        // }else{
        //     row_Filter();
        // }


    })
})
function row_Filter(value){
    let Tbody = document.querySelectorAll(".table tbody");
    let node = [];
    Tbody[0].innerHTML = "";

    for(let i= 1; i < rooms.length;i++){
        let cells = rooms[i].getElementsByTagName("td");
        if(!value){
            Tbody[0].innerHTML += rooms[i].innerHTML;
        }else{
            for(let j = 0;j<cells.length;j++){
                if(cells[j].textContent === value){
                    node.push(rooms[i]);
                    Tbody[0].innerHTML += rooms[i].innerHTML;
                }
            }
        }

    }
}