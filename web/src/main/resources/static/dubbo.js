$(function () {
    // Ajax Auth
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    // SearchBox
    $('#searchBoxButton').click(function () {
        $('.search-toggle').show(0);
        $('main,header,footer').addClass('blur');
    });

    // ModuleMenu
    $('#moduleMenuButton').click(function () {
        $('.module-toggle').show(0);
        $('main,header,footer').addClass('blur');
    });

    // Mask
    $('.blur-mask').click(function () {
        $('.search-toggle').hide(0);
        $('.module-toggle').hide(0);
        $('main,header,footer').removeClass('blur');
    });

    $('.updateButton').click()(function () {
        alert("123");
    })
});