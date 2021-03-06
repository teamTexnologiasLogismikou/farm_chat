            $(document).ready(function() {
                $("#webcam").scriptcam({
                    width: 320,
                    height: 240,
                    showMicrophoneErrors:false,
                    onError:onError,
                    cornerRadius:20,
                    disableHardwareAcceleration:1,
                    cornerColor:'e3e5e2',
                    onWebcamReady:onWebcamReady,
                    uploadImage:'upload.gif',
                    onPictureAsBase64:base64_tofield_and_image
                });
            });
			
            function base64_tofield() {
                $('#formfield').val($.scriptcam.getFrameAsBase64());
            };
            function base64_toimage() {
                $('#image').attr("src","data:image/png;base64,"+$.scriptcam.getFrameAsBase64());
            };
            function base64_tofield_and_image() {
                $('#base64Form').val($.scriptcam.getFrameAsBase64());
                $('#image').attr("src","data:image/png;base64,"+$.scriptcam.getFrameAsBase64());
            };
            function changeCamera() {
                $.scriptcam.changeCamera($('#cameraNames').val());
            }
            function onError(errorId,errorMsg) {
                $( "#btn1" ).attr( "disabled", true );
                $( "#btn2" ).attr( "disabled", true );
                alert(errorMsg);
            }          
            function onWebcamReady(cameraNames,camera,microphoneNames,microphone,volume) {
                $.each(cameraNames, function(index, text) {
                    $('#cameraNames').append( $('<option></option>').val(index).html(text) )
                });
                $('#cameraNames').val(camera);
            }
            
            $(document).ready(function() {
                $('#showHideBtn').click(function() {
                    $('#showHide').toggle("slide");
                });
            });