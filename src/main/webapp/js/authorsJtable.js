$(document).ready(function (data) {

    $('.upload').live('change', function () {
        $("#previewImage").html('');
        $("#previewImage").html('<img src="/img/loader.gif" alt="Uploading...."/>');
        $("#imageUploadForm").ajaxForm({
            target: '#previewImage',
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $('#previewImage').html('<img src="data:image/png;base64,' + data + '" />')
            }
        }).submit();

    });

    $('#AuthorsTableContainer').jtable({
        title: 'Authors List',
        actions: {
            listAction: 'pageAction',
            createAction: 'createAction',
            updateAction: 'updateAction',
            deleteAction: 'deleteAction'
        },
        formCreated: function (event, data) {
            if (data.formType == 'edit') {
                $.ajax({
                    url: "downloadImage",
                    target: '#previewImage',
                    data: {id: data.record.id},
                    contentType: "application/json",
                    dataType: 'json',
                    success: function (data) {
                        $('#previewImage').html('<img src="data:image/png;base64,' + data + '" />')
                    }
                });
            }
        },
        fields: {
            id: {
                title: 'Author Id',
                width: '10%',
                key: true,
                list: true,
                edit: false,
                create: false
            },
            name: {
                title: 'Name',
                width: '30%',
                edit: true,
                create: true
            },
            miniBio: {
                title: 'Mini Biography',
                width: '30%',
                type: 'textarea',
                edit: true,
                create: true
            },
            biography: {
                title: 'Biography',
                width: '30%',
                type: 'textarea',
                edit: true,
                create: true,
                list: false
            },
            language: {
                title: 'Language',
                width: '20%',
                options: {'en': 'English', 'zh': 'Chinese', 'ja': 'Japanese', 'pt': 'Portuguese', 'fr': 'French'},
                edit: true,
                create: true,
                list: true
            },
            joinedOn: {
                title: 'Joined On',
                width: '30%',
                type: 'date',
                edit: true,
                create: true,
                list: true
            },
            imgSrc: {
                title: 'Image',
                width: '30%',
                edit: true,
                create: true,
                list: false,
                input: function (data) {
                    return '<div id="imageUpload"><form id="imageUploadForm" method="post" enctype="multipart/form-data" action="uploadImage"><input type="file" name="image" id="image" class="upload"/><input type="hidden"  value="image"/></form></div><div id="previewImage"></div>';
                }
            }
        }
    });
    $('#AuthorsTableContainer').jtable('load');
});