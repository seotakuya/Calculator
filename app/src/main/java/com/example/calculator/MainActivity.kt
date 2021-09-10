package com.example.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //この処理は処理ファイルから指定するやり方
        // var a = findViewById<Button>(R.id.bt2)
        // a.setBackgroundColor(Color.rgb(200, 0, 0))
    }
    //色の管理
    var colorcount = 0

    //textの値
    var textvalue = ""

    //合計の格納
    var value = 0

    //変化の値格納
    var value2 = 0

    //エラーが発生した時の確認
    var Errorflag = false

    //演算子が押されたか確認
    var flag = false
    //イコールが押されたか確認
    var flagE = false

    //演算子の種類を格納
    var op = ""

    //0の押された回数
    var count = 0


    //演算子が押された場合の処理
    fun onCilckBt2(v: View) {
        when (v.id) {
            R.id.op -> Calculation("×")
            R.id.op2 -> Calculation("÷")
            R.id.op3 -> Calculation("-")
            R.id.op4 -> sample()
            R.id.op5 -> Calculation("+")
        }
    }


    //数値が押された場合の処理
    fun onClickBt(v: View) {
        when (v.id) {
            R.id.bt0 -> text("0")
            R.id.bt1 -> text("1")
            R.id.bt2 -> text("2")
            R.id.bt3 -> text("3")
            R.id.bt4 -> text("4")
            R.id.bt5 -> text("5")
            R.id.bt6 -> text("6")
            R.id.bt7 -> text("7")
            R.id.bt8 -> text("8")
            R.id.bt9 -> text("9")
            //R.id.bt -> findViewById<TextView>(R.id.text).text="1"
            //R.id.bt1 -> findViewById<TextView>(R.id.text).text="1"
        }
    }


    //それぞれ数字のボタン処理
    //public fun onClickBt1(v: View){
    //   value += 1
    // findViewById<TextView>(R.id.text).text= "$value"
    //}

    //Cボタンを押した場合のメソッド
    public fun onClickClose(v: View) {
        op = ""
        value2 = 0
        value = 0
        textvalue = ""
        flag = false
        flagE = false
        Errorflag = false
        count = 0
        findViewById<TextView>(R.id.text).text = "0"
        findViewById<TextView>(R.id.textleft).text = ""
    }

    public fun Close() {
        op = ""
        value2 = 0
        value = 0
        flag = false
        flagE = false
        Errorflag = false
        count = 0
        findViewById<TextView>(R.id.text).text = "$textvalue"
        textvalue = ""
        findViewById<TextView>(R.id.textleft).text = ""
    }

    //数字が動くメソッド
     fun text(a: String) {

        //イコールのリセット
         flagE = false


        //演算子が押されたかのチェック
        if (flag) {
            //演算子が押されていた時のリセット
            textvalue = ""
            flag = false
            count = 0
        }


        //初期の値に0の値が入っているか確認
        if(textvalue.equals("0")){

            if(count!=1){

            textvalue= ""
            }
        }else {
                textvalue += a

        }

        //追加した値が0であるか確認
        if (textvalue.equals("0")) {

            //0が押された数をcount
            count++


            //0が押されるのが1回だった時の処理
            if(count<2){
                findViewById<TextView>(R.id.text).text = "$textvalue"

            }else {

                //1回以上の時の処理
                textvalue = ""
                findViewById<TextView>(R.id.text).text = "0"

            }


        } else {
            if (textvalue.length < 11) {
                findViewById<TextView>(R.id.text).text = "$textvalue"
            } else {
                textvalue = "Overflow"
                findViewById<TextView>(R.id.text).text = "$textvalue"
                Close()
            }
        }
    }


    //計算する四則演算子が押された時の処理
     fun Calculation(b: String) {
        //演算子の格納
         op = b
        findViewById<TextView>(R.id.textleft).text = op

        //演算子が押された。
         flag = true

         if (textvalue != "") {

             if (value == 0) {  //合計値が0であるかを確認

                 //valueの値が0の時は直接valueに値を入れている。
                 value = textvalue.toInt()

                 //Toast.makeText(applicationContext, "test", Toast.LENGTH_SHORT).show()
                 return

             } else {//合計値に値が入っている場合は変化値を格納する変数に代入
                 value2 = textvalue.toInt()
             }

             if (!flagE) { //イコールがfalseの時は計算する。

                 when (op) { //演算子の種類の判定
                     "+" -> {
                         value += value2
                     }
                     "-" -> {
                         value -= value2
                     }
                     "×" -> {
                             value *= value2
                     }

                     "÷" -> {
                         try {
                             value /= value2
                         } catch (e: ArithmeticException) {
                             textvalue = "0除算エラー"
                             Errorflag = true
                             return
                         }
                     }
                 }
             }

         }
     }

    //イコールボタンを押したときのメソッド
    fun sample() {
        //イコールが押された時の演算処理
       Calculation(op)
        //イコールが押されているかの確認
        flagE = true
        findViewById<TextView>(R.id.textleft).text=""

        //エラーが発生しているかの確認
        if(!Errorflag) {
            textvalue = value.toString()
            if(textvalue.length < 11) {
                findViewById<TextView>(R.id.text).text = "$value"
            }else{
                textvalue = "Overflow"
                findViewById<TextView>(R.id.text).text = "$textvalue"
                Close()
            }
        }else if(Errorflag){
           findViewById<TextView>(R.id.text).text = "$textvalue"
        }

    }



    //色の変換機能メソッド
    fun onClickcolor(v:View){
         colorcount++
        when(colorcount){
            1 -> Redcolor()
            2 -> Greencolor()
            3 -> Bluecolor()
            4 -> Brackcolor()

        }


    }


    //赤
    fun Redcolor(){
        var a = findViewById<Button>(R.id.bt0)
        a.setBackgroundColor(Color.rgb(200, 0, 0))

        var b = findViewById<Button>(R.id.bt1)
        b.setBackgroundColor(Color.rgb(200, 0, 0))

        var c = findViewById<Button>(R.id.bt2)
        c.setBackgroundColor(Color.rgb(200, 0, 0))

        var d = findViewById<Button>(R.id.bt3)
        d.setBackgroundColor(Color.rgb(200, 0, 0))

        var e = findViewById<Button>(R.id.bt4)
        e.setBackgroundColor(Color.rgb(200, 0, 0))

        var f = findViewById<Button>(R.id.bt5)
        f.setBackgroundColor(Color.rgb(200, 0, 0))

        var g = findViewById<Button>(R.id.bt6)
        g.setBackgroundColor(Color.rgb(200, 0, 0))

        var h = findViewById<Button>(R.id.bt7)
        h.setBackgroundColor(Color.rgb(200, 0, 0))

        var i = findViewById<Button>(R.id.bt8)
        i.setBackgroundColor(Color.rgb(200, 0, 0))

        var j = findViewById<Button>(R.id.bt9)
        j.setBackgroundColor(Color.rgb(200, 0, 0))

        var k = findViewById<Button>(R.id.op)
        k.setBackgroundColor(Color.rgb(200, 0, 0))

        var l = findViewById<Button>(R.id.op2)
        l.setBackgroundColor(Color.rgb(200, 0, 0))

        var n = findViewById<Button>(R.id.op3)
        n.setBackgroundColor(Color.rgb(200, 0, 0))

        var m = findViewById<Button>(R.id.op4)
        m.setBackgroundColor(Color.rgb(200, 0, 0))


        var o = findViewById<Button>(R.id.op5)
        o.setBackgroundColor(Color.rgb(200, 0, 0))

        var p = findViewById<Button>(R.id.close)
        p.setBackgroundColor(Color.rgb(200, 0, 0))

        var q = findViewById<Button>(R.id.color)
        q.setBackgroundColor(Color.rgb(200, 0, 0))

    }

    //緑
    fun Greencolor(){
        var a = findViewById<Button>(R.id.bt0)
        a.setBackgroundColor(Color.rgb(0,200,0))

        var b = findViewById<Button>(R.id.bt1)
        b.setBackgroundColor(Color.rgb(0, 200, 0))

        var c = findViewById<Button>(R.id.bt2)
        c.setBackgroundColor(Color.rgb(0, 200, 0))

        var d = findViewById<Button>(R.id.bt3)
        d.setBackgroundColor(Color.rgb(0, 200, 0))

        var e = findViewById<Button>(R.id.bt4)
        e.setBackgroundColor(Color.rgb(0, 200, 0))

        var f = findViewById<Button>(R.id.bt5)
        f.setBackgroundColor(Color.rgb(0, 200, 0))

        var g = findViewById<Button>(R.id.bt6)
        g.setBackgroundColor(Color.rgb(0, 200,0 ))

        var h = findViewById<Button>(R.id.bt7)
        h.setBackgroundColor(Color.rgb(0, 200, 0))

        var i = findViewById<Button>(R.id.bt8)
        i.setBackgroundColor(Color.rgb(0, 200, 0))

        var j = findViewById<Button>(R.id.bt9)
        j.setBackgroundColor(Color.rgb(0, 200, 0))

        var k = findViewById<Button>(R.id.op)
        k.setBackgroundColor(Color.rgb(0, 200, 0))

        var l = findViewById<Button>(R.id.op2)
        l.setBackgroundColor(Color.rgb(0, 200, 0))

        var n = findViewById<Button>(R.id.op3)
        n.setBackgroundColor(Color.rgb(0, 200, 0))

        var m = findViewById<Button>(R.id.op4)
        m.setBackgroundColor(Color.rgb(0, 200, 0))

        var o = findViewById<Button>(R.id.op5)
        o.setBackgroundColor(Color.rgb(0, 200, 0))

        var p = findViewById<Button>(R.id.close)
        p.setBackgroundColor(Color.rgb(0, 200, 0))

        var q = findViewById<Button>(R.id.color)
        q.setBackgroundColor(Color.rgb(0, 200, 0))

    }

    //青
    fun Bluecolor(){
            var a = findViewById<Button>(R.id.bt0)
            a.setBackgroundColor(Color.rgb(0, 0, 200))

            var b = findViewById<Button>(R.id.bt1)
            b.setBackgroundColor(Color.rgb(0, 0, 200))

            var c = findViewById<Button>(R.id.bt2)
            c.setBackgroundColor(Color.rgb(0, 0, 200))

            var d = findViewById<Button>(R.id.bt3)
            d.setBackgroundColor(Color.rgb(0, 0, 200))

            var e = findViewById<Button>(R.id.bt4)
            e.setBackgroundColor(Color.rgb(0, 0, 200))

            var f = findViewById<Button>(R.id.bt5)
            f.setBackgroundColor(Color.rgb(0, 0, 200))

            var g = findViewById<Button>(R.id.bt6)
            g.setBackgroundColor(Color.rgb(0, 0, 200))

            var h = findViewById<Button>(R.id.bt7)
            h.setBackgroundColor(Color.rgb(0, 0, 200))

            var i = findViewById<Button>(R.id.bt8)
            i.setBackgroundColor(Color.rgb(0, 0, 200))

            var j = findViewById<Button>(R.id.bt9)
            j.setBackgroundColor(Color.rgb(0, 0, 200))

            var k = findViewById<Button>(R.id.op)
            k.setBackgroundColor(Color.rgb(0, 0, 200))

            var l = findViewById<Button>(R.id.op2)
            l.setBackgroundColor(Color.rgb(0, 0, 200))

            var n = findViewById<Button>(R.id.op3)
            n.setBackgroundColor(Color.rgb(0, 0, 200))

            var m = findViewById<Button>(R.id.op4)
            m.setBackgroundColor(Color.rgb(0, 0, 200))


            var o = findViewById<Button>(R.id.op5)
            o.setBackgroundColor(Color.rgb(0, 0, 200))

            var p = findViewById<Button>(R.id.close)
            p.setBackgroundColor(Color.rgb(0, 0, 200))

            var q = findViewById<Button>(R.id.color)
            q.setBackgroundColor(Color.rgb(0, 0, 200))


        }

    fun Brackcolor(){
        var a = findViewById<Button>(R.id.bt0)
        a.setBackgroundColor(Color.rgb(0, 0, 0))

        var b = findViewById<Button>(R.id.bt1)
        b.setBackgroundColor(Color.rgb(0, 0, 0))

        var c = findViewById<Button>(R.id.bt2)
        c.setBackgroundColor(Color.rgb(0, 0, 0))

        var d = findViewById<Button>(R.id.bt3)
        d.setBackgroundColor(Color.rgb(0, 0, 0))

        var e = findViewById<Button>(R.id.bt4)
        e.setBackgroundColor(Color.rgb(0, 0, 0))

        var f = findViewById<Button>(R.id.bt5)
        f.setBackgroundColor(Color.rgb(0, 0, 0))

        var g = findViewById<Button>(R.id.bt6)
        g.setBackgroundColor(Color.rgb(0, 0, 0))

        var h = findViewById<Button>(R.id.bt7)
        h.setBackgroundColor(Color.rgb(0, 0, 0))

        var i = findViewById<Button>(R.id.bt8)
        i.setBackgroundColor(Color.rgb(0, 0, 0))

        var j = findViewById<Button>(R.id.bt9)
        j.setBackgroundColor(Color.rgb(0, 0, 0))

        var k = findViewById<Button>(R.id.op)
        k.setBackgroundColor(Color.rgb(0, 0, 0))

        var l = findViewById<Button>(R.id.op2)
        l.setBackgroundColor(Color.rgb(0, 0, 0))

        var n = findViewById<Button>(R.id.op3)
        n.setBackgroundColor(Color.rgb(0, 0, 0))

        var m = findViewById<Button>(R.id.op4)
        m.setBackgroundColor(Color.rgb(0, 0, 0))


        var o = findViewById<Button>(R.id.op5)
        o.setBackgroundColor(Color.rgb(0, 0, 0))

        var p = findViewById<Button>(R.id.close)
        p.setBackgroundColor(Color.rgb(0, 0, 0))

        var q = findViewById<Button>(R.id.color)
        q.setBackgroundColor(Color.rgb(0, 0, 0))

        colorcount = 0
    }
    }




