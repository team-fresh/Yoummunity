package com.yoummunity

import android.app.Activity
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import android.widget.Toast

class CustomViewClient(var activity: Activity) : WebViewClient() {
    override fun onLoadResource(view: WebView?, url: String?) {
        Log.d("SOEUN", "doUpdateVisitedHistory")
        var strings = url?.split("watch?v=")
        var videoId =
            if (strings!!.size >= 2) {
                strings[1].split("#")[0]
            } else {
                null
            }

        var delay = 2000L

        when (videoId) {
            GlobalClass.videoId, null -> {
                delay -= delay
            }
            else -> {
                CommentViewModel(
                    activity,
                    GlobalClass.webView?.url
                ).getComment()         // web crawling with retrofit2, coroutine

                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        // 댓글 보기 버튼 강제 클릭
                        view!!.loadUrl(
                            "javascript:" +
                                    "var button = document.getElementsByTagName(\"ytm-comment-section-header-renderer\")[0];" +
                                    "button.getElementsByTagName(\"button\")[0].click();"
                        )
                    }, 1000L
                )
            }
        }
//                      // 댓글 삭제
//                      view!!.loadUrl(
//                            "javascript:" +
//                            "var comment = document.getElementsByTagName('ytm-comment-thread-renderer')[0];" +
//                            "comment.parentNode.removeChild(comment);"

        var emojiUrl =
            "https://blogimgs.pstatic.net/sticker/xhdpi/moon_and_james/original/" // 39.png
        Handler(Looper.getMainLooper()).postDelayed(
            {
                // 이모티콘 불러오기
                view!!.loadUrl(
                    "javascript:" +
                            "var list = document.querySelectorAll(\"p.comment-text.user-text\");" +
                            "for(var i=0; i<list.length; i++) {" +
                            "   var origin = list.item(i);" +
                            "   if(!origin.innerHTML.includes(\"[yt-eco/emo/\")) continue;" +

                            "   var innerText = origin.innerHTML;" +
                            "   var matched = innerText.match(/(\\[yt-eco\\/emo\\/[0-9]+\\.(gif|png)\\])/g)[0];" +

                            "   var src = matched.replace(\"[yt-eco/emo/\", \"" + emojiUrl + "\").replace(\"]\", \"\");" +
                            "   origin.innerHTML = innerText.replace(matched, \"\");" +

                            "   origin.insertAdjacentHTML(\"afterbegin\"," + // or \"beforebegin\"
                            "       `<img src=\"\${src}\"/><br>`);" +
                            "}"
                )
            }, delay
        )
        super.onLoadResource(view, url)
    }
}