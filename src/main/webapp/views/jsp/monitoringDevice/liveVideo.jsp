<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
    <video id="myPlayer" poster="" controls playsInline webkit-playsinline autoplay width="800" height="600">
        <source src="rtmp://rtmp.open.ys7.com/openlive/d1feb006b05e42308a47d7792b114c82" type="" />
    </video>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        var player = new EZUIPlayer('myPlayer');
        player.on('error', function () {
            console.log('error');
        });
        player.on('play', function () {
            console.log('play');
        });
        player.on('pause', function () {
            console.log('pause');
        });
    });
</script>
