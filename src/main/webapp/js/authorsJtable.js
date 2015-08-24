$(document).ready(function() {


});


$(document).ready(function () {
    $('.upload').live('change', function()          {
        var id=this.id;
        $("#preview"+id).html('');
        $("#preview"+id).html('<img src="img/loader.gif" alt="Uploading...."/>');
        $("#form"+id).ajaxForm({
            target: '#preview'+id
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

        fields: {
            id: {
                title: 'Author Id',
                width: '30%',
                key: true,
                list: true,
                edit: false,
                create: false
            },
            name: {
                title: 'Name',
                width: '30%',
                edit: true
            },
            miniBio: {
                title: 'Mini Biography',
                width: '30%',
                type: 'textarea',
                edit: true
            },
            biography: {
                title: 'Biography',
                width: '30%',
                type: 'textarea',
                edit: true,
                list: false
            },
            language: {
                title: 'Language',
                width: '20%',
                options: {'en': 'English', 'zh': 'Chinese', 'ja': 'Japanese', 'pt': 'Portuguese', 'fr': 'French'},
                edit: true
            },
            joinedOn: {
                title: 'Joined On',
                width: '30%',
                type: 'date',
                edit: true
            },
            imgSrc: {
                title: 'Image',
                width: '30%',
                edit: true,
                list: false,
                input: function (data) {
                    return '<div id=' + data.record.PersonId + '><form id="form' + data.record.PersonId + '" method="post" enctype="multipart/form-data" action="ajaximage.php"><input type="file" name="photoimg" id="' + data.record.PersonId + '" class="upload"/><input type="hidden"  value="' + data.record.PersonId + '"/></form></div><div id="preview' + data.record.PersonId + '"></div>';
                }
            }
        }
    });
    $('#AuthorsTableContainer').jtable('load');
});