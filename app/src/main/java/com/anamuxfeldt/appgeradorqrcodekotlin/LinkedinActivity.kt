package com.anamuxfeldt.appgeradorqrcodekotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anamuxfeldt.appgeradorqrcodekotlin.databinding.ActivityLinkedinBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class LinkedinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLinkedinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinkedinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //gerar qrcode
        gerarQRCode(binding.editQRCodeLinkedin!!.text.toString())

        //botão voltar
        binding.btnVoltar!!.setOnClickListener{
            val intent = Intent (this@LinkedinActivity, FirstActivity::class.java )
            startActivity(intent)
            finish()
        }

    }



    fun gerarQRCode(conteudoQRCode: String) {

        val qrCode = QRCodeWriter()

        try {
            val bitMatrix = qrCode.encode(conteudoQRCode, BarcodeFormat.QR_CODE, 196, 196)
            val tamanho = bitMatrix.width
            val altura = bitMatrix.height

            val bitmap = Bitmap.createBitmap(tamanho, altura, Bitmap.Config.RGB_565)
            for (x in 0 until tamanho) {
                for (y in 0 until altura) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }

            binding.imgQRCode.setImageBitmap(bitmap)

        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }


}