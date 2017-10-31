/**
 *
 * HTML5 Image uploader with Jcrop
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2012, Script Tutorials
 * http://www.script-tutorials.com/
 */
// convert bytes into friendly format
function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB'];
    if (bytes == 0) return 'n/a';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};

// check for selected crop region
function checkForm() {
    if (parseInt($('#w').val())) {
    	//改变上传时的样式
    	$(".upload-btn").attr("disabled", "disabled");
    	$(".upload-btn").css("background-color", "#999");
    	$(".upload-btn").val("上传中...");
    	return true;
    }
    $('.error').html('请选择截取部分，再点击上传').show();
    return false;
};

// update info by cropping (onChange and onSelect events handler)
function updateInfo(e) {
    $('#x1').val(e.x);
    $('#y1').val(e.y);
    $('#x2').val(e.x2);
    $('#y2').val(e.y2);
    $('#w').val(e.w);
    $('#h').val(e.h);
};

// clear info by cropping (onRelease event handler)
function clearInfo() {
    $('.info #w').val('');
    $('.info #h').val('');
};

//Create variables (in this scope) to hold the Jcrop API and image size
var jcrop_api, boundx, boundy;//预防不能更换图片
function fileSelectHandler() {

    // get selected file
    var oFile = $('#image_file')[0].files[0];

    // hide all errors
    $('.error').hide();

    // check for image type (jpg and png are allowed)
    var rFilter = /^(image\/jpeg|image\/png)$/i;
    if (! rFilter.test(oFile.type)) {
        $('.error').html('请选择一张jpg或者png图片').show();
        return;
    }

    // check for file size
    if (oFile.size > 512 * 1024) {
        $('.error').html('您选择的图片过大，请选择512K以内的图片').show();
        return;
    }

    // preview element
    var oImage = document.getElementById('preview');

    // prepare HTML5 FileReader
    var oReader = new FileReader();
        oReader.onload = function(e) {

        // e.target.result contains the DataURL which we can use as a source of the image
        oImage.src = e.target.result;
        oImage.onload = function () { // onload event handler

            // display some basic image info
            var sResultFileSize = bytesToSize(oFile.size);
            $('#filesize').val(sResultFileSize);
            $('#filetype').val(oFile.type);
            $('#filedim').val(oImage.naturalWidth + 'x' + oImage.naturalHeight);
          
            if (typeof jcrop_api != 'undefined') {
                jcrop_api.setImage(e.target.result);
            }
            else {
                $('.step2').fadeIn(500);
                $('#preview').Jcrop({
                    minSize: [50, 50], // min crop size
                    aspectRatio : 1, // keep aspect ratio 1:1
                    bgFade: true, // use fade effect
                    bgOpacity: .3, // fade opacity
                    boxWidth: 720,
                    boxHeight: 405,
                    onChange: updateInfo,
                    onSelect: updateInfo,
                    onRelease: clearInfo
                }, function(){
                    // use the Jcrop API to get the real image size
                         var bounds = this.getBounds();
                         boundx = bounds[0];
                         boundy = bounds[1];
                    // Store the Jcrop API in the jcrop_api variable
                    jcrop_api = this;
                });
            }
        };
    };

    // read selected file as DataURL
    oReader.readAsDataURL(oFile);
}