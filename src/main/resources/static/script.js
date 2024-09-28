document.querySelector('.sidebar-toggler').addEventListener('click',()=>{
    document.documentElement.style.setProperty('--sidebar-sliding-spped','0.65s');
    if(localStorage.getItem('sidebarStatus') != 'open'){
        localStorage.setItem('sidebarStatus','open');
        document.documentElement.style.setProperty('--sidebar-width','200px');
        document.documentElement.style.setProperty('--tab-text-show','inline');
        document.documentElement.style.setProperty('--sidebar-logo-width','80%');
        document.documentElement.style.setProperty('--tab-text-scale','scale(1)');
        document.documentElement.style.setProperty('--sidebar-toggler-rotate','rotate(180deg)');
    }else{
        localStorage.setItem('sidebarStatus','close');
        document.documentElement.style.setProperty('--sidebar-width','78px');
        document.documentElement.style.setProperty('--tab-text-show','none');
        document.documentElement.style.setProperty('--sidebar-logo-width','80%');
        document.documentElement.style.setProperty('--tab-text-scale','scale(0.2)');
        document.documentElement.style.setProperty('--sidebar-toggler-rotate','rotate(0deg)');


    }
})

window.addEventListener('load',()=>{
    let sidebarStatus = localStorage.getItem('sidebarStatus');
    if(sidebarStatus === 'open'){
        document.documentElement.style.setProperty('--sidebar-width','200px');
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