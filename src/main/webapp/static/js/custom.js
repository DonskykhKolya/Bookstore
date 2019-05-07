$(function () {
    switch (menu) {
        case 'About Us':
            $('#about').addClass('active');
            break;
        case 'Contact Us':
            $('#contact').addClass('active');
            break;
        case 'Reviews':
            $('#reviews').addClass('active');
            break;
        case 'All Books':
            $('#listBooks').addClass('active');
            break;
        case 'Control Books':
            $('#controlBooks').addClass('active');
            break;
        case 'User Shopping Catr':
            $('#userShoppingCart').addClass('active');
            break;
        default:
            if (menu == "Home") {
                $('#home').addClass('active');
                break;
            }
            $('#listBooks').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }

    var token = $('meta[name="_csrf"]').attr('content');
    var header = $('meta[name="_csrf_header"]').attr('content');

    if (token.length > 0 && header.length > 0) {
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
    var $table = $('#bookListTable');
    if ($table.length) {
        var jsonUrl = '';

        if (window.categoryId) {
            jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/books'
        } else {
            if (window.authorName) {
                jsonUrl = window.contextRoot + '/json/data/author/' + window.authorName + '/books'
            } else {
                jsonUrl = window.contextRoot + '/json/data/all/books'
            }
        }
        $table
            .DataTable({
                lengthMenu: [
                    [3, 5, 10, -1],
                    ['3 Records', '5 Records', '10 Records',
                        'All Records']],
                pageLength: 5,
                ajax: {
                    url: jsonUrl,
                    dataSrc: '',
                },
                columns: [
                    {
                        data: 'code',
                        mRender: function (data, type, row) {
                            return '<img src="' + window.contextRoot
                                + '/static/images/' + data
                                + '.jpg" class="dataTableImg"/>'
                        }
                    },
                    {
                        data: 'title',
                    },
                    {
                        data: 'authorName',
                    },
                    {
                        data: 'dateOfPublish',
                    },
                    {
                        data: 'price',
                        mRender: function (data, type, row) {
                            return '&#36; ' + data;
                        }
                    },
                    {
                        data: 'quantity',
                        mRender: function (data, type, row) {
                            if (data < 1) {
                                return '<span style="color:red;">Out of Stock!</span>';
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        data: 'id',
                        bSortable: false,

                        mRender: function (data, type, row) {
                            var str = '';
                            str += '<a href="' + window.contextRoot + '/show/' + data + '/books" class="btn btn-primary">&#128065;</a>';

                            if (row.quantity >= 1) {

                                if (userRole == 'ADMIN') {
                                    str += '<a href="'
                                        + window.contextRoot
                                        + '/control/'
                                        + data
                                        + '/books" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

                                } else {
                                    str += '<a href="'
                                        + window.contextRoot
                                        + '/shoppingCart/add/'
                                        + data
                                        + '/books" class="btn btn-outline-success">&#128722;</a>';

                                }
                            } else {

                                if (userRole == 'ADMIN') {
                                    str += '<a href="' + window.contextRoot + '/control/' + data + '/books" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

                                } else {

                                    str += '<a href="javascript:void(0)" class="btn btn-outline-success disabled">&#128722;</a>';
                                }
                            }

                            return str;
                        }
                    },]
            });
    }

    var $alert = $('.alert');
    if ($alert.length) {
        setTimeout(function () {
            $alert.fadeOut('slow');
        }, 3000)
    }

    var $adminBooksTable = $('#adminBooksTable');
    if ($adminBooksTable.length) {
        var jsonUrl = window.contextRoot + '/json/data/admin/all/books';

        $adminBooksTable
            .DataTable({
                lengthMenu: [
                    [10, 30, 50, -1],
                    ['10 Records', '30 Records', '50 Records',
                        'All Records']],
                pageLength: 30,
                ajax: {
                    url: jsonUrl,
                    dataSrc: '',
                    dataType: 'json',
                },
                columns: [
                    {
                        data: 'id'
                    },
                    {
                        data: 'code',
                        mRender: function (data, type, row) {
                            return '<img src="'
                                + window.contextRoot
                                + '/static/images/'
                                + data
                                + '.jpg" class="adminDataTableImg"/>'
                        }
                    },
                    {
                        data: 'title',
                    },
                    {
                        data: 'authorName',
                    },
                    {
                        data: 'dateOfPublish',
                    },
                    {
                        data: 'quantity',
                        mRender: function (data, type, row) {
                            if (data < 1) {
                                return '<span style="color:red;">Out of Stock!</span>';
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        data: 'price',
                        mRender: function (data, type, row) {
                            return '&#36; ' + data;
                        }
                    },

                    {
                        data: 'active',
                        bSortable: false,
                        mRender: function (data, type, row) {
                            var str = '';

                            str += '<label class="switch">';
                            if (data) {
                                str += '<input type="checkbox" checked="checked" value="' + row.id + '" />'
                            } else {
                                str += '<input type="checkbox" value="' + row.id + '" />'
                            }

                            str += '<div class="slider"></div></label>';

                            return str;

                        }

                    },

                    {
                        data: 'id',
                        bSortable: false,
                        mRender: function (data, type, row) {
                            var str = '';
                            str += '<a href="' + window.contextRoot + '/control/' + data + '/books" class="btn btn-warning">';
                            str += '<span class="glyphicon glyphicon-pencil"></span></a>';

                            return str;

                        }

                    },],
                initComplete: function () {
                    var api = this.api();
                    api
                        .$('.switch input[type="checkbox"]')
                        .on(
                            'change',
                            function () {
                                var checkbox = $(this);
                                var checked = checkbox
                                    .prop('checked');

                                var dMsg = (checked) ? 'You want to activate the Book?'
                                    : 'You want to deactivate the Book?';
                                var value = checkbox.prop('value');

                                bootbox
                                    .confirm({
                                        size: 'medium',
                                        title: 'Book Activation & Deactivation',
                                        message: dMsg,
                                        callback: function (confirmed) {
                                            if (confirmed) {
                                                var activationUrl = window.contextRoot + '/control/books/' + value + '/activation';
                                                $
                                                    .post(
                                                        activationUrl,
                                                        function (
                                                            data) {
                                                            bootbox
                                                                .alert({
                                                                    size: 'medium',
                                                                    title: 'Information',
                                                                    message: data
                                                                });
                                                        });

                                            } else {
                                                checkbox
                                                    .prop(
                                                        'checked',
                                                        !checked);
                                            }
                                        }
                                    });

                            });
                }
            });
    }

    var $authorForm = $('#authorForm');

    if ($authorForm.length) {
        $authorForm
            .validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 3
                    },
                    description: {
                        required: true
                    }
                },
                massage: {
                    name: {
                        required: 'Please Add the author!',
                        minlength: 'The author should not be less than 3 characters'
                    },
                    description: {
                        required: 'Please add a description for this author'
                    }
                },
                errorElement: 'em',
                errorPlacement: function (error, element) {
                    error.addClass('help-block');
                    error.insertAfter(element);
                }
            });
    }

    var $categoryForm = $('#categoryForm');

    if ($categoryForm.length) {
        $categoryForm
            .validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 4
                    },
                    description: {
                        required: true
                    }
                },
                massage: {
                    name: {
                        required: 'Please Add the category name!',
                        minlength: 'The category name should not be less than 4 characters'
                    },
                    description: {
                        required: 'Please add a description for this category'
                    }
                },
                errorElement: 'em',
                errorPlacement: function (error, element) {
                    error.addClass('help-block');
                    error.insertAfter(element);
                }
            });
    }

    var $loginForm = $('#loginForm');

    if ($loginForm.length) {
        $loginForm.validate({
            rules: {
                username: {
                    required: true,
                    email: true
                },
                password: {
                    required: true
                }
            },
            massage: {
                username: {
                    required: 'Please Enter the name or suname',
                    email: 'Please Enter Valid Email Address'
                },
                description: {
                    required: 'Please Enter Password'
                }
            },
            errorElement: 'em',
            errorPlacement: function (error, element) {
                error.addClass('help-block');
                error.insertAfter(element);
            }
        });
    }

    $('button[name="refreshCart"]')
        .click(
            function () {

                var booksInCartId = $(this).attr('value');
                var countElement = $('#count_' + booksInCartId);
                var originalCount = countElement.attr('value');
                var currentCount = countElement.val();

                if (currentCount !== originalCount) {

                    if (currentCount < 1 || currentCount > 3) {
                        countElement.val(originalCount);
                        bootbox
                            .alert({
                                size: 'medium',
                                title: 'Error',
                                message: 'Book count should be mininum 1 and maximum 3!'

                            });
                    } else {
                        var updateUrl = window.contextRoot + '/shoppingCart/' + booksInCartId + '/update?count=' + currentCount;
                        window.location.href = updateUrl;
                    }
                }
            });

    var domen = 'http://storemybook.herokuapp.com';

    $("#review_submit").on('click', function () {
        $.ajax({
            url: domen + '/addRewiew',
            type: "POST",
            data: {
                name: $("#name").val(),
                review: $("#review").val(),
                rating: $("#rating").val(),
            },
            dataType: 'json',
            success: function (json) {
                console.log(json);
                var template = $(".hidden > li");
                var temp = template.clone();
                temp.find(".user_name").text($("#name").val());
                temp.find(".review_text").text($("#review").val());
                temp.find(".rate_us").text($("#rating").val());
                $("#comms").append(temp);
                document.getElementById("review_form").reset();
            },
            error: function (json) {
                console.log('error');
                console.log(json);
            }
        });
    });
});
