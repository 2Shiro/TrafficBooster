$(function() {
    const modalHtml = `
        <div class="alert-modal-overlay" id="alertModal">
          <div class="alert-modal-container">
            <h3 class="alert-modal-title" id="alertModalTitle"></h3>
            <p class="alert-modal-content" id="alertModalContent"></p>
            <div class="alert-modal-button-group" id="alertModalButtonGroup"></div>
          </div>
        </div>
      `;
    $('body').append(modalHtml);
});

function showCustomAlert(title, content, buttons = []) {
    const modal = $('#alertModal');
    const titleElement = $('#alertModalTitle');
    const contentElement = $('#alertModalContent');
    const buttonGroup = $('#alertModalButtonGroup');

    titleElement.text(title);
    contentElement.html(content);
    buttonGroup.empty();

    if (buttons.length === 0) {
        buttons.push({
            text: '닫기',
            class: 'alert-modal-button alert-modal-button-secondary',
            action: function() {
                hideCustomAlert();
            }
        });
    }

    buttons.forEach(function(button) {
        let buttonElement;
        if (button.href) {
            buttonElement = $('<a></a>')
                .attr('href', button.href)
                .attr('target', button.target || '_self');
        } else {
            buttonElement = $('<button></button>');
        }
        buttonElement.text(button.text);
        buttonElement.attr('class', button.class || 'alert-modal-button alert-modal-button-secondary');
        buttonElement.on('click', button.action || function() {
            hideCustomAlert();
        });
        buttonGroup.append(buttonElement);
    });

    modal.addClass('show');
}

function hideCustomAlert() {
    $('#alertModal').removeClass('show');
}

// 모달 배경 클릭시 닫기
$('body').on('click', '#alertModal', function(e) {
    if (e.target === this) {
        hideCustomAlert();
    }
});
