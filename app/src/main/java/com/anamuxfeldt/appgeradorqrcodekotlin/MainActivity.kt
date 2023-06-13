package com.anamuxfeldt.appgeradorqrcodekotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.anamuxfeldt.appgeradorqrcodekotlin.databinding.ActivityMainBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnGerarQRCode!!.setOnClickListener {

            val imm = binding.root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)


            if (TextUtils.isEmpty(binding.editQRCode!!.text.toString())) {
                //validar editText
                binding.editQRCode!!.error = "*"
                binding.editQRCode!!.requestFocus()


            } else {
                //gerar qrcode
                gerarQRCode( binding.editQRCode!!.text.toString())
            }


        }
    }

    fun gerarQRCode(conteudoQRCode: String) {

        val qrCode = QRCodeWriter()

        try {
            val bitMatrix = qrCode.encode(conteudoQRCode, BarcodeFormat.QR_CODE, 196, 196)
            val tamanho = bitMatrix.width
            val altura = bitMatrix.height

            val bitmap = Bitmap.createBitmap(tamanho, altura, Bitmap.Config.RGB_565)
            for (x in 0 until tamanho){
                for (y in 0 until altura){
                    bitmap.setPixel(x,y, if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }

            binding.imgQRCode!!.setImageBitmap(bitmap)

        } catch (e: WriterException) {
        }
    }
}