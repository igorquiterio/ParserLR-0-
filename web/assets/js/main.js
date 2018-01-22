function showDatepicker() {
    if ($('.datepicker').length > 0) {
        $('.datepicker').datepicker({
            language: 'pt-BR',
            format: 'dd/mm/yyyy',
            autoclose: true
        });
    }
}

function readUser() {
    $.getJSON($(this).data('href'), function (data) {
        var usuario = data;
        $('.p_id').html('<strong>ID: </strong>' + usuario.id);
        $('.p_login').html('<strong>Login2: </strong>' + usuario.login);
        $('.p_nome').html('<strong>Nome: </strong>' + usuario.nome);
        $('.p_nascimento').html('<strong>Data de nascimento: </strong>' + usuario.nascimento);
        $('.modal_visualizar_usuario').modal();
    });
}

function deleteUser() {
    $('.link_confirmacao_excluir_usuario').attr('href', $(this).data('href'));
    $('.modal_excluir_usuario').modal();
}

function deleteUsers() {
    $('.form_excluir_usuarios').submit();
}

$(document).ready(function () {
    showDatepicker();
    //showErrors();
    $(document).on('click', '.link_excluir_usuario', deleteUser);
    $(document).on('click', '.button_confirmacao_excluir_usuarios', deleteUsers);
    $(document).on('click', '.link_visualizar_usuario', readUser);
});