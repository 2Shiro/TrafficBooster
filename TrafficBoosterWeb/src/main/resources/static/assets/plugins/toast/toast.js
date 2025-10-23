// 기존 함수 유지
function showToast(message, duration = 3000) {
    const toast = $(`<div class="toast-message">${message}</div>`);
    $('body').append(toast);

    setTimeout(() => toast.addClass('show'), 100);
    setTimeout(() => {
        toast.removeClass('show');
        setTimeout(() => toast.remove(), 300);
    }, duration);
}

// 타입별 toast 함수 추가
function showTypedToast(message, type = 'default', duration = 3000) {
    const toast = $(`<div class="toast-message ${type}">${message}</div>`);
    $('body').append(toast);

    setTimeout(() => toast.addClass('show'), 100);
    setTimeout(() => {
        toast.removeClass('show');
        setTimeout(() => toast.remove(), 300);
    }, duration);
}

// 편의 함수들
function showSuccessToast(message, duration = 3000) {
    showTypedToast(message, 'success', duration);
}

function showInfoToast(message, duration = 3000) {
    showTypedToast(message, 'info', duration);
}


function showErrorToast(message, duration = 3000) {
    showTypedToast(message, 'error', duration);
}

function showWarningToast(message, duration = 3000) {
    showTypedToast(message, 'warning', duration);
}