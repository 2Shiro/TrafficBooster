function createDialogButton(type, text, trigger, link, openNewTab) {

    const $button = $('<button></button>');
    $button.text(text);

    if (type === 'success') {
        $button.addClass('success-btn');
    } else if (type === 'warning') {
        $button.addClass('warning-btn');
    } else if (type === 'danger') {
        $button.addClass('danger-btn');
    } else if (type === 'dark') {
        $button.addClass('dark-btn');
    } else {
        $button.addClass('default-btn');
    }

    if (trigger !== null) {
        if (trigger === 'close') {
            $button.on('click', function () {
                $('#dialog-container').fadeOut(150);
            });
        } else if (trigger === 'link') {
            $button.on('click', function () {
                $('#dialog-container').fadeOut(150);
                if (openNewTab) {
                    window.open(link);
                } else {
                    window.location.href = link;
                }
            });
        } else if (trigger === 'refresh') {
            $button.on('click', function () {
                $('#preloader').fadeIn(0);
                window.location.reload();
            });
        } else if (trigger === 'channelTalk') {
            $button.on('click', function () {
                $('#dialog-container').fadeOut(150);
                window.ChannelIO('showMessenger');
            });
        }
    }

    return $button;


}


function showAlertDialog(request, buttons) {

    if ($('#dialog-container').length) {
        $('#dialog-container').remove();
    }

    const type = request.type;

    let $typeClass = 'alert-info';
    if (type === 'success') {
        $typeClass = 'alert-success';
    } else if (type === 'warning') {
        $typeClass = 'alert-warning';
    } else if (type === 'danger') {
        $typeClass = 'alert-danger';
    } else if (type === 'info') {
        $typeClass = 'alert-info';
    }

    let dialogHtml = `
        <div id="dialog-container">
            <div class="dialog-popup dialog-show" tabindex="-1" role="dialog" aria-modal="true">`;

    if (type !== undefined && type !== null) {
        dialogHtml += ` <span aria-hidden="false" role="presentation" class="dialog-icon ${$typeClass}"></span>`;
    }

    let titlePosition = request. titlePosition;
    let titlePositionClass = null;
    if (titlePosition === null || titlePosition === 'center') {
        titlePositionClass = 'text-center';
    } else {
        titlePositionClass = 'text-left';
    }

    let descPosition = request.descPosition;
    let descPositionClass;
    if (descPosition === null || descPosition === 'center') {
        descPositionClass = 'text-center';
    } else  {
        descPositionClass = 'text-start';
    }


    let titleEllipsisClass = '';

    if (request.titleEllipsis != null && request.titleEllipsis === true){
        titleEllipsisClass = 'text-ellipsis';
    }

    let descEllipsisClass = '';

    if (request.descEllipsis != null && request.descEllipsis === true){
        descEllipsisClass = 'text-ellipsis';
    }


    if (request.descPosition === undefined){
        dialogHtml += `<div class="dialog-content">
                    <div class="dialog-title ${titleEllipsisClass} ${titlePositionClass}">${request.title}</div>
                </div>
                <div class="interaction-group text-nowrap">
                </div>
            </div>
        </div>`;
    } else {
        dialogHtml += `<div class="dialog-content">
                    <div class="dialog-title ${titleEllipsisClass} ${titlePositionClass}">${request.title}</div>
                    <div class="dialog-description ${descEllipsisClass} ${descPositionClass}">${request.content}</div>
                </div>
                <div class="interaction-group text-nowrap">
                </div>
            </div>
        </div>`;
    }

    $('body').append(dialogHtml);

    if (buttons) {
        buttons.forEach(button => {
            $('.interaction-group').append(button);
        });
    }

    if (request.static === false) {
        $(document).off('keydown').on('keydown', function (event) {
            if (event.key === "Escape") {
                $('#dialog-container').fadeOut(300);
            }
        });
    }
}


