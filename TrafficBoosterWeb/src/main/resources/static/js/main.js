
// header.js
$(document).ready(function() {
    const $mobileMenuBtn = $('.mobile-menu-btn');
    const $mobileSidebar = $('.mobile-sidebar');
    const $sidebarOverlay = $('.sidebar-overlay');
    const $sidebarCloseBtn = $('.sidebar-close-btn');
    const $sidebarToggle = $('.sidebar-toggle');
    const $body = $('body');

    // 사이드바 열기
    function openSidebar() {
        $mobileSidebar.addClass('active');
        $sidebarOverlay.addClass('active');
        $body.addClass('sidebar-open');
    }

    // 사이드바 닫기
    function closeSidebar() {
        $mobileSidebar.removeClass('active');
        $sidebarOverlay.removeClass('active');
        $body.removeClass('sidebar-open');
    }

    // 햄버거 버튼 클릭
    $mobileMenuBtn.on('click', openSidebar);

    // 닫기 버튼 클릭
    $sidebarCloseBtn.on('click', closeSidebar);

    // 오버레이 클릭
    $sidebarOverlay.on('click', closeSidebar);

    // 설정 메뉴 토글 (모바일)
    $sidebarToggle.on('click', function(e) {
        e.preventDefault();
        $(this).closest('.sidebar-item').toggleClass('active');
    });

    // ESC 키로 사이드바 닫기
    $(document).on('keydown', function(e) {
        if (e.key === 'Escape' && $mobileSidebar.hasClass('active')) {
            closeSidebar();
        }
    });

    // 윈도우 리사이즈 시 데스크톱으로 전환되면 사이드바 닫기
    $(window).on('resize', function() {
        if ($(window).width() >= 960 && $mobileSidebar.hasClass('active')) {
            closeSidebar();
        }
    });
});



function getCookie(name) {
    const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
    return match ? match[2] : null;
}

function deleteCookie(name) {
    document.cookie = name + "=; Path=/; Max-Age=0;";
}

function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}