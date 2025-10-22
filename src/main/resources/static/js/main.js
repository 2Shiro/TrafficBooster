// 모바일 햄버거 메뉴 토글
$(document).ready(function () {
    const $mobileMenuToggle = $('.mobile-menu-toggle');
    const $sidebar = $('.sidebar');
    const $mobileOverlay = $('.mobile-overlay');
    const $profileDropdown = $('.profile-dropdown');
    const $profileButton = $('.profile-button');
    const $sidebarLinks = $('.sidebar-menu-link');

    // 햄버거 메뉴 클릭
    $mobileMenuToggle.on('click', function () {
        $(this).toggleClass('active');
        $sidebar.toggleClass('sidebar-active');
        $mobileOverlay.toggleClass('active');
        $('body').css('overflow', $sidebar.hasClass('sidebar-active') ? 'hidden' : '');
    });

    // 오버레이 클릭시 메뉴 닫기
    $mobileOverlay.on('click', function () {
        $mobileMenuToggle.removeClass('active');
        $sidebar.removeClass('sidebar-active');
        $(this).removeClass('active');
        $('body').css('overflow', '');
    });

    // 프로필 드롭다운 토글
    $profileButton.on('click', function (e) {
        e.stopPropagation();
        $profileDropdown.toggleClass('active');
    });

    // 문서 클릭시 드롭다운 닫기
    $(document).on('click', function (e) {
        if (!$profileDropdown.is(e.target) && $profileDropdown.has(e.target).length === 0) {
            $profileDropdown.removeClass('active');
        }
    });

    // 윈도우 리사이즈시 모바일 메뉴 상태 초기화
    $(window).on('resize', function () {
        if ($(window).width() > 991) {
            $mobileMenuToggle.removeClass('active');
            $sidebar.removeClass('sidebar-active');
            $mobileOverlay.removeClass('active');
            $('body').css('overflow', '');
        }
    });

    // 사이드바 메뉴 클릭시 모바일에서 메뉴 닫기
    $sidebarLinks.on('click', function () {
        if ($(window).width() <= 991) {
            $mobileMenuToggle.removeClass('active');
            $sidebar.removeClass('sidebar-active');
            $mobileOverlay.removeClass('active');
            $('body').css('overflow', '');
        }
    });
});

function activateSidebar() {
    let pathName = window.location.pathname;
    $(`.sidebar a[href="${pathName}"]`).addClass('active');
}
activateSidebar();


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