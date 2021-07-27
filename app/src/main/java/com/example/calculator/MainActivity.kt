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
            textvalue= ""
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
            findViewById<TextView>(R.id.text).text = "$textvalue"
        }


    }


    //計算する四則演算子が押された時の処理
     fun Calculation(b: String) {
        //演算子の格納
         op = b
        //演算子が押された。
         flag = true
         if (textvalue != "") {
             if (value == 0) {
                 value = textvalue.toInt()
                 //Toast.makeText(applicationContext, "test", Toast.LENGTH_SHORT).show()
                 return
             } else {
                 value2 = textvalue.toInt()
             }
             if (!flagE) {

                 when (op) {
                     "+" -> {
                         value += value2
                     }
                     "-" -> {
                         value -= value2
                     }
                     "×" -> {
                        // Toast.makeText(applicationContext, "test", Toast.LENGTH_SHORT).show()
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
       Calculation(op)
        flagE = true


        //エラーが発生しているかの確認
        if(!Errorflag) {
            findViewById<TextView>(R.id.text).text = "$value"
        }else if(Errorflag){
           findViewById<TextView>(R.id.text).text = "$textvalue"
        }

    }

    }



